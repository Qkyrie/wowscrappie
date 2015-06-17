package com.deswaef.weakauras.requests.service;

import com.deswaef.weakauras.requests.controller.dto.CreateQuestionDto;
import com.deswaef.weakauras.requests.controller.dto.CreateResponseDto;
import com.deswaef.weakauras.requests.controller.dto.QuestionResponseDto;
import com.deswaef.weakauras.requests.domain.ConfigRequest;
import com.deswaef.weakauras.requests.domain.ConfigRequestResponse;
import com.deswaef.weakauras.requests.repository.ConfigRequestRepository;
import com.deswaef.weakauras.requests.repository.ConfigRequestResponseRepository;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * User: Quinten
 * Date: 27-5-2015
 * Time: 21:08
 *
 * @author Quinten De Swaef
 */
@Service
public class RequestServiceImpl implements RequestService {

    public static final Predicate<ConfigRequestResponse> ROOT_RESPONSES = x -> x.getInResponseTo() == null;
    private static final PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);

    @Autowired
    private ConfigRequestRepository configRequestRepository;
    @Autowired
    private ConfigRequestResponseRepository configRequestResponseRepository;

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

    @Transactional(readOnly = true)
    @Override
    public List<QuestionResponseDto> findByConfigRequest(ConfigRequest configRequest, ScrappieUser scrappieUser) {
        List<ConfigRequestResponse> allResponses = configRequestResponseRepository.findByConfigRequest(configRequest);
        return allResponses
                .stream()
                .filter(ROOT_RESPONSES)
                .map(transformRequests(scrappieUser))
                .map(getResponsesForResponse(allResponses, scrappieUser))
                .collect(Collectors.toList());
    }

    private Function<ConfigRequestResponse, QuestionResponseDto> transformRequests(ScrappieUser scrappieUser) {
        return x -> new QuestionResponseDto()
                .setId(x.getId())
                .setPoster(x.getResponder().getUsername())
                .setPosterId(x.getResponder().getId())
                .setDate(x.getLastEditDate())
                .setDeleted(x.isDeleted())
                .setPrettyDate(prettyTime.format(x.getLastEditDate()))
                .setResponse(x.getResponse())
                .setOwn(isOwn(x, scrappieUser));
    }

    private boolean isOwn(ConfigRequestResponse x, ScrappieUser scrappieUser) {
        return (scrappieUser !=  null && scrappieUser.getId().equals(x.getResponder().getId()));
    }

    @Override
    @Transactional
    public CreateResponseDto respond(ConfigRequest configRequest, CreateResponseDto createResponseDto, ScrappieUser scrappieUser) {
        try {
            configRequestResponseRepository.save(
                    new ConfigRequestResponse()
                            .setConfigRequest(configRequest)
                            .setDeleted(false)
                            .setInResponseTo(null)
                            .setLastEditDate(now())
                            .setOriginalPostDate(now())
                            .setResponder(scrappieUser)
                            .setResponse(createResponseDto.getUserComment())
            );
            return createResponseDto;
        } catch (Exception ex) {
            return createResponseDto
                    .setHasErrors(true)
                    .setErrorMessage("Unable to respond to this question. Please try again later");
        }
    }

    @Transactional
    @Override
    public CreateResponseDto respond(ConfigRequestResponse configRequestResponse, CreateResponseDto createResponseDto, ScrappieUser scrappieUser) {
        try {
            configRequestResponseRepository.save(
                    new ConfigRequestResponse()
                            .setConfigRequest(configRequestResponse.getConfigRequest())
                            .setDeleted(false)
                            .setInResponseTo(configRequestResponse)
                            .setLastEditDate(now())
                            .setOriginalPostDate(now())
                            .setResponder(scrappieUser)
                            .setResponse(createResponseDto.getUserComment())
            );
            return createResponseDto;
        } catch (Exception ex) {
            return createResponseDto
                    .setHasErrors(true)
                    .setErrorMessage("Unable to respond to this comment. Please try again later");
        }
    }

    @Override
    public Optional<ConfigRequestResponse> findResponseById(Long id) {
        return configRequestResponseRepository.findOne(id);
    }

    @Override
    @Transactional
    public void hide(ConfigRequestResponse configRequestResponse) {
        configRequestResponseRepository.save(configRequestResponse.setDeleted(true));
    }

    private Function<QuestionResponseDto, QuestionResponseDto> getResponsesForResponse(List<ConfigRequestResponse> allResponses, ScrappieUser scrappieUser) {
        return x -> x.setResponses(mapResponsesToResponse(x.getId(), allResponses, scrappieUser));
    }


    private List<QuestionResponseDto> mapResponsesToResponse(Long responseId, List<ConfigRequestResponse> allResponses, ScrappieUser scrappieUser) {
        return allResponses
                .stream()
                .filter(x -> x.getInResponseTo() != null)
                .filter(x -> x.getInResponseTo().getId().equals(responseId))
                .map(
                        transformRequests(scrappieUser)
                )
                .map(getResponsesForResponse(allResponses, scrappieUser))
                .collect(Collectors.toList());
    }

    private Date now() {
        return new Date();
    }
}
