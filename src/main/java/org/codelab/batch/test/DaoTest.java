package org.codelab.batch.test;

import java.util.Iterator;
import java.util.List;

import org.codelab.batch.dao.PersonDao;
import org.codelab.batch.dto.Person;
import org.codelab.batch.mapper.PersonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@MybatisTest
public class DaoTest {

	@Autowired
	private PersonMapper personMapper;
	
	@Test
	public void daoTest() {
		List<Person> list =personMapper.getPerson();
		if(list==null) {
			System.out.println(">>>>>>>>>>>>> test");
			return;
		}
		for (Person person : list) {
			System.out.println(person.getFirstName());
		}
	}
}
