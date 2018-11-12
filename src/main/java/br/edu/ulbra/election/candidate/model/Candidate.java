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
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, name = "number")
	private Long numberElection;

	@Column(nullable = false, name = "election_id")
	private Long electionId;

	@Column(nullable = false, name = "party_id")
	private Long partyId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNumberElection() {
		return numberElection;
	}

	public void setNumberElection(Long numberElection) {
		this.numberElection = numberElection;
	}

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

}
