package com.epam.learning.library.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString(callSuper=true, exclude="password")
@NoArgsConstructor
@Entity
@Table(name="user")
@Indexed
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username", unique=true)
	@Field
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email", unique=true)
	@Field
	private String email;
	
	@Column(name="enable")
	private boolean enable;
	
	@Column(name="first_name")
	@Field
	private String firstName;
	
	@Column(name="last_name")
	@Field
	private String lastName;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="birthday")
	private Date birthday;
	
	@Column(name="address")
	private String address;

	@Column(name="postal")
	private String postalCode;
	
	@Column(name="city")
	private String city;
	
	@Column(name="record_created")
	private Timestamp recordCreated;

	@ManyToMany(fetch=FetchType.EAGER,
				cascade= {CascadeType.DETACH, CascadeType.MERGE,
						CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
				name="user_role",
				joinColumns=@JoinColumn(name="user_id"),
				inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;
	
	@OneToMany(	mappedBy="user",
				cascade= {CascadeType.DETACH, CascadeType.MERGE,
						CascadeType.PERSIST, CascadeType.REFRESH},
				fetch=FetchType.LAZY)
	private Set<Reserved> reservedBooks; 
	
	public User(String username, String password, String email, boolean enable, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.enable = enable;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void addRole(Role role){
		
		if (roles == null){
			roles = new HashSet<Role>();
		}
		
		roles.add(role);
	}
}
