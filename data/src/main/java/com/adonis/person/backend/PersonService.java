package com.adonis.person.backend;

import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB to hide JPA related stuff from the UI layer.
 */
@Stateless
@NoArgsConstructor
public class PersonService {

	@Inject
	PersonRepository entryRepo;

	@PersistenceContext(unitName = "personsdb")
	EntityManager em;


	public List<Person> getEntries() {
		return entryRepo.findAll();
	}

	public Person save(Person entity) {
		return entryRepo.saveAndFlush(entity);
	}

	public void delete(Person entity) {
		// Hibernate cannot remove detached, reattach...
		entryRepo.removeAndFlush(entryRepo.findById(entity.getId()));
	}
	public List<Person> loadDataFromDb() {
		return entryRepo.findAll();
	}
	public void loadData() {

		String csvFile = "Persons.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(csvFile);
			br = new BufferedReader(new InputStreamReader(inputStream));

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");

			while ((line = br.readLine()) != null) {
				String[] person = line.split(cvsSplitBy);

				Person entry = new Person();
				// entry.setId(Long.parseLong(person[0]));
				entry.setFirstName(person[1]);
				entry.setLastName(person[2]);
				entry.setEmail(person[3]);
				//entry.setLogin(person[4]);
				try {
					entry.setDateOfBirth(sdf.parse(person[4]));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				entry.setRemind(Math.random() > 0.5);
				entry.setPicture(person[5]);
				entry.setNotes(person[6]);
                entry.setCreated(new Date());
				em.persist(entry);
				em.flush();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
    public Person findByLogin(String login){
		return entryRepo.findByLogin(login);

	}
}
