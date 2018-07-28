package org.codelab.batch.job.parallelstep;

import org.codelab.batch.common.Const;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class ParallelJobConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	@Qualifier(Const.STEP_PARALLEL_1)
	private Step step1;

	@Autowired
	@Qualifier(Const.STEP_ALPHABET)
	private Step step2;
	
	@Autowired
	@Qualifier(Const.STEP_READFILE)
	private Step step3;

	@Bean(name = Const.JOB_PARALLEL)
	public Job job() {
		return jobBuilderFactory.get(Const.JOB_PARALLEL).incrementer(new RunIdIncrementer()).start(step1)
				.split(new SimpleAsyncTaskExecutor()).add(flow2()).next(step3).end().build();
	}

	@Bean
	public Flow flow2() {
		return new FlowBuilder<Flow>("flow2").start(step2).build();
	}
}
