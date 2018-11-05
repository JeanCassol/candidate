package br.edu.ulbra.election.candidate.builder;

import br.edu.ulbra.election.candidate.input.v1.CandidateInput;
import br.edu.ulbra.election.candidate.model.Candidate;
import br.edu.ulbra.election.candidate.output.v1.CandidateOutput;

import java.util.Collections;
import java.util.List;

public class CandidateBuilder {

    public static CandidateOutput getCandidateOutput(){
    	CandidateOutput candidateOutput = new CandidateOutput();
        candidateOutput.setNumberElection(1L);
        candidateOutput.setName("Candidate Name");
        return candidateOutput;
    }

    public static List<CandidateOutput> getCandidaterOutputList(){
        return Collections.singletonList(getCandidateOutput());
    }

    public static CandidateInput getCandidateInput() {
    	CandidateOutput candidateOutput = getCandidateOutput();
    	CandidateInput candidateInput = new CandidateInput();
    	candidateInput.setElectionId(candidateOutput.getNumberElection());
    	candidateInput.setName(candidateOutput.getName());
        candidateInput.setNumberElection(candidateOutput.getNumberElection());
        return candidateInput;
    }

    public static Candidate getCandidate(){
    	Candidate candidate = new Candidate();
    	candidate.setElectionId(1L);
    	candidate.setName("Voter Name");
        return candidate;
    }

    public static List<Candidate> getCandidateList() {
        return Collections.singletonList(getCandidate());
    }
}
