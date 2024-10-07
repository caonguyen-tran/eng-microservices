package com.engapp.QuizService.service.implement;

import com.engapp.QuizService.configuration.CustomUserDetails;
import com.engapp.QuizService.configuration.PrincipalConfiguration;
import com.engapp.QuizService.exception.ApplicationException;
import com.engapp.QuizService.exception.ErrorCode;
import com.engapp.QuizService.pojo.Answer;
import com.engapp.QuizService.pojo.ExamResponses;
import com.engapp.QuizService.pojo.Question;
import com.engapp.QuizService.pojo.QuizResult;
import com.engapp.QuizService.repository.ExamResponseRepository;
import com.engapp.QuizService.service.AnswerService;
import com.engapp.QuizService.service.ExamResponsesService;
import com.engapp.QuizService.service.QuizResultService;
import com.engapp.QuizService.utils.enumerate.PointEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ExamResponsesServiceImplement implements ExamResponsesService {
    @Autowired
    private ExamResponseRepository examResponseRepository;

    @Autowired
    private PrincipalConfiguration principalConfiguration;

    @Autowired
    private QuizResultService quizResultService;

    @Autowired
    private AnswerService answerService;

    @Override
    public List<ExamResponses> createMultipleExamResponses(List<Question> questions, QuizResult quizResult) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        List<ExamResponses> examResponses = new ArrayList<>();
        for (Question question : questions) {
            examResponses.add(createSingleExamResponse(question, quizResult, userDetails.getId()));
        }
        return examResponses;
    }

    @Override
    public ExamResponses createSingleExamResponse(Question question, QuizResult quizResult, String userId) {
        ExamResponses examResponses = new ExamResponses();
        examResponses.setQuestion(question);
        examResponses.setResult(quizResult);
        examResponses.setUserId(userId);
        examResponses.setIsAnswer(false);
        return this.examResponseRepository.save(examResponses);
    }

    @Override
    public List<ExamResponses> getMultipleExamResponses(QuizResult quizResult) {
        CustomUserDetails userDetails = this.principalConfiguration.getCustomUserDetails();
        if (!quizResult.getUserId().equals(userDetails.getId())) {
            throw new ApplicationException(ErrorCode.NOT_ACCEPTABLE);
        }

        return this.examResponseRepository.findByResultId(quizResult.getId());
    }

    @Override
    public List<ExamResponses> reDoMultiplelExamResponses(List<ExamResponses> examResponses) {
        List<ExamResponses> examResponsesList = new ArrayList<>();
        for (ExamResponses examResponse : examResponses) {
            examResponse.setIsAnswer(false);
            examResponse.setAnswer(null);
            examResponse.setIsCorrect(false);
            examResponsesList.add(this.examResponseRepository.save(examResponse));
        }
        return examResponsesList;
    }

    @Override
    public QuizResult submitQuiz(List<ExamResponses> examResponses) {
        List<ExamResponses> examResponsesList = new ArrayList<>();
        int correctCount = 0;
        int overallPoint = 0;
        for (ExamResponses examResponse : examResponses) {
            ExamResponses examResponsesInstance = this.examResponseRepository
                    .findByExamId(examResponse.getId())
                    .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_EXIST));

            Answer answer = examResponse.getAnswer();
            if (answer != null) {
                examResponsesInstance.setAnswer(examResponse.getAnswer());
                String answerKey = answer.getAnswerKey();
                if (examResponse.getQuestion().getCorrectAnswer().equals(answerKey)) {
                    examResponsesInstance.setIsCorrect(true);
                    correctCount++;
                } else {
                    examResponsesInstance.setIsCorrect(false);
                }
                examResponsesInstance.setIsAnswer(true);
            }

            examResponsesList.add(this.examResponseRepository.save(examResponsesInstance));
        }
        overallPoint = correctCount * PointEnum.READING_POINT.getPoint();
        QuizResult quizResult = this.quizResultService.findById(examResponsesList.getFirst().getResult().getId());

        if (quizResult != null) {
            quizResult.setCorrectAnswers(correctCount);
            quizResult.setOverallPoint(overallPoint);

            return this.quizResultService.saveQuizResult(quizResult);
        }

        throw new ApplicationException(ErrorCode.RUNTIME_EXCEPTION);
    }
}
