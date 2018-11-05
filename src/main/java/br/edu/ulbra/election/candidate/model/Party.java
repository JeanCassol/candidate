package br.edu.ulbra.election.candidate.model;

import javax.persistence.*;

/*
 id integer identity primary key,
  code varchar(10) not null,
  name varchar(255) not null,
  number integer not null
 */
@Entity
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 10, name = "code")
	private Long code;

	@Column(nullable = false, length = 255, name = "name")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	private Integer number;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
