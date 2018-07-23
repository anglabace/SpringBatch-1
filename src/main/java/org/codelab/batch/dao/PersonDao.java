package org.codelab.batch.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.codelab.batch.dto.Person;
import org.codelab.batch.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {
	
	@Autowired
	private PersonMapper personMapper;
	
	public List<Person> getPersons() {
		return personMapper.getPerson().stream().collect(Collectors.toList());
	}
}
