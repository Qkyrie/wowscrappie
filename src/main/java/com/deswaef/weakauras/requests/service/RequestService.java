package com.deswaef.weakauras.requests.service;

import com.deswaef.weakauras.requests.controller.dto.CreateQuestionDto;
import com.deswaef.weakauras.requests.domain.ConfigRequest;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * User: Quinten
 * Date: 27-5-2015
 * Time: 21:05
 *
 * @author Quinten De Swaef
 */
public interface RequestService {
    Page<ConfigRequest> findAll(Pageable pageable);
    Optional<ConfigRequest> findById(Long id);
    CreateQuestionDto createNewRequest(ScrappieUser scrappieUser, CreateQuestionDto createQuestionDto);

    CreateQuestionDto updateRequest(CreateQuestionDto createQuestionDto);
}
