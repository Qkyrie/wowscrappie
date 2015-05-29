package com.deswaef.weakauras.requests.controller;

import com.deswaef.weakauras.requests.controller.dto.QuestionDto;
import com.deswaef.weakauras.requests.domain.ConfigRequest;
import com.deswaef.weakauras.requests.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/questions")
public class ConfigRequestController {

    @Autowired
    private RequestService requestService;

    @RequestMapping(method = GET)
    public String index(ModelMap modelMap, @PageableDefault Pageable pageable) {
        modelMap.put("questions", getTopQuestions(pageable));
        modelMap.put("all", requestService.findAll(pageable));
        return "questions/index";
    }

    private Page<QuestionDto> getTopQuestions(Pageable pageable) {
        Page<ConfigRequest> all = requestService.findAll(pageable);
        List<QuestionDto> collect = all.getContent()
                .stream()
                .map(QuestionDto::fromConfigRequest)
                .collect(Collectors.toList());

        return new PageImpl<>(collect, pageable, collect.size());
    }

    @RequestMapping(value = "/new", method = GET)
    public String ask() {
        return "questions/ask";
    }

}
