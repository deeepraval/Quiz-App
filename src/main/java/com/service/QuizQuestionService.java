package com.service;

import com.dto.QuizQuestionRequest;
import com.entity.Quiz;
import com.entity.QuizQuestion;
import com.repository.QuizQuestionRepository;
import com.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizQuestionService {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    private QuizRepository quizRepository;

    // 7. addQuizQuestion - input: quizId, question, option1,2,3,4, correctAns
    public QuizQuestion addQuizQuestion(QuizQuestionRequest request) {
        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + request.getQuizId()));

        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setQuiz(quiz);
        quizQuestion.setQuestion(request.getQuestion());
        quizQuestion.setOption1(request.getOption1());
        quizQuestion.setOption2(request.getOption2());
        quizQuestion.setOption3(request.getOption3());
        quizQuestion.setOption4(request.getOption4());
        quizQuestion.setCorrectAnswer(request.getCorrectAnswer());

        return quizQuestionRepository.save(quizQuestion);
    }

    // 8. removeQuizQuestion - input: quizQuestionId
    public String removeQuizQuestion(Integer quizQuestionId) {
        if (quizQuestionRepository.existsById(quizQuestionId)) {
            quizQuestionRepository.deleteById(quizQuestionId);
            return "Quiz question deleted successfully!";
        }
        throw new RuntimeException("Quiz question not found with id: " + quizQuestionId);
    }

    // 9. updateQuizQuestion - input: quizId, question, option1,2,3,4, correctAns, quizQuestionId
    public QuizQuestion updateQuizQuestion(QuizQuestionRequest request) {
        QuizQuestion quizQuestion = quizQuestionRepository.findById(request.getQuizQuestionId())
                .orElseThrow(() -> new RuntimeException("Quiz question not found with id: " + request.getQuizQuestionId()));

        // Update quiz if quizId is provided and different
        if (request.getQuizId() != null && !quizQuestion.getQuiz().getQuizId().equals(request.getQuizId())) {
            Quiz quiz = quizRepository.findById(request.getQuizId())
                    .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + request.getQuizId()));
            quizQuestion.setQuiz(quiz);
        }

        // Update other fields
        if (request.getQuestion() != null) {
            quizQuestion.setQuestion(request.getQuestion());
        }
        if (request.getOption1() != null) {
            quizQuestion.setOption1(request.getOption1());
        }
        if (request.getOption2() != null) {
            quizQuestion.setOption2(request.getOption2());
        }
        if (request.getOption3() != null) {
            quizQuestion.setOption3(request.getOption3());
        }
        if (request.getOption4() != null) {
            quizQuestion.setOption4(request.getOption4());
        }
        if (request.getCorrectAnswer() != null) {
            quizQuestion.setCorrectAnswer(request.getCorrectAnswer());
        }

        return quizQuestionRepository.save(quizQuestion);
    }

    // 10. listAllQuizQuestionByQuizId - input: quizId
    public List<QuizQuestion> listAllQuizQuestionByQuizId(Integer quizId) {
        return quizQuestionRepository.findByQuizQuizId(quizId);
    }

    // Additional method to get question by ID
    public QuizQuestion getQuizQuestionById(Integer quizQuestionId) {
        return quizQuestionRepository.findById(quizQuestionId)
                .orElseThrow(() -> new RuntimeException("Quiz question not found with id: " + quizQuestionId));
    }
}