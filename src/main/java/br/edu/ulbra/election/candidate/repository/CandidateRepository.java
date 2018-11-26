package br.edu.ulbra.election.candidate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.edu.ulbra.election.candidate.model.Candidate;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {
    Candidate findFirstByNumberElectionAndElectionId(Long numberElection, Long electionId);
    Candidate findFirstByElectionId(Long electionId);
    List<Candidate> findAllByElectionId(Long electionId);
    Candidate findFirstByPartyId(Long partyId);
}
