package br.edu.ulbra.election.candidate.model;

import javax.persistence.*;

@Entity
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false, name="party_id")
	private Integer partyId;

	@Column(nullable = false, name="")
	private Integer numberElection;

	@Column(nullable = false, name="election_id")
	private Long electionId;

	public String name() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer partyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public Integer numberElection() {
		return numberElection;
	}

	public void setNumberElection(Integer numberElection) {
		this.numberElection = numberElection;
	}

	public Long electionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

}
