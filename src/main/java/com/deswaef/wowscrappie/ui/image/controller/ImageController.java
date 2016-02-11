package com.deswaef.wowscrappie.ui.image.controller;

import com.deswaef.wowscrappie.ui.image.ImageStore;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/shared/image")
public class ImageController {

    @Autowired
    private ImageStore imageStore;

    @RequestMapping(value = "/{reference:.+}", method = RequestMethod.GET)
    public @ResponseBody byte[] getImage(@PathVariable("reference") String reference) {
        Optional<File> imageByName = imageStore.getImageByName(reference);
        if (imageByName.isPresent()) {
            try {
                return IOUtils.toByteArray(new FileInputStream(imageByName.get()));
            } catch (IOException e) {
                return null;
            }
        } else {
            return null;
        }
    }



}

