package com.embltest.embl.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.embltest.embl.exception.ManagePersonException;
import com.embltest.embl.service.IPersonService;

import vo.ApiResponse;
import vo.Person;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	IPersonService service;

	private Logger logger = Logger.getLogger(PersonController.class);

	@PostMapping
	public ResponseEntity<ApiResponse> createPersons(@RequestBody List<Person> persons) {

		ApiResponse response = null;

		try {
			if (validatePersons(persons)) {

				boolean isSuccess = service.createPersons(persons);

				if (isSuccess) {
					response = new ApiResponse("200", "Persons saved successfully");
					return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
				} else {
					return new ResponseEntity<ApiResponse>(HttpStatus.INTERNAL_SERVER_ERROR);

				}

			} else {
				return new ResponseEntity<ApiResponse>(HttpStatus.BAD_REQUEST);
			}

		} catch (ManagePersonException mpe) {

			logger.error("Exception occured while saving persons", mpe);
			response = new ApiResponse("500", "Internal server error occured");
			return new ResponseEntity<ApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping
	public ResponseEntity<?> fetchPersons() {

		ApiResponse response = null;

		try {

			List<Person> persons = service.fetchPersons();

			if (persons != null && !persons.isEmpty()) {
				response = new ApiResponse("200", "Persons fetched successfully");
				return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
			} else {
				return new ResponseEntity<ApiResponse>(HttpStatus.NO_CONTENT);

			}

		} catch (ManagePersonException mpe) {

			logger.error("Exception occured while saving persons", mpe);
			// response = new ApiResponse("500", "Internal server error
			// occured");
			// return new ResponseEntity<ApiResponse>(response,
			// HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return null;

	}

	@PutMapping
	public ResponseEntity<?> updatePerson(@RequestBody Person person) {

		ApiResponse response = null;

		try {
			if (person != null) {
				boolean isUpdated = service.updatePerson(person);

				if (isUpdated) {
					response = new ApiResponse("200", "Persons fetched successfully");
					return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
				} else {
					return new ResponseEntity<ApiResponse>(HttpStatus.NO_CONTENT);

				}
			} else {

				return new ResponseEntity<ApiResponse>(HttpStatus.BAD_REQUEST);
			}

		} catch (ManagePersonException mpe) {

			logger.error("Exception occured while saving persons", mpe);
		
		}
		return null;

	}
	
	
	@DeleteMapping("{ssn}")
	public ResponseEntity<?> deletePerson(@RequestBody @PathVariable("ssn") String ssn) {

		ApiResponse response = null;

		try {
			if (ssn != null) {
				long deletedCntRecord = service.deletePerson(ssn);

				if (deletedCntRecord > 0) {
					response = new ApiResponse("200", "Persons deleted successfully");
					return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
				} else {
					return new ResponseEntity<ApiResponse>(HttpStatus.NO_CONTENT);

				}
			} else {

				return new ResponseEntity<ApiResponse>(HttpStatus.BAD_REQUEST);
			}

		} catch (ManagePersonException mpe) {

			logger.error("Exception occured while saving persons", mpe);
		
		}
		return null;

	}
	
	

	public ApiResponse handleResponse() {

		return new ApiResponse();
	}

	private boolean validatePersons(List<Person> persons) {

		if (persons != null && !persons.isEmpty()) {

			return true;

		} else {

			return false;
		}

	}
}
