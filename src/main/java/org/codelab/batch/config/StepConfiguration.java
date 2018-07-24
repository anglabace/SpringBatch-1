package org.codelab.batch.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.codelab.batch.dto.Person;
import org.codelab.batch.job.PersonItemProcessor;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.batch.core.Step;

@Configuration
public class StepConfiguration {

	private static final String STEP_NAME = "Step";
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
    public Step Step() {
        return stepBuilderFactory.get(STEP_NAME)
            .<Person, Person>chunk(1)
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .build();
    }
	
	@Bean
	public FlatFileItemReader<Person> reader() {
		return new FlatFileItemReaderBuilder<Person>()
				.name("personItemReader")
				.resource(new ClassPathResource("sample-data.csv"))
				.delimited()
				.names(new String[] {"firstName","lastName"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
					setTargetType(Person.class);
				}}).build();
	}
	
	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}
	
	@Bean
	public ItemWriter<Person> writer() {
		MyBatisBatchItemWriter<Person> writer = new MyBatisBatchItemWriter<>();
		writer.setSqlSessionFactory(sqlSessionFactory);
		writer.setStatementId("org.codelab.batch.mapper.PersonMapper.insertPerson");
		return writer;
	}
}
