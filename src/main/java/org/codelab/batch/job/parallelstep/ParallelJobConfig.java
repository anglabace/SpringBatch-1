package org.codelab.batch.job.parallelstep;

import org.codelab.batch.common.Const;
import org.codelab.batch.job.alphabet.AlphabetReader;
import org.codelab.batch.job.alphabet.UpperCaseProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private Step stepReadfile;

	@Bean(name = Const.JOB_PARALLEL)
	public Job job() {
		return jobBuilderFactory.get(Const.JOB_PARALLEL)
				.incrementer(new RunIdIncrementer())
				.start(stepParallel1())
				.split(new SimpleAsyncTaskExecutor())
				.add(flow2()).next(stepReadfile).end().build();
	}
	
	@Bean
	public Step stepParallel1() {
		return stepBuilderFactory.get("stepParallel1")
				.<Integer,Integer>chunk(1)
				.reader(new NumberReader())
				.processor(new NumberProcessor())
				.build();
	}
	
	public Step stepParallel2() {
		return stepBuilderFactory.get("stepParallel2")
				.<String,String>chunk(1)
				.reader(new AlphabetReader())
				.processor(new UpperCaseProcessor())
				.build();
	}

	@Bean
	public Flow flow2() {
		return new FlowBuilder<Flow>("flow2").start(stepParallel2()).build();
	}
}
