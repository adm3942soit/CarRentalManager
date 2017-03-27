package com.adonis.person.backend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * A domain object example. In a real application this would probably be a JPA
 * entity or DTO.
 */
@SuppressWarnings("serial")
@Entity()
@Getter
@Setter
@NoArgsConstructor
public class Person extends AbstractEntity {

	@Column(name = "FIRST_NAME")
	@NotNull(message = "First Name is required")
	@Size(min = 3, max = 40, message = "First Name must be longer than 3 and less than 40 characters")
	private String firstName;

	@Column(name = "LAST_NAME")
	@NotNull(message = "Last Name is required")
	@Size(min = 3, max = 40, message = "Last Name must be longer than 3 and less than 40 characters")
	private String lastName;

	@Column(name = "EMAIL", unique = true)
	@NotNull(message = "Email is required")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Must be valid email")
	private String email;

	//@NotNull(message = "Login is required")
	@Column(name = "LOGIN")
	private String login;

	//@NotNull(message = "Login is required")
	@Column(name = "PASSWORD")
	private String password;

	@Temporal(javax.persistence.TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;

	private boolean remind = false;

	@Column(name = "PICTURE", unique = true)
	private String picture;

	@Lob
	@Column(name = "NOTES", length = 1000)
	private String notes;

	@Column(name = "CREATED")
	private Date created;
	@Column(name = "UPDATED")
	private Date updated;


}
