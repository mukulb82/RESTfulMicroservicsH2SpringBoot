package com.embltest.embl.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.embltest.embl.exception.ManagePersonException;
import com.embltest.embl.model.PersonEntity;
import com.embltest.embl.model.PersonRepository;

import vo.Person;

@Component
public class PersonService implements IPersonService {

	@Autowired
	PersonRepository repository;
	
	private Logger logger = Logger.getLogger(PersonService.class);

	@Override
	public boolean createPersons(List<Person> persons) throws ManagePersonException {

		try {

			for (Person person : persons) {

				repository.save(new PersonEntity(person.getSsn(), person.getFirstName(), person.getLastName(), person.getAge(),
						person.getFavouriteColor()));
			}

		} catch (Exception ex) {
			logger.error("exception occured in saving during DAO layer interactions", ex);
			throw new ManagePersonException(ex);
			
		}
		

		return true;
	}

	@Override
	public List<Person> fetchPersons() throws ManagePersonException {
		
		try {			
			List<PersonEntity> personEntities =	repository.findAll();		
			
			List<Person> persons = convertToResponse(personEntities);
			return persons;
			
					
		} catch (Exception ex) {
			logger.error("exception occured in fetching persons during DAO layer interaction ", ex);
			throw new ManagePersonException(ex);
			
		}
		
		
	}

	private List<Person> convertToResponse(List<PersonEntity> personEntities) {
		
		List<Person> persons = new ArrayList<>();
		
		for(PersonEntity entity : personEntities) {
			Person person = new Person();
			person.setSsn(entity.getSsn());
			person.setFirstName(entity.getFirstName());
			person.setLastName(entity.getLastName());			
			person.setAge(entity.getAge());
			person.setFavouriteColor(entity.getFavouriteColor());
			
			persons.add(person);
		}
		return persons;
	}

	@Override
	public boolean updatePerson(Person person) {
		
		// get the person which needs to be updated by fetching through ssn
		
		try {			
			PersonEntity personEntity =	(PersonEntity) repository.findBySsn(person.getSsn()).get(0);	
			
			if(personEntity == null) {
				logger.info("Person not found");
			}
			
			if (personEntity != null) {
				
				PersonEntity newPerson = new PersonEntity();
				newPerson.setSsn(person.getSsn());
				newPerson.setFirstName(person.getFirstName());
				newPerson.setLastName(person.getLastName());
				newPerson.setFavouriteColor(person.getFavouriteColor());
				newPerson.setAge(person.getAge());
				
				repository.save(newPerson);
				
				return true;
				
			}	else {
				
				return false;
			}
			
					
		} catch (Exception ex) {
			logger.error("exception occured in updating person during DAO layer interaction ", ex);
			throw new ManagePersonException(ex);
			
		}
		
		
		
		
	}

	@Override
	public long deletePerson(String ssn) {
		try {
			long deletedRecordCnt = repository.deleteBySsn(ssn);
			return deletedRecordCnt;
		} catch (Exception e) {
			logger.error("exception occured in deleting person during DAO layer interaction ", e);
			throw new ManagePersonException(e);
			
		}
	}

}
