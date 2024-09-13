package com.engapp.QuizService.service.implement;

import com.engapp.QuizService.dto.request.update.QuestionSetUpdateRequest;
import com.engapp.QuizService.exception.ApplicationException;
import com.engapp.QuizService.exception.ErrorCode;
import com.engapp.QuizService.pojo.QuestionSet;
import com.engapp.QuizService.repository.QuestionSetRepository;
import com.engapp.QuizService.service.QuestionSetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
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
    public List<QuestionSet> getQuestionSetByParams(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<QuestionSet> pagedResult = this.questionSetRepository.findAll(pageable);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        }
        else{
            return new ArrayList<QuestionSet>();
        }
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
        questionSet.setReadingPart(questionSetUpdateRequest.getReadingPart());
        questionSet.setYearOf(questionSetUpdateRequest.getYearOfUpdate());
        return this.questionSetRepository.save(questionSet);
    }

    @Override
    public List<QuestionSet> getQuestionSetByName(String name) {
        return this.questionSetRepository.filterByName(name);
    }

    @Override
    public void deleteQuestionSetById(int id) {
        this.questionSetRepository.deleteById(id);
    }
}
