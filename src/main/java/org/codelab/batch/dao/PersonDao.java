package org.codelab.batch.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.codelab.batch.dto.Person;
import org.codelab.batch.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {
	
//	@Autowired
//	private SqlSession session;
	
	@Autowired
	private PersonMapper personMapper;
//	
	public List<Person> getPersons() {
		return personMapper.getPerson().stream().collect(Collectors.toList());
	}
//	
//	public int insertPerson(Person person) {
//		return personMapper.insertPerson(person);
//	}
	
	public int insertPerson(Person person) {
		//return session.insert("org.codelab.batch.mapper.PersonMapper.insertPerson", person);
		return personMapper.insertPerson(person);
	}
}
