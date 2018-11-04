package br.edu.ulbra.election.candidate.model;

import javax.persistence.*;

@Entity
public class Candidate {

	
	
	@Column(nullable = false)
    private String name;

	@Column(nullable = false)
	private Integer partyId;
	
	@Column(nullable = false)
	private Integer numberElection;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
