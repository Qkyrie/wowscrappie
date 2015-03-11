package com.deswaef.weakauras.contribution.controller;

import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.classes.service.ClassService;
import com.deswaef.weakauras.contribution.controller.dto.ContributionCommand;
import com.deswaef.weakauras.contribution.controller.dto.SelectSpecDto;
import com.deswaef.weakauras.contribution.service.ContributionService;
import com.deswaef.weakauras.ui.image.ImageStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/contribute")
public class ContributionController {

    @Autowired
    private ClassService classService;
    @Autowired
    private ImageStore imageStore;
    @Autowired
    private ContributionService contributionService;

    @RequestMapping
    public String index() {
        return "contribute/index";
    }

    @RequestMapping("/details/{category}")
    public String getDetails(ModelMap modelMap, @PathVariable("category") String category) {
        if (category.equals("Bossfight")) {
            return "contribute/fragments/details :: bossfight";
        }
        else if(category.equals("ClassSpec")) {
            modelMap.put("wowclasses", classService.findAll());
            return "contribute/fragments/details :: classpec";
        }
        else {
            return "contribute/fragments/details :: 404";
        }
    }

    @RequestMapping("/details/{category}/{wowclass}/specs")
    public
    @ResponseBody
    List<SelectSpecDto> getSpecs(@PathVariable("category") String category, @PathVariable("wowclass") String wowclass) {
        Optional<WowClass> wowClass = classService.bySlug(wowclass);
        if (wowClass.isPresent()) {
            List<SelectSpecDto> collect = wowClass.get().getSpecs()
                    .stream()
                    .map(SelectSpecDto::fromSpec)
                    .collect(Collectors.toList());
            collect.add(0, new SelectSpecDto().setId(0L).setName("Class Specific").setSlug("class_specific"));
            return collect;
        } else {
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/cachescreenshot", method = RequestMethod.POST)
    public @ResponseBody
    List<String> upload(MultipartHttpServletRequest request) throws IOException {
        // Getting uploaded files from the request object
        Map<String, MultipartFile> fileMap = request.getFileMap();
        return fileMap.values()
                .stream()
                .map(imageStore::storeFile)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/do-contribution", method = POST)
    public
    @ResponseBody
    String doUpload(@RequestBody ContributionCommand command) {
        contributionService.contribute(command);
        return "success";
    }
}
