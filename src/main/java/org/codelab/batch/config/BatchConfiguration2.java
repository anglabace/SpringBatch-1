package org.codelab.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration2 {

	private static final String JOB_NAME = "job1010";
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	private Step stepName;

	@Bean(name = JOB_NAME)
	public Job job() {
		return jobBuilderFactory.get(JOB_NAME).start(stepName).build();
	}
}
