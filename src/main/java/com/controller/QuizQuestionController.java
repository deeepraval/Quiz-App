package com.controller;

import com.dto.QuizQuestionRequest;
import com.entity.QuizQuestion;
import com.service.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quiz/questions")
public class QuizQuestionController {

    @Autowired
    private QuizQuestionService quizQuestionService;

    // 7. addQuizQuestion - input: quizId, question, option1,2,3,4, correctAns
    @PostMapping("/add")
    public ResponseEntity<QuizQuestion> addQuizQuestion(@RequestBody QuizQuestionRequest request) {
        QuizQuestion quizQuestion = quizQuestionService.addQuizQuestion(request);
        return ResponseEntity.ok(quizQuestion);
    }

    // 8. removeQuizQuestion - input: quizQuestionId
    @DeleteMapping("/remove/{quizQuestionId}")
    public ResponseEntity<String> removeQuizQuestion(@PathVariable Integer quizQuestionId) {
        String result = quizQuestionService.removeQuizQuestion(quizQuestionId);
        return ResponseEntity.ok(result);
    }

    // 9. updateQuizQuestion - input: quizId, question, option1,2,3,4, correctAns, quizQuestionId
    @PutMapping("/update")
    public ResponseEntity<QuizQuestion> updateQuizQuestion(@RequestBody QuizQuestionRequest request) {
        QuizQuestion quizQuestion = quizQuestionService.updateQuizQuestion(request);
        return ResponseEntity.ok(quizQuestion);
    }

    // 10. listAllQuizQuestionByQuizId - input: quizId
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuizQuestion>> listAllQuizQuestionByQuizId(@PathVariable Integer quizId) {
        List<QuizQuestion> questions = quizQuestionService.listAllQuizQuestionByQuizId(quizId);
        return ResponseEntity.ok(questions);
    }

    // Additional endpoint to get question by ID
    @GetMapping("/{quizQuestionId}")
    public ResponseEntity<QuizQuestion> getQuizQuestionById(@PathVariable Integer quizQuestionId) {
        QuizQuestion question = quizQuestionService.getQuizQuestionById(quizQuestionId);
        return ResponseEntity.ok(question);
    }
}