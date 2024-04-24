package com.lingotutor.quizzes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lingotutor.quizzes.bean.QuestionAnswer;
import com.lingotutor.quizzes.bean.QuizNameAndLevelBean;
import com.lingotutor.quizzes.repo.QuizIdNameLevelAndAnswers;
import com.lingotutor.quizzes.repo.QuizRepository;


@RestController
@RequestMapping("/quizzes")
public class QuizResource {

	@Autowired
	private QuizRepository quizRepo;

	@GetMapping("/all")
	public ResponseEntity<Object> getAllQuizzes() {
		return ResponseEntity.ok(quizRepo.findAll());
	}

	@GetMapping
	public ResponseEntity<Object> getQuizzesByLanguageId(
			@RequestParam(name = "languageId", required = false) Long languageId) {
		if (languageId == null) {
			return getAllQuizzes();
		}
		List<QuizIdNameLevelAndAnswers> respList = quizRepo.findAllByLanguageId(languageId).get();

		var response = respList.stream().map(obj -> new QuizNameAndLevelBean(obj)).toList();

		return ResponseEntity.ok(response);
	}

	@GetMapping("/answers")
	public ResponseEntity<Object> getAllAnswers(@RequestParam(name = "languageId", required = false) Long languageId,
			@RequestParam(name = "quizId", required = false) Long quizId) {
		if (quizId != null) {
			var resp = quizRepo.findById(quizId).get().getAnswers();
			return ResponseEntity.ok(resp);
		} else if (languageId != null && quizId == null) {
			List<QuizIdNameLevelAndAnswers> resp = quizRepo.findAllByLanguageId(languageId).get();
			return ResponseEntity.ok(resp);
		}
		return getAllQuizzes();

	}

	@GetMapping("/{quizId}/answers")
	public ResponseEntity<Object> getAllAnswersByQuizId(@PathVariable(name = "quizId", required = false) Long quizId) {
		if (quizId != null) {
			var resp = quizRepo.findById(quizId).get().getAnswers();
			return ResponseEntity.ok(resp);
		}
		return getAllQuizzes();

	}

	@PostMapping("/{quizId}/answers/scores")
	public ResponseEntity<Object> saveQuizScore(@RequestBody List<QuestionAnswer> submitReq,
			@PathVariable(name = "quizId") Long quizId) {

		var resp = quizRepo.findById(quizId).get().getAnswers();

		var maxScore = resp.size();

		var score = 0;
		for (int i = 0; i < resp.size(); i++) {

			long currentMcqId = resp.get(i).getMcq();
			long answerId = resp.get(i).getChoice();

			Optional<QuestionAnswer> userSubmittedQA = submitReq.stream()
					.filter(x -> x.getMcq() == currentMcqId).findFirst();
			if (userSubmittedQA.isPresent()) {
				if (userSubmittedQA.get().getChoice() == answerId) {
					score++;
				}
			}

		}

		return ResponseEntity.ok("score -> "+score +" out of "+maxScore);

	}
}
