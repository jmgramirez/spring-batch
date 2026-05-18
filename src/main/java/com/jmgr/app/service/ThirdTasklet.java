package com.jmgr.app.service;

import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

@Service
public class ThirdTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("¡Hola Mundo! Este es mi tercera tarea Spring Batch");
        return RepeatStatus.FINISHED;
    }

}
