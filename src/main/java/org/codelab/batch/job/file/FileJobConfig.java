package org.codelab.batch.job.file;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class FileJobConfig {

	private static final String JOB_NAME = "fileJob";
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	private Step fileJobStep;

	@Bean(name = JOB_NAME)
	public Job job() {
		return jobBuilderFactory.get(JOB_NAME).incrementer(new RunIdIncrementer()).start(fileJobStep).build();
	}
}
