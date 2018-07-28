package org.codelab.batch.test;

import java.util.List;
import java.util.stream.Collectors;

import org.codelab.batch.dao.PersonDao;
import org.codelab.batch.dto.Person;
import org.codelab.batch.mapper.PersonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@Profile("local")
@AutoConfigureTestDatabase(replace=Replace.NONE)
@MybatisTest
public class DaoTest {

	@Autowired
	private PersonMapper personMapper;
	
//	@Autowired
//	private PersonDao personDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void daoTest() {
		
		List<Person> list = personMapper.getPerson().stream().collect(Collectors.toList());
		for (Person person : list) {
			System.out.println(person.toString());
		}
		
		Person person = new Person();
		person.setFirstName("first1111");
		person.setLastName("last122222");
		int ret = personMapper.insertPerson(person);
		System.out.println(ret);
	}
}
