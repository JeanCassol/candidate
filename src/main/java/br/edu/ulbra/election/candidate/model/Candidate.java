package br.edu.ulbra.election.candidate.model;

import javax.persistence.*;

/*
 *  id integer identity primary key,
  name varchar(255) not null,
  number integer not null,
  election_id integer not null,
  party_id integer not null
  
 */
@Entity
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer number;

	@Column(nullable = false, name = "election_id")
	private Long electionId;

	@Column(nullable = false, name = "party_id")
	private Integer partyId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}
}
