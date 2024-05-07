package com.lingotutor.quizzes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lingotutor.quizzes.bean.QuestionAnswer;
import com.lingotutor.quizzes.bean.QuizMcqsResponse;
import com.lingotutor.quizzes.bean.QuizNameAndLevelBean;
import com.lingotutor.quizzes.bean.UserQuizScoreRequest;
import com.lingotutor.quizzes.entity.MultiChoiceQuestion;
import com.lingotutor.quizzes.proxy.UserServiceProxy;
import com.lingotutor.quizzes.repo.MultiChoiceQuestionId;
import com.lingotutor.quizzes.repo.MultiChoiceQuestionRepository;
import com.lingotutor.quizzes.repo.QuizIdNameLevelAndAnswers;
import com.lingotutor.quizzes.repo.QuizRepository;

@RestController
@RequestMapping("/quizzes")
public class QuizResource {

	@Autowired
	private QuizRepository quizRepo;
	
	
	@Autowired
	private MultiChoiceQuestionRepository mcqRepo;

	@Autowired
	private UserServiceProxy userServiceProxy;
	
	
	private  ResponseEntity<Object> getAllQuizzes() {
		return ResponseEntity.ok(quizRepo.findAll());
	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<Object> getQuizzes(@RequestParam(name = "languageId", required = false) Long languageId) {
		if (languageId == null) {
			return getAllQuizzes();
		}
		List<QuizIdNameLevelAndAnswers> respList = quizRepo.findAllByLanguageId(languageId).get();

		var response = respList.stream().map(obj -> new QuizNameAndLevelBean(obj)).toList();

		return ResponseEntity.ok(response);
	}

	@GetMapping(path="/{quizId}",produces = "application/json")
	public ResponseEntity<Object> getQuizById(@PathVariable("quizId") Long quizId) {

		Optional<List<MultiChoiceQuestionId>> quiz = mcqRepo.findAllByQuizId(quizId);

		if (quiz.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		QuizMcqsResponse response	= new QuizMcqsResponse(quiz.get().stream().map(x->x.getId()).toList());

		return ResponseEntity.ok(response);

	}
	
	@GetMapping(path="mcqs/{mcqId}",produces = "application/json")
	public ResponseEntity<Object> getMcqByIdAndQuizId(@PathVariable("mcqId") Long mcqId) {
		Optional<MultiChoiceQuestion> mcq = mcqRepo.findById(mcqId);
		return ResponseEntity.ok(mcq.get());
	}

	@GetMapping(path="/{quizId}/answers",produces = "application/json")
	public ResponseEntity<Object> getAllAnswersByQuizId(@PathVariable(name = "quizId", required = false) Long quizId) {
		if (quizId != null) {
			var resp = quizRepo.findById(quizId).get().getAnswers();
			return ResponseEntity.ok(resp);
		}
		return getAllQuizzes();

	}

	@PostMapping(path="/{quizId}/answers/scores",consumes = "application/json",produces = "application/json")
	public ResponseEntity<Object> saveQuizScore(@RequestBody List<QuestionAnswer> submitReq,
			@PathVariable(name = "quizId") Long quizId, @RequestHeader("userId") long userId) {

		var resp = quizRepo.findById(quizId).get().getAnswers();

		var maxScore = resp.size();

		var score = 0;
		for (int i = 0; i < resp.size(); i++) {

			long currentMcqId = resp.get(i).getMcq();
			long answerId = resp.get(i).getChoice();

			Optional<QuestionAnswer> userSubmittedQA = submitReq.stream().filter(x -> x.getMcq() == currentMcqId)
					.findFirst();
			if (userSubmittedQA.isPresent()) {
				if (userSubmittedQA.get().getChoice() == answerId) {
					score++;
				}
			}
		}

		return userServiceProxy.saveQuizScore(userId, new UserQuizScoreRequest(userId, quizId, score, maxScore));

	}
}
