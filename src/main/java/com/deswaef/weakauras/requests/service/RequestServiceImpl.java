package com.deswaef.weakauras.requests.service;

import com.deswaef.weakauras.requests.controller.dto.CreateQuestionDto;
import com.deswaef.weakauras.requests.domain.ConfigRequest;
import com.deswaef.weakauras.requests.repository.ConfigRequestRepository;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * User: Quinten
 * Date: 27-5-2015
 * Time: 21:08
 *
 * @author Quinten De Swaef
 */
@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private ConfigRequestRepository configRequestRepository;

    @Override
    public Page<ConfigRequest> findAll(Pageable pageable) {
        return configRequestRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ConfigRequest> findById(Long id) {
        return configRequestRepository.findOne(id);
    }

    @Override
    @Transactional
    public CreateQuestionDto createNewRequest(ScrappieUser scrappieUser, CreateQuestionDto createQuestionDto) {
        try {
            Date now = new Date();
            ConfigRequest save = configRequestRepository.save(
                    new ConfigRequest()
                            .setTitle(createQuestionDto.getTitle())
                            .setDeleted(false)
                            .setLastEditDate(now)
                            .setOriginalPostDate(now)
                            .setPoster(scrappieUser)
                            .setQuestion(createQuestionDto.getQuestion())
            );
            return createQuestionDto.setId(save.getId());
        } catch (Exception ex) {
            return createQuestionDto
                    .setHasErrors(true)
                    .setErrorMessage("Unable to create your question, please make sure you have a title and an actual question");
        }
    }

    @Override
    @Transactional
    public CreateQuestionDto updateRequest(CreateQuestionDto createQuestionDto) {
        try {
            Optional<ConfigRequest> one = configRequestRepository.findOne(createQuestionDto.getId());
            if (one.isPresent()) {
                Date now = new Date();
                configRequestRepository.save(
                        one.get()
                                .setLastEditDate(now)
                                .setQuestion(createQuestionDto.getQuestion())
                );
                return createQuestionDto;
            } else {
                return createQuestionDto
                        .setHasErrors(true)
                        .setErrorMessage("Unable to create your question, please make sure you have a title and an actual question");
            }
        } catch (Exception ex) {
            return createQuestionDto
                    .setHasErrors(true)
                    .setErrorMessage("Unable to create your question, please make sure you have a title and an actual question");
        }
    }
}
