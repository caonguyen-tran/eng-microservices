package com.engapp.ReadingQuizService.service.implement;

import com.engapp.ReadingQuizService.dto.request.update.QuestionSetUpdateRequest;
import com.engapp.ReadingQuizService.exception.ApplicationException;
import com.engapp.ReadingQuizService.exception.ErrorCode;
import com.engapp.ReadingQuizService.pojo.QuestionSet;
import com.engapp.ReadingQuizService.repository.QuestionSetRepository;
import com.engapp.ReadingQuizService.service.QuestionSetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class QuestionSetServiceImplement implements QuestionSetService {
    @Autowired
    private QuestionSetRepository questionSetRepository;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public QuestionSet createQuestionSet(QuestionSet questionSet) {
        questionSet.setCreatedDate(Instant.now());
        questionSet.setUpdatedDate(Instant.now());
        return this.questionSetRepository.save(questionSet);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<QuestionSet> getAllQuestionSets() {
        return this.questionSetRepository.findAll();
    }

    @Override
    public QuestionSet getQuestionSetById(int id) {
        return this.questionSetRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_EXIST));
    }

    @Override
    public List<QuestionSet> getQuestionSetByReadingPartAndYearOf(int readingPart, int yearOf) {
        return this.questionSetRepository.filterByReadingPartAndYearOf(readingPart, yearOf);
    }

    @Override
    public List<QuestionSet> getQuestionSetByReadingPart(int readingPart) {
        return this.questionSetRepository.filterByReadingPart(readingPart);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public QuestionSet updateQuestionSet(QuestionSetUpdateRequest questionSetUpdateRequest) {
        QuestionSet questionSet = this.getQuestionSetById(questionSetUpdateRequest.getId());
        questionSet.setUpdatedDate(Instant.now());
        questionSet.setName(questionSetUpdateRequest.getNameUpdate());
        questionSet.setDescription(questionSetUpdateRequest.getDescriptionUpdate());
        questionSet.setReadingPart(questionSetUpdateRequest.getReadingPart().getKey());
        questionSet.setYearOf(questionSetUpdateRequest.getYearOfUpdate());
        return this.questionSetRepository.save(questionSet);
    }
}
