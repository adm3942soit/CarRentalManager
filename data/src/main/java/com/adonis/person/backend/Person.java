package com.adonis.person.backend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * A domain object example. In a real application this would probably be a JPA
 * entity or DTO.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "persons", schema = "")
@Getter
@Setter
@NoArgsConstructor
public class Person extends AbstractEntity {

	@Column(name = "FIRST_NAME", nullable = false)
	@NotNull(message = "First Name is required")
	@Size(min = 3, max = 40, message = "First Name must be longer than 3 and less than 40 characters")
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	@NotNull(message = "Last Name is required")
	@Size(min = 3, max = 40, message = "Last Name must be longer than 3 and less than 40 characters")
	private String lastName;

	@Column(name = "EMAIL", unique = true, nullable = false)
	@NotNull(message = "Email is required")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Must be valid email")
	private String email;

	//@NotNull(message = "Login is required")
	@Column(name = "LOGIN", nullable = true)
	private String login;

	//@NotNull(message = "Login is required")
	@Column(name = "PASSWORD", nullable = true)
	private String password;

	@Temporal(javax.persistence.TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH", nullable = true)
	private Date dateOfBirth;

	private boolean remind = false;

	@Column(name = "PICTURE", nullable = true)
	private String picture;

	@Lob
	@Column(name = "NOTES", length = 1000, nullable = true)
	private String notes;

	@Column(name = "CREATED")
	private Date created;
	@Column(name = "UPDATED")
	private Date updated;


}
