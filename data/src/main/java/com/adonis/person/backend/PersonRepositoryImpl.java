package com.adonis.person.backend;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Oxana on 05.03.2017.
 */
public class PersonRepositoryImpl implements PersonRepository
{

    @PersistenceContext(unitName = "personsdb")
    EntityManager em;

    @Override
    public List<Person> findAll() {
        return em.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Override
    public Person saveAndFlush(Person person) {
        Person original = findById(person.getId());
        person.setId(original.getId());
        em.merge(person);
        em.flush();
      return person;
    }

    @Override
    public void removeAndFlush(Person person) {
        em.remove(person);
    }

    @Override
    public Person findById(Long id) {
        return em.find(Person.class, id);
    }

    @Override
    public Person findByLogin(String login) {
        return em.createQuery("select p from Person p where p.login = '"+login +"'", Person.class ).getSingleResult();
    }
}
