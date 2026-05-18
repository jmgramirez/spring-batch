package com.jmgr.app.listener;

import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Antes de ejecutar el Job: " + jobExecution.getJobInstance().getJobName());
        System.out.println("Parámetros del Job: " + jobExecution.getJobParameters());
        System.out.println("Contexto de Ejecución del Job: " + jobExecution.getExecutionContext());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Después de ejecutar el Job: " + jobExecution.getJobInstance().getJobName());
        System.out.println("Parámetros del Job: " + jobExecution.getJobParameters());
        System.out.println("Contexto de Ejecución del Job: " + jobExecution.getExecutionContext());
    }

}
