package com.deswaef.weakauras.requests.controller;

import com.deswaef.weakauras.infrastructure.service.OnRoleDependable;
import com.deswaef.weakauras.requests.controller.dto.CreateQuestionDto;
import com.deswaef.weakauras.requests.controller.dto.QuestionListDto;
import com.deswaef.weakauras.requests.controller.dto.SpecificQuestionDto;
import com.deswaef.weakauras.requests.domain.ConfigRequest;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/questions")
public class ConfigRequestController {

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
    public @ResponseBody ResponseEntity<CreateQuestionDto> doEditQuestion(@PathVariable("id") Long id,
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
    public @ResponseBody ResponseEntity<CreateQuestionDto> askNewQuestion(@RequestBody CreateQuestionDto createQuestionDto, @CurrentUser ScrappieUser scrappieUser) {
        return ResponseEntity.ok(requestService.createNewRequest(scrappieUser, createQuestionDto));
    }

    boolean isAdmin(){
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
