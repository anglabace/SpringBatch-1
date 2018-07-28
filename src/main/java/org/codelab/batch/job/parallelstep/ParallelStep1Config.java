package org.codelab.batch.job.parallelstep;

import org.codelab.batch.common.Const;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParallelStep1Config {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean(name=Const.STEP_PARALLEL_1)
	public Step step() {
		return stepBuilderFactory.get(Const.STEP_PARALLEL_1)
				.<Integer,Integer>chunk(1)
				.reader(new NumberReader())
				.processor(new NumberProcessor())
				.build();
	}
}
