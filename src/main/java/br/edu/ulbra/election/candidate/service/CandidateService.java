package br.edu.ulbra.election.candidate.service;

import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ulbra.election.candidate.exception.GenericOutputException;
import br.edu.ulbra.election.candidate.input.v1.CandidateInput;
import br.edu.ulbra.election.candidate.model.Candidate;
import br.edu.ulbra.election.candidate.output.v1.CandidateOutput;
import br.edu.ulbra.election.candidate.output.v1.GenericOutput;
import br.edu.ulbra.election.candidate.repository.CandidateRepository;

@Service
public class CandidateService {

	private final CandidateRepository candidateRepository;

	private final ModelMapper modelMapper;

	private static final String MESSAGE_INVALID_ID = "Invalid id";
	private static final String MESSAGE_CANDIDATE_NOT_FOUND = "Candidate not found";

	@Autowired
	public CandidateService(CandidateRepository candidateRepository, ModelMapper modelMapper) {
		this.candidateRepository = candidateRepository;
		this.modelMapper = modelMapper;
	}

	public List<CandidateOutput> getAll() {
		Type candidateOutputListType = new TypeToken<List<CandidateOutput>>() {
		}.getType();
		return modelMapper.map(candidateRepository.findAll(), candidateOutputListType);
	}

	public CandidateOutput create(CandidateInput candidateInput) {
		validateInput(candidateInput, false);
		Candidate candidate = modelMapper.map(candidateInput, Candidate.class);
		candidate = candidateRepository.save(candidate);
		return modelMapper.map(candidate, CandidateOutput.class);
	}

	public CandidateOutput getById(Long candidateId) {
		if (candidateId == null) {
			throw new GenericOutputException(MESSAGE_INVALID_ID);
		}

		Candidate candidate = candidateRepository.findById(candidateId).orElse(null);
		if (candidate == null) {
			throw new GenericOutputException(MESSAGE_CANDIDATE_NOT_FOUND);
		}

		return modelMapper.map(candidate, CandidateOutput.class);
	}

	public CandidateOutput update(Long candidateId, CandidateInput candidateInput) {
		if (candidateId == null) {
			throw new GenericOutputException(MESSAGE_INVALID_ID);
		}
		validateInput(candidateInput, true);

		Candidate candidate = candidateRepository.findById(candidateId).orElse(null);
		if (candidate == null) {
			throw new GenericOutputException(MESSAGE_CANDIDATE_NOT_FOUND);
		}

		candidate.setName(candidateInput.getName());
		candidate.setNumberElection(candidateInput.getNumberElection());
		candidate.setPartyId(candidateInput.getPartyId());
		candidate.setElectionId(candidateInput.getElectionId());

		candidate = candidateRepository.save(candidate);
		return modelMapper.map(candidate, CandidateOutput.class);
	}

	public GenericOutput delete(Long candidateId) {
		if (candidateId == null) {
			throw new GenericOutputException(MESSAGE_INVALID_ID);
		}

		Candidate candidate = candidateRepository.findById(candidateId).orElse(null);
		if (candidate == null) {
			throw new GenericOutputException(MESSAGE_CANDIDATE_NOT_FOUND);
		}

		candidateRepository.delete(candidate);

		return new GenericOutput("Candidate deleted");
	}

	private void validateInput(CandidateInput candidateInput, boolean isUpdate) {
		String[] names = candidateInput.getName().split(" ");
		if (StringUtils.isBlank(candidateInput.getName()) ||
				candidateInput.getName().length() < 3 ||
				names.length > 1) {
			throw new GenericOutputException("Invalid Name");
		}
		
		if (candidateInput.getNumberElection().equals(0)) {
			throw new GenericOutputException("Invalid Number Election");
		}
		
		if (candidateInput.getPartyId().equals(0)) {
			throw new GenericOutputException("Invalid Party Id");
		}
		
		if (candidateInput.getElectionId().equals(0)) {
			throw new GenericOutputException("Invalid Election Id");
		}
	}

}
