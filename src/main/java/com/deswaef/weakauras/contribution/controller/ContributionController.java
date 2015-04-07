package com.deswaef.weakauras.contribution.controller;

import com.deswaef.weakauras.classes.domain.WowClass;
import com.deswaef.weakauras.classes.service.ClassService;
import com.deswaef.weakauras.contribution.controller.dto.ContributionCommand;
import com.deswaef.weakauras.contribution.controller.dto.SelectSpecDto;
import com.deswaef.weakauras.contribution.service.ContributionService;
import com.deswaef.weakauras.notifications.controller.dto.PersistentNotificationDto;
import com.deswaef.weakauras.notifications.domain.Notification;
import com.deswaef.weakauras.notifications.service.NotificationService;
import com.deswaef.weakauras.notifications.service.PersistentNotificationService;
import com.deswaef.weakauras.raids.controller.dto.RaidDto;
import com.deswaef.weakauras.raids.service.RaidService;
import com.deswaef.weakauras.security.CurrentUser;
import com.deswaef.weakauras.ui.image.ImageStore;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
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
    @Autowired
    private RaidService raidService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PersistentNotificationService persistentNotificationService;


    @RequestMapping
    public String index() {
        return "contribute/index";
    }

    @RequestMapping("/details/{category}")
    public String getDetails(ModelMap modelMap, @PathVariable("category") String category) {
        if (category.equals("Bossfight")) {
            modelMap.put("raids", raidService
                    .findAll()
                    .stream()
                    .map(RaidDto::create)
                    .collect(Collectors.toList()));
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
            if(!wowClass.get().getSlug().equals("general")) {
                collect.add(0, new SelectSpecDto().setId(0L).setName("Class Specific").setSlug("class_specific"));
            }
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
    String doUpload(@RequestBody ContributionCommand command, @CurrentUser ScrappieUser scrappieUser) {
        contributionService.contribute(command);
        persistentNotificationService.notifyAdmins(PersistentNotificationDto.create()
                .setContent(String.format("%s just did a contribution", scrappieUser.getUsername()))
                .setTitle("New Contribution!")
                .setUrl("/admin/pending-uploads"))
        ;
        return "success";
    }
}
