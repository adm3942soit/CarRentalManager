
package com.adonis.person.backend;

import java.util.List;

//@Repository
public interface PersonRepository {//extends EntityRepository<Person, Long> {

	public List<Person> findAll();

	public Person saveAndFlush(final Person person);

	public void removeAndFlush(Person person);

	public Person findById(Long id);


	public Person findByLogin(String login);
}
