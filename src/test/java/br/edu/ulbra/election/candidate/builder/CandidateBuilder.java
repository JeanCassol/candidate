package br.edu.ulbra.election.candidate.builder;

import java.util.Collections;
import java.util.List;

import br.edu.ulbra.election.candidate.input.v1.CandidateInput;
import br.edu.ulbra.election.candidate.model.Candidate;
import br.edu.ulbra.election.candidate.output.v1.CandidateOutput;
import br.edu.ulbra.election.candidate.output.v1.ElectionOutput;
import br.edu.ulbra.election.candidate.output.v1.PartyOutput;

public class CandidateBuilder {

	public static CandidateOutput getCandidateOutput() {
		CandidateOutput candidateOutput = new CandidateOutput();
		candidateOutput.setId(1L);
		candidateOutput.setName("Candidate Name");
		candidateOutput.setNumberElection(1L);
		PartyOutput partyOutput = new PartyOutput();
		partyOutput.setId(1L);
		partyOutput.setName("Party Name");
		partyOutput.setNumber(99);
		partyOutput.setCode("RS");
		candidateOutput.setPartyOutput(partyOutput);
		ElectionOutput electionOutput = new ElectionOutput();
		electionOutput.setId(1L);
		electionOutput.setDescription("Election description");
		electionOutput.setStateCode("BR");
		electionOutput.setYear(2018);
		candidateOutput.setElectionOutput(electionOutput);
		return candidateOutput;
	}

	public static List<CandidateOutput> getCandidateOutputList() {
		return Collections.singletonList(getCandidateOutput());
	}

	public static CandidateInput getCandidateInput() {
		CandidateOutput candidateOutput = getCandidateOutput();
		CandidateInput candidateInput = new CandidateInput();
		candidateInput.setName(candidateOutput.getName());
		candidateInput.setNumberElection(candidateOutput.getNumberElection());
		candidateInput.setElectionId(candidateOutput.getElectionOutput().getId());
		candidateInput.setPartyId(candidateOutput.getPartyOutput().getId());
		return candidateInput;
	}

	public static Candidate getCandidate() {
		Candidate candidate = new Candidate();
		candidate.setId(1L);
		candidate.setName("Candidate Name");
		candidate.setNumberElection(99L);
		candidate.setElectionId(1L);
		candidate.setPartyId(1L);
		return candidate;
	}

	public static List<Candidate> getCandidateList() {
		return Collections.singletonList(getCandidate());
	}
}
