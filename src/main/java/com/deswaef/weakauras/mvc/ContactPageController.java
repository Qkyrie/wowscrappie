package com.deswaef.weakauras.mvc;

import com.deswaef.weakauras.messaging.service.PrivateMessageService;
import com.deswaef.weakauras.mvc.dto.ContactRequestDto;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/contact")
public class ContactPageController {

    @Autowired
    private PrivateMessageService privateMessageService;

    @RequestMapping(method = GET)
    public String index() {
        return "contact";
    }

    @RequestMapping(method = POST)
    public
    @ResponseBody
    ContactRequestDto doRequest(@RequestBody ContactRequestDto contactRequestDto) {
        if(Strings.isNullOrEmpty(contactRequestDto.getTitle())) {
            return contactRequestDto
                    .setHasErrors(true)
                    .setErrorMessage("Please fill in a title");
        } else if (Strings.isNullOrEmpty(contactRequestDto.getContent())) {
            return contactRequestDto
                    .setHasErrors(true)
                    .setErrorMessage("Please fill in your message");
        } else {
            try {
                privateMessageService.sendToPika(contactRequestDto);
                return contactRequestDto;
            } catch (Exception ex) {
                return contactRequestDto
                        .setHasErrors(true)
                        .setErrorMessage(ex.getMessage());
            }
        }
    }

}
