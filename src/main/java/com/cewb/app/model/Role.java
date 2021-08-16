package com.cewb.app.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@JsonIgnore
	@ManyToMany(targetEntity = User.class, mappedBy = "roles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<User> users = new HashSet<>();

	public Role(String name) {
		this.name = name;
	}
}
