package com.lingotutor.languageservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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


	@GetMapping
	public ResponseEntity<List<IdAndName>> getAllLanguages() {
		return ResponseEntity.ok(langRepo.findAll().stream().map(lang->new IdAndName(lang)).toList());
	}

	@GetMapping("/{id}")
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

	@GetMapping("/{languageId}/quizzes/{quizId}/answers")
	public ResponseEntity<Object> getLanguageMcqsByQuizId(@PathVariable("languageId") Long languageId,
			@PathVariable("quizId") long quizId) {
		var response = quizProxy.getAllAnswers(languageId, quizId).getBody();

		return ResponseEntity.ok(response);
	}

	@GetMapping("/sections")
	public ResponseEntity<List<Section>> getAllSections() {
		return ResponseEntity.ok(sectionRepo.findAll());
	}

	@GetMapping("/sections/{sectionId}")
	public ResponseEntity<Object> getSectionById(@PathVariable("sectionId") Long sectionId) {
		Optional<Section> section = sectionRepo.findById(sectionId);
		if (section.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(section.get());
	}

	@GetMapping("/articles")
	public ResponseEntity<List<ArticleResponse>> getAllArticles() {
		return ResponseEntity.ok(articleRepo.findAll().stream().map(article -> new ArticleResponse(article)).toList());
	}

	@GetMapping("/articles/{articleId}")
	public ResponseEntity<Object> getArticleById(@PathVariable("articleId") Long articleId) {
		Optional<Article> article = articleRepo.findById(articleId);
		if (article.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		EntityModel<ArticleResponse> articleModel = EntityModel.of(new ArticleResponse(article.get()));
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
	
	@GetMapping("/articles/{articleId}/info")
	public ResponseEntity<Object> getArticleInfoById(@PathVariable("articleId") Long articleId) {
		Optional<Article> article = articleRepo.findById(articleId);

		if (article.isEmpty()) {
			return ResponseEntity.ok(new ArticleIdNameResponse(articleId));
		}

		return ResponseEntity.ok(new ArticleIdNameResponse(article.get()));
	}

}
