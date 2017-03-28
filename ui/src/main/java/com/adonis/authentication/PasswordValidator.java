package com.adonis.authentication;

import com.adonis.person.backend.Person;
import com.adonis.person.backend.PersonService;
import com.vaadin.data.validator.AbstractValidator;

/**
 * Created by oksdud on 28.03.2017.
 */
public class PasswordValidator extends AbstractValidator<String> {
        String userName;
        PersonService personService;

        public PasswordValidator(PersonService personService, String userName) {
            super("The password provided is not valid");
            this.userName = userName;
            this.personService = personService;
        }

        @Override
        protected boolean isValidValue(String value) {
            // Password must be at least 8 characters long and contain at least
            // one number
            if (value != null
                    && (value.length() < 8 || !value.matches(".*\\d.*"))) {
                return false;
            }

            Person person  =  personService.findByLogin(this.userName);

            if (person==null) return false;

            if(person.getPassword().equals(value)) return true;

            return false;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }

}
