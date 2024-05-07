package com.lingotutor.languageservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lingotutor.languageservice.bean.ArticleIdNameResponse;
import com.lingotutor.languageservice.bean.ArticleResponse;
import com.lingotutor.languageservice.bean.IdAndName;
import com.lingotutor.languageservice.bean.LanguageResponse;
import com.lingotutor.languageservice.bean.Quiz;
import com.lingotutor.languageservice.entity.Article;
import com.lingotutor.languageservice.entity.Language;
import com.lingotutor.languageservice.entity.Section;
import com.lingotutor.languageservice.proxy.QuizServiceProxy;
import com.lingotutor.languageservice.repository.ArticleRepository;
import com.lingotutor.languageservice.repository.LanguageRepository;
import com.lingotutor.languageservice.repository.SectionRepository;

@RestController
@RequestMapping("/languages")
public class LanguageResource {

	@Autowired
	private QuizServiceProxy quizProxy;

	@Autowired
	private LanguageRepository langRepo;

	@Autowired
	private SectionRepository sectionRepo;

	@Autowired
	private ArticleRepository articleRepo;
	
	@Autowired
	Environment environment;


	@GetMapping(produces = "application/json")
	public ResponseEntity<List<IdAndName>> getAllLanguages() {
		return ResponseEntity.ok(langRepo.findAll().stream().map(lang->new IdAndName(lang)).toList());
	}

	@GetMapping(path = "/{id}",produces = "application/json")
	public ResponseEntity<Object> getLanguageById(@PathVariable("id") Long id) {
	
		Optional<Language> language = langRepo.findById(id);
		if (language.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		List<Quiz> quizzes = quizProxy.getQuizzesByLanguageId(id);
		LanguageResponse response = new LanguageResponse(language.get(), quizzes);
		//HATEOAS
		EntityModel<LanguageResponse> languageModel = EntityModel.of(response);
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllLanguages());
		languageModel.add(link.withRel("all-languages"));
		return ResponseEntity.ok(languageModel);
	
	}

	@GetMapping(path="/{languageId}/quizzes/{quizId}/answers",produces = "application/json")
	public ResponseEntity<Object> getLanguageMcqsByQuizId(@PathVariable("languageId") Long languageId,
			@PathVariable("quizId") long quizId) {
		var response = quizProxy.getAllAnswers(languageId, quizId).getBody();

		return ResponseEntity.ok(response);
	}

	@GetMapping(path="/sections",produces = "application/json")
	public ResponseEntity<List<Section>> getAllSections() {
		return ResponseEntity.ok(sectionRepo.findAll());
	}

	@GetMapping(path="/sections/{sectionId}",produces = "application/json")
	public ResponseEntity<Object> getSectionById(@PathVariable("sectionId") Long sectionId) {
		Optional<Section> section = sectionRepo.findById(sectionId);
		if (section.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(section.get());
	}

	@GetMapping(path="/articles",produces = "application/json")
	public ResponseEntity<List<ArticleResponse>> getAllArticles() {
		String port = environment.getProperty("local.server.port");
		return ResponseEntity.ok(articleRepo.findAll().stream().map(article -> new ArticleResponse(article,port)).toList());
	}

	@GetMapping(path="/articles/{articleId}",produces = "application/json")
	public ResponseEntity<Object> getArticleById(@PathVariable("articleId") Long articleId) {
		Optional<Article> article = articleRepo.findById(articleId);
		if (article.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		String port = environment.getProperty("local.server.port");

		EntityModel<ArticleResponse> articleModel = EntityModel.of(new ArticleResponse(article.get(),port));
		WebMvcLinkBuilder articleLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllArticles());
		WebMvcLinkBuilder sectionLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getSectionById(article.get().getSection().getId()));
		WebMvcLinkBuilder infoLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getArticleInfoById(article.get().getId()));
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllLanguages());
		
		
		articleModel.add(articleLink.withRel("all-articles"));
		articleModel.add(sectionLink.withRel("section-articles"));
		articleModel.add(infoLink.withRel("article-info"));
		articleModel.add(link.withRel("all-languages"));
	
		return ResponseEntity.ok(articleModel);
	}
	
	@GetMapping(path="/articles/{articleId}/info",produces = "application/json")
	public ResponseEntity<Object> getArticleInfoById(@PathVariable("articleId") Long articleId) {
		Optional<Article> article = articleRepo.findById(articleId);

		if (article.isEmpty()) {
			return ResponseEntity.ok(new ArticleIdNameResponse(articleId));
		}

		return ResponseEntity.ok(new ArticleIdNameResponse(article.get()));
	}

}
