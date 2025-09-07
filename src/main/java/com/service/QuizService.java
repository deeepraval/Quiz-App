package com.service;

import com.dto.QuizRequest;
import com.entity.Quiz;
import com.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    // 2. addQuiz - input: title, description
    public Quiz addQuiz(QuizRequest request) {
        Quiz quiz = new Quiz(request.getTitle(), request.getDescription());
        return quizRepository.save(quiz);
    }

    // 3. updateQuiz - input: title, description, active, quizId
    public Quiz updateQuiz(QuizRequest request) {
        Optional<Quiz> quizOpt = quizRepository.findById(request.getQuizId());
        if (quizOpt.isPresent()) {
            Quiz quiz = quizOpt.get();
            quiz.setTitle(request.getTitle());
            quiz.setDescription(request.getDescription());
            if (request.getActive() != null) {
                quiz.setActive(request.getActive());
            }
            return quizRepository.save(quiz);
        }
        throw new RuntimeException("Quiz not found with id: " + request.getQuizId());
    }

    // 4. deleteQuiz - input: quizId
    public String deleteQuiz(Integer quizId) {
        Optional<Quiz> quizOpt = quizRepository.findById(quizId);
        if (quizOpt.isPresent()) {
            quizRepository.deleteById(quizId);
            return "Quiz deleted successfully!";
        }
        throw new RuntimeException("Quiz not found with id: " + quizId);
    }

    // 5. listAllQuiz - get all quiz from database
    public List<Quiz> listAllQuiz() {
        return quizRepository.findAll();
    }

    // 6. listAllActiveQuiz - get all active quiz from database
    public List<Quiz> listAllActiveQuiz() {
        return quizRepository.findByActiveTrue();
    }

    // Additional method to get quiz by ID
    public Quiz getQuizById(Integer quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + quizId));
    }
}