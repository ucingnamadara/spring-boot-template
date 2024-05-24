package com.dana.springboottemplate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AuditData {

	@Id
	@GenericGenerator(name = "user-uuid", strategy = "uuid2")
	@GeneratedValue(generator = "user-uuid")
	@Column(name = "id", length = 36, nullable = false, updatable = false, unique = true)
	private String id;

	@Column(name = "email", length = 100)
	private String email;

	@Column(name = "phoneNumber", length = 20)
	private String phoneNumber;

	@Column(name = "password", nullable = false)
	private String password;

}
