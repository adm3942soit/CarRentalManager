package com.adonis.person.backend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * A domain object example. In a real application this would probably be a JPA
 * entity or DTO.
 */
@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person extends AbstractEntity {

	@NotNull(message = "First Name is required")
	@Size(min = 3, max = 40, message = "First Name must be longer than 3 and less than 40 characters")
	private String firstName;

	@NotNull(message = "Last Name is required")
	@Size(min = 3, max = 40, message = "Last Name must be longer than 3 and less than 40 characters")
	private String lastName;

	@NotNull(message = "Email is required")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Must be valid email")
	private String email;

	//@NotNull(message = "Login is required")
	private String login;

	//@NotNull(message = "Login is required")
	private String password;

	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateOfBirth;

	private boolean remind = false;

	private String picture;

	@Lob
	@Column(length = 1000)
	private String notes;

	private Date created;
	private Date updated;


}
