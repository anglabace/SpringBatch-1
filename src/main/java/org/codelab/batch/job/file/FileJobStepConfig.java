package org.codelab.batch.job.file;

import org.apache.ibatis.session.SqlSessionFactory;
import org.codelab.batch.dto.Person;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FileJobStepConfig {

	private static final String STEP_NAME = "fileJobStep";
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean(name=STEP_NAME)
    public Step step() {
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
	public transformItemProcessor processor() {
		return new transformItemProcessor();
	}
	
	@Bean
	public ItemWriter<Person> writer() {
		MyBatisBatchItemWriter<Person> writer = new MyBatisBatchItemWriter<>();
		writer.setSqlSessionFactory(sqlSessionFactory);
		writer.setStatementId("org.codelab.batch.mapper.PersonMapper.insertPerson");
		return writer;
	}
}
