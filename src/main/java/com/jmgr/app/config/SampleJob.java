package com.jmgr.app.config;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.jmgr.app.listener.FirstJobListener;
import com.jmgr.app.listener.FirstStepListener;
import com.jmgr.app.processor.FirstItemProcessor;
import com.jmgr.app.reader.FirstItemReader;
import com.jmgr.app.service.ThirdTasklet;
import com.jmgr.app.writer.FirstItemWritter;

@Configuration
public class SampleJob {

    @Autowired
    private ThirdTasklet thirdTasklet;

    @Autowired
    private FirstJobListener firstJobListener;

    @Autowired
    private FirstStepListener firstStepListener;

    @Autowired
    private FirstItemReader firstItemReader;

    @Autowired
    private FirstItemProcessor firstItemProcessor;

    @Autowired
    private FirstItemWritter firstItemWritter;

    //@Bean
    public Job helloWorldJob(JobRepository jobRepository, Step helloWorldStep) {
        return new JobBuilder("helloWorldJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(helloWorldStep)
                .next(secondStep(jobRepository, secondTasklet()))
                .next(thirdStep(jobRepository, thirdTasklet))
                .listener(firstJobListener)
                .build();
    }

    @Bean
    public Step helloWorldStep(JobRepository jobRepository, 
                               PlatformTransactionManager transactionManager,
                               Tasklet helloWorldTasklet) {
        return new StepBuilder("helloWorldStep", jobRepository)
                .tasklet(helloWorldTasklet, transactionManager)
                .listener(firstStepListener)
                .build();
    }

    @Bean
    public Tasklet helloWorldTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("¡Hola Mundo! Este es mi primer Job de Spring Batch");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step secondStep(JobRepository jobRepository, 
                               /*PlatformTransactionManager transactionManager,*/
                               Tasklet secondTasklet) {
        return new StepBuilder("Second Step", jobRepository)
                               .tasklet(secondTasklet/* ,transactionManager*/)
                .build();
    }

    @Bean
    public Tasklet secondTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("¡Hola Mundo! Este es mi segunda tarea Spring Batch");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step thirdStep(JobRepository jobRepository, 
                               Tasklet thirdTasklet) {
        return new StepBuilder("Third Step", jobRepository)
                               .tasklet(thirdTasklet/* ,transactionManager*/)
                .build();
    }


    @Bean
    public Job SecondJobOrientedChunks(JobRepository jobRepository, Step helloWorldStep) {
        return new JobBuilder("SecondJobOrientedChunks", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(chunkStep(jobRepository))
                .next(helloWorldStep)
                .build();
    }

    public Step chunkStep(JobRepository jobRepository) {
        return new StepBuilder("Chunk Step", jobRepository)
                .<Integer, String>chunk(3)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWritter)
                .build();
    }


}