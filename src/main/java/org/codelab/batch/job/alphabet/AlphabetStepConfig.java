package org.codelab.batch.job.alphabet;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlphabetStepConfig {

	private static final String STEP_NAME = "alphabetStep";
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean(name=STEP_NAME)
    public Step step() {
		return stepBuilderFactory.get(STEP_NAME)
				.<String,String>chunk(1)
				.reader(new AlphabetReader())
				.processor(new UpperCaseProcessor())
				.writer(new AlphabetWriter())
				.build();
	}
}
