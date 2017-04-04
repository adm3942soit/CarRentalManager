package com.adonis.contact;

import com.adonis.person.backend.Person;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.server.ExternalResource;

public class PersonView extends PersonDesign {

	public interface PersonSaveListener {
		void savePerson(Person person);
	}

	public interface PersonDeleteListener {
		void deletePerson(Person person);
	}

	BeanFieldGroup<Person> binder = new BeanFieldGroup<>(Person.class);

	public PersonView(PersonSaveListener saveEvt, PersonDeleteListener delEvt) {
		binder.bindMemberFields(this);

		save.addClickListener(evt -> {
			try {
				binder.commit();
				saveEvt.savePerson(binder.getItemDataSource().getBean());
			} catch (CommitException e) {
				e.printStackTrace();
			}
		});

		cancel.addClickListener(evt -> {
			binder.discard();
		});

		delete.addClickListener(evt -> {
			delEvt.deletePerson(binder.getItemDataSource().getBean());
		});
	}

	public void setPerson(Person selectedRow) {
		binder.setItemDataSource(selectedRow);

		picture.setSource(new ExternalResource(selectedRow.getPicture()));
	}

}
