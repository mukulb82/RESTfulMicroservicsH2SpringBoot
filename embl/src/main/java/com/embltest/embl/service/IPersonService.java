package com.embltest.embl.service;

import java.util.List;

import com.embltest.embl.exception.ManagePersonException;

import vo.Person;

public interface IPersonService {

	public boolean createPersons(List <Person> persons) throws ManagePersonException;

	public List<Person> fetchPersons() throws ManagePersonException;

	public boolean updatePerson(Person person);

	public long deletePerson(String ssn);
}
