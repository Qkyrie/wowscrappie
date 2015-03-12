package com.deswaef.weakauras.ui.image;

import com.deswaef.weakauras.ui.image.repository.ScreenshotRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;
import java.util.Random;

@Component
public class ImageStore {

    private Log logger = LogFactory.getLog(ImageStore.class);

    @Autowired
    private ScreenshotRepository screenshotRepository;

    @Value("${com.deswaef.scrappie.imagestore}")
    private String destinationLocation;

    public String storeFile(MultipartFile multipartFile) {
        try {
            String reference = getReference(multipartFile);
            FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(getOutputFilename(reference)));

            BufferedImage thumbnail = Scalr.resize(ImageIO.read(new File(getOutputFilename(reference))), 125);

            String thumbNailReference = String.format("thumbnail-%s", reference);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            String fileType = getFileType(thumbNailReference);
            ImageIO.write(thumbnail, fileType, baos);
            byte[] thumbnailBytes = baos.toByteArray();
            FileCopyUtils.copy(thumbnailBytes, new FileOutputStream(getOutputFilename(thumbNailReference)));
            return reference;
        } catch (IOException e) {
            return "";
        }
    }

    private String getFileType(String thumbNailReference) {
        String[] split = thumbNailReference.split("\\.");
        return split[split.length - 1];
    }

    public Optional<File> getImageByName(String name) {
        File f = new File(destinationLocation + File.separator + name);
        if (f.exists()) {
            return Optional.of(f);
        } else {
            return Optional.empty();
        }
    }

    @Async
    public void deleteOldEntries() {
        File imageStorePath = new File(destinationLocation);
        final Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        FileUtils.listFiles(imageStorePath, null, true)
                .stream()
                .filter(file -> FileUtils.isFileOlder(file, yesterday.getTime()))
                .filter(file -> notPresentInRepository(file) && thumbNailNotInRepository(file))
                .forEach(
                        file -> {
                            try {
                                FileUtils.forceDelete(file);
                            } catch (IOException e) {
                                logger.error(String.format("couldnt remove %s", file.getAbsolutePath()));
                            }
                        }
                );
    }

    private boolean thumbNailNotInRepository(File file) {
        if (file.getName().startsWith("thumbnail")) {
            String substring = file.getName().substring(10);
            return !screenshotRepository.findByReference(substring).isPresent();
        } else {
            return false;
        }
    }

    private boolean notPresentInRepository(File file) {
        return !screenshotRepository.findByReference(file.getName()).isPresent();
    }

    private String getOutputFilename(String reference) {
        return String.format("%s"+File.separator+"%s", destinationLocation, reference);
    }

    private String getReference(MultipartFile multipartFile) {
        String[] split = multipartFile.getOriginalFilename().split("\\.");
        Random randomGenerator = new Random();
        int i = randomGenerator.nextInt(10000);
        return String.format("%s.%s", getHash(String.format(String.valueOf(System.currentTimeMillis()), String.valueOf(i)), "SHA1"), split[split.length - 1]);
    }

    public String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            //error action
        }
        return null;
    }

    public String sha1(String txt) {
        return getHash(txt, "SHA1");
    }
}
