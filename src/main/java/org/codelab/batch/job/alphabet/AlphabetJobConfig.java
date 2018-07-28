package org.codelab.batch.job.alphabet;

import org.codelab.batch.common.Const;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class AlphabetJobConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	@Qualifier(Const.STEP_ALPHABET)
	private Step step;

	@Bean(name=Const.JOB_ALPHABET)
	public Job job() {
		return jobBuilderFactory.get(Const.JOB_ALPHABET)
				.incrementer(new RunIdIncrementer())
				.start(step).build();
	}
}
