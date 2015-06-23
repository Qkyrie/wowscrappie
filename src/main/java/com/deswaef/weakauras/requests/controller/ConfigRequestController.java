package com.deswaef.weakauras.requests.controller;

import com.deswaef.weakauras.requests.controller.dto.CreateQuestionDto;
import com.deswaef.weakauras.requests.controller.dto.CreateResponseDto;
import com.deswaef.weakauras.requests.controller.dto.QuestionListDto;
import com.deswaef.weakauras.requests.controller.dto.SpecificQuestionDto;
import com.deswaef.weakauras.requests.domain.ConfigRequest;
import com.deswaef.weakauras.requests.domain.ConfigRequestResponse;
import com.deswaef.weakauras.requests.service.RequestService;
import com.deswaef.weakauras.security.CurrentUser;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/questions")
public class ConfigRequestController {

    public static final String PLEASE_FILL_IN_YOUR_ACTUAL_QUESTION = "Please fill in your actual question";
    public static final String PLEASE_ADD_A_TITLE = "Please add a title";
    @Autowired
    private RequestService requestService;

    @RequestMapping(method = GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String index(ModelMap modelMap, @PageableDefault Pageable pageable) {
        modelMap.put("questions", getTopQuestions(pageable));
        modelMap.put("all", requestService.findAll(pageable));
        return "questions/index";
    }

    @RequestMapping(method = GET, value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String specificQuestion(ModelMap modelMap, @PathVariable("id") Long id, @CurrentUser ScrappieUser scrappieUser) {
        Optional<ConfigRequest> byId = requestService.findById(id);
        if (byId.isPresent()) {
            modelMap.put("question", SpecificQuestionDto.fromConfigRequest(byId.get()));
            modelMap.put("canEdit", canEdit(scrappieUser, byId.get()));
            return "questions/specificquestion";
        } else {
            return "questions/specificquestion-not-found";
        }
    }

    @RequestMapping(method = GET, value = "/{id}/responses")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String responsesForQuestions(ModelMap modelMap, @PathVariable("id") Long id, @CurrentUser ScrappieUser scrappieUser) {
        Optional<ConfigRequest> byId = requestService.findById(id);
        if (byId.isPresent()) {
            modelMap.put("responses", requestService.findByConfigRequest(byId.get(), scrappieUser));
        } else {
            modelMap.put("responses", new ArrayList<>());
        }
        return "questions/includes/comments :: responses";
    }

    @RequestMapping(method = POST, value = "/{id}/comment")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public
    @ResponseBody
    CreateResponseDto
    doComment(@RequestBody CreateResponseDto createResponseDto, @PathVariable("id") Long id, @CurrentUser ScrappieUser scrappieUser) {
        Optional<ConfigRequest> byId = requestService.findById(id);
        if (!createResponseDto.getQuestionId().equals(id) || !byId.isPresent()) {
            return createResponseDto
                    .setHasErrors(true)
                    .setUserComment("Something went wrong, were tinkering with the data?");
        }
        else if (createResponseDto.getUserComment() == null && createResponseDto.getUserComment().length() <= 9) {
            return createResponseDto
                    .setHasErrors(true)
                    .setUserComment("Your comment has to consist at least out of 10 characters");
        } else {
            return requestService
                    .respond(byId.get(), createResponseDto, scrappieUser);
        }
    }

    @RequestMapping(method = POST, value = "/{questionId}/comment/{responseId}/respond")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public
    @ResponseBody
    CreateResponseDto respondToComment(@RequestBody CreateResponseDto createResponseDto,
                                       @CurrentUser ScrappieUser currentUser,
                                       @PathVariable("questionId") Long questionId,
                                       @PathVariable("responseId") Long responseId) {
        Optional<ConfigRequestResponse> responseById = requestService.findResponseById(responseId);
        if (responseById.isPresent() && responseById.get().getConfigRequest().getId().equals(questionId)) {
            return requestService.respond(responseById.get(), createResponseDto, currentUser);
        } else {
            return createResponseDto
                    .setHasErrors(true)
                    .setErrorMessage("Something went very wrong, please try again");
        }
    }

    @RequestMapping(method = GET, value = "/{questionId}/comment/{responseId}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody HttpStatus deleteComment(@CurrentUser ScrappieUser scrappieUser,
                                    @PathVariable("questionId") Long questionId,
                                    @PathVariable("responseId") Long responseId) {
        Optional<ConfigRequestResponse> responseById = requestService.findResponseById(responseId);
        if (responseById.isPresent() && responseById.get().getConfigRequest().getId().equals(questionId)) {
            if(responseById.get().getResponder().getId().equals(scrappieUser.getId()) || isAdmin()) {
                requestService.hide(responseById.get());
            }
        }
        return HttpStatus.OK;
    }

    private boolean canEdit(ScrappieUser scrappieUser, ConfigRequest configRequest) {
        if (scrappieUser != null) {
            return scrappieUser.getId().equals(configRequest.getPoster().getId()) || isAdmin();
        } else {
            return false;
        }
    }

    @RequestMapping(method = GET, value = "/{id}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editQuestion(ModelMap modelMap, @PathVariable("id") Long id, @CurrentUser ScrappieUser scrappieUser) {
        Optional<ConfigRequest> byId = requestService.findById(id);
        if (byId.isPresent() && canEdit(scrappieUser, byId.get())) {
            modelMap.put("question", CreateQuestionDto.fromConfigRequest(byId.get()));
            return "questions/editquestion";
        } else {
            return "questions/cant-edit-question";
        }
    }

    @RequestMapping(method = POST, value = "/{id}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public
    @ResponseBody
    ResponseEntity<CreateQuestionDto> doEditQuestion(@PathVariable("id") Long id,
                                                     @CurrentUser ScrappieUser scrappieUser,
                                                     @RequestBody CreateQuestionDto createQuestionDto) {
        Optional<ConfigRequest> byId = requestService.findById(id);
        if (byId.isPresent() && byId.get().getId().equals(id) && canEdit(scrappieUser, byId.get())) {
            return ResponseEntity.ok(requestService.updateRequest(createQuestionDto));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }


    private Page<QuestionListDto> getTopQuestions(Pageable pageable) {
        Page<ConfigRequest> all = requestService.findAll(pageable);
        List<QuestionListDto> collect = all.getContent()
                .stream()
                .map(QuestionListDto::fromConfigRequest)
                .collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, collect.size());
    }

    @RequestMapping(value = "/new", method = GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String ask() {
        return "questions/ask";
    }

    @RequestMapping(value = "/new", method = POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public
    @ResponseBody
    ResponseEntity<CreateQuestionDto> askNewQuestion(@RequestBody CreateQuestionDto createQuestionDto, @CurrentUser ScrappieUser scrappieUser) {
        if (createQuestionDto.getTitle() == null || createQuestionDto.getTitle().trim().isEmpty()) {
            return ResponseEntity.ok(createQuestionDto.setErrorMessage(PLEASE_ADD_A_TITLE).setHasErrors(true));
        }
        if (createQuestionDto.getQuestion() == null || createQuestionDto.getQuestion().trim().isEmpty()) {
            return ResponseEntity.ok(createQuestionDto.setErrorMessage(PLEASE_FILL_IN_YOUR_ACTUAL_QUESTION).setHasErrors(true));
        }
        return ResponseEntity.ok(requestService.createNewRequest(scrappieUser, createQuestionDto));
    }

    boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        } else {
            return (authentication.getAuthorities()
                    .stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));
        }
    }


}
