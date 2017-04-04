package com.adonis.login;

import com.adonis.person.backend.Person;
import com.adonis.person.backend.PersonService;
import com.google.common.collect.Lists;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by oksdud on 29.03.2017.
 */
@Stateless
public class DataApplication  {
    @Inject
    public PersonService personService;
    public List<Person> personList = Lists.newArrayList();
    @PostConstruct
    public void constructData(){
        personService.loadData();
        personList = personService.loadDataFromDb();

    }

    public PersonService getPersonService() {
        return personService;
    }

    public List<Person> getPersonList() {
        return personList;
    }
}
