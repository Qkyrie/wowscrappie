package com.deswaef.wowscrappie.requests.service;

import com.deswaef.wowscrappie.requests.domain.ConfigRequest;
import com.deswaef.wowscrappie.requests.domain.ConfigRequestResponse;
import com.deswaef.wowscrappie.requests.dto.CreateQuestionDto;
import com.deswaef.wowscrappie.requests.dto.CreateResponseDto;
import com.deswaef.wowscrappie.requests.dto.QuestionResponseDto;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
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
