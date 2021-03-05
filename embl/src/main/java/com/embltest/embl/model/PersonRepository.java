package com.embltest.embl.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

	List<PersonEntity> findBySsn(String ssn);
	
	long deleteBySsn(String ssn);
}
