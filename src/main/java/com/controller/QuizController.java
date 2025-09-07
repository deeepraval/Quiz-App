package com.controller;

import com.dto.QuizRequest;
import com.entity.Quiz;
import com.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // 2. addQuiz - input: title, description
    @PostMapping("/add")
    public ResponseEntity<Quiz> addQuiz(@RequestBody QuizRequest request) {
        Quiz quiz = quizService.addQuiz(request);
        return ResponseEntity.ok(quiz);
    }

    // 3. updateQuiz - input: title, description, active, quizId
    @PutMapping("/update")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody QuizRequest request) {
        Quiz quiz = quizService.updateQuiz(request);
        return ResponseEntity.ok(quiz);
    }

    // 4. deleteQuiz - input: quizId
    @DeleteMapping("/delete/{quizId}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Integer quizId) {
        String result = quizService.deleteQuiz(quizId);
        return ResponseEntity.ok(result);
    }

    // 5. listAllQuiz - get all quiz from database
    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> listAllQuiz() {
        List<Quiz> quizzes = quizService.listAllQuiz();
        return ResponseEntity.ok(quizzes);
    }

    // 6. listAllActiveQuiz - get all active quiz from database
    @GetMapping("/active")
    public ResponseEntity<List<Quiz>> listAllActiveQuiz() {
        List<Quiz> quizzes = quizService.listAllActiveQuiz();
        return ResponseEntity.ok(quizzes);
    }

    // Additional endpoint to get quiz by ID
    @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Integer quizId) {
        Quiz quiz = quizService.getQuizById(quizId);
        return ResponseEntity.ok(quiz);
    }
}