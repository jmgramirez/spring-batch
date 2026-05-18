package com.jmgr.app.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Antes de ejecutar el Step: " + stepExecution.getStepName());
        System.out.println("Parámetros del Step: " + stepExecution.getJobParameters());
        System.out.println("Contexto de Ejecución del Step: " + stepExecution.getExecutionContext());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Después de ejecutar el Step: " + stepExecution.getStepName());
        System.out.println("Parámetros del Step: " + stepExecution.getJobParameters());
        System.out.println("Contexto de Ejecución del Step: " + stepExecution.getExecutionContext());
        return ExitStatus.COMPLETED;
    }

}
