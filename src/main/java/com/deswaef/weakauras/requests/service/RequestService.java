package com.deswaef.weakauras.requests.service;

import com.deswaef.weakauras.requests.controller.dto.CreateQuestionDto;
import com.deswaef.weakauras.requests.controller.dto.CreateResponseDto;
import com.deswaef.weakauras.requests.controller.dto.QuestionResponseDto;
import com.deswaef.weakauras.requests.domain.ConfigRequest;
import com.deswaef.weakauras.requests.domain.ConfigRequestResponse;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RequestService {
    Page<ConfigRequest> findAll(Pageable pageable);
    Optional<ConfigRequest> findById(Long id);

    @Transactional
    void incrementViews(ConfigRequest configRequest);

    CreateQuestionDto createNewRequest(ScrappieUser scrappieUser, CreateQuestionDto createQuestionDto);
    CreateQuestionDto updateRequest(CreateQuestionDto createQuestionDto);
    List<QuestionResponseDto> findByConfigRequest(ConfigRequest configRequest, ScrappieUser scrappieUser);
    CreateResponseDto respond(ConfigRequest configRequest, CreateResponseDto createResponseDto, ScrappieUser scrappieUser);
    CreateResponseDto respond(ConfigRequestResponse configRequestResponse, CreateResponseDto createResponseDto, ScrappieUser scrappieUser);
    Optional<ConfigRequestResponse> findResponseById(Long id);

    void hide(ConfigRequestResponse configRequestResponse);
}
