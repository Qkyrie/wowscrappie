package com.deswaef.wowscrappie.sounds.configuration;

import com.deswaef.wowscrappie.sounds.domain.SoundRepositoryEnum;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@Component
public class SoundStore {

    @Value("${com.deswaef.scrappie.soundstore}")
    private String destinationLocation;

    public Optional<File> getRandomFile(SoundRepositoryEnum soundRepo) {
        try {
            File parentFile = new File(destinationLocation + File.separator + soundRepo.getStoreValue());
            if (parentFile.isDirectory()) {
                File[] files = parentFile.listFiles((FileFilter) FileFileFilter.FILE);
                long count = files.length;
                if (count == 0) return Optional.empty();
                Random r = new Random();
                long randomIndex = count <= Integer.MAX_VALUE ? r.nextInt((int) count) :
                        r.longs(1, 0, count).findFirst().orElseThrow(AssertionError::new);
                return Arrays.asList(files)
                        .stream()
                        .skip(randomIndex)
                        .findFirst();
            }

        } catch (Exception ex) {
            return Optional.empty();
        }
        return Optional.empty();
    }
}
