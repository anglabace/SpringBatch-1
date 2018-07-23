package org.codelab.batch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.codelab.batch.dto.Person;

@Mapper
public interface PersonMapper {
	
	@Select("SELECT * FROM person")
	List<Person> getPerson();
}
