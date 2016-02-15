package com.deswaef.heureka.wowuction.continuous;

import com.deswaef.heureka.wowuction.client.model.Entry;
import com.deswaef.heureka.wowuction.continuous.dto.AuctionHouseSnapshotHolder;
import com.deswaef.heureka.wowuction.continuous.processing.EntryToAuctionHouseSnapshotProcessor;
import com.deswaef.heureka.wowuction.continuous.writing.WowuctionSnapshotWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class WowuctionBatchConfiguration {

    public static final int CHUNK_SIZE = 50;

    @Bean(name = "import-wowuction")
    public Job importAuctionSnapshotsJob(
            JobBuilderFactory jobs,
            @Qualifier("wowuction-import-start-step") Step startStep,
            @Qualifier("import-wowuction-step") Step importStep) {
        return jobs.get("import-wowuction-job")
                .incrementer(new RunIdIncrementer())
                .start(startStep)
                .next(importStep)
                .build();
    }

    @Bean(name = "wowuction-import-start-step")
    public Step wowuctionImportStartStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory
                .get("wowuction-import-start-step")
                .tasklet((stepContribution, chunkContext) -> RepeatStatus.FINISHED).build();
    }

    @Bean(name = "import-wowuction-step")
    public Step importStep(StepBuilderFactory stepBuilderFactory,
                           EntryToAuctionHouseSnapshotProcessor processor,
                           WowuctionSnapshotWriter writer,
                           @Qualifier("import-wowuction-data-reader") ItemReader itemReader) throws Exception {
        return stepBuilderFactory
                .get("import-wowuction-step")
                .<Entry, AuctionHouseSnapshotHolder>chunk(CHUNK_SIZE)
                .reader(itemReader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
