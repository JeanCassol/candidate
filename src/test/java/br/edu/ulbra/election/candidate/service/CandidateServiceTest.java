package br.edu.ulbra.election.candidate.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.ulbra.election.candidate.TestConfig;
import br.edu.ulbra.election.candidate.builder.CandidateBuilder;
import br.edu.ulbra.election.candidate.input.v1.CandidateInput;
import br.edu.ulbra.election.candidate.output.v1.CandidateOutput;
import br.edu.ulbra.election.candidate.output.v1.GenericOutput;
import br.edu.ulbra.election.candidate.repository.CandidateRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(CandidateService.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
public class CandidateServiceTest {

	@MockBean
	private CandidateRepository candidateRepository;

	@Autowired
	private CandidateService candidateService;

	@Test
	public void shouldReturnEmptyList() {
		given(candidateRepository.findAll()).willReturn(new ArrayList<>());
		List<CandidateOutput> candidateOutputList = candidateService.getAll();
		Assert.assertEquals(0, candidateOutputList.size());
	}

	@Test
	public void shouldFindAllElements() {
		given(candidateRepository.findAll()).willReturn(CandidateBuilder.getCandidateList());
		List<CandidateOutput> candidateOutputList = candidateService.getAll();
		Assert.assertEquals(1, candidateOutputList.size());
	}

	@Test
	public void shouldGetById() {
		given(candidateRepository.findById(anyLong())).willReturn(Optional.of(CandidateBuilder.getCandidate()));
		CandidateOutput candidateOutput = candidateService.getById(1L);
		Assert.assertEquals((Long) 1L, candidateOutput.getId());
	}

	@Test
	public void shouldCreate() {
		CandidateInput candidateInput = CandidateBuilder.getCandidateInput();
		given(candidateRepository.save(any())).willReturn(CandidateBuilder.getCandidate());
		CandidateOutput candidateOutput = candidateService.create(candidateInput);
		Assert.assertEquals(candidateInput.getName(), candidateOutput.getName());
		Assert.assertEquals(candidateInput.getNumberElection(), candidateOutput.getNumberElection());
		Assert.assertEquals(candidateInput.getElectionId(), candidateOutput.getElectionOutput().getId());
		Assert.assertEquals(candidateInput.getPartyId(), candidateOutput.getPartyOutput().getId());
	}

	@Test
	public void shouldUpdate() {
		CandidateInput candidateInput = CandidateBuilder.getCandidateInput();
		given(candidateRepository.findById(anyLong())).willReturn(Optional.of(CandidateBuilder.getCandidate()));
		given(candidateRepository.save(any())).willReturn(CandidateBuilder.getCandidate());
		CandidateOutput candidateOutput = candidateService.update(1L, candidateInput);
		Assert.assertEquals(candidateInput.getName(), candidateOutput.getName());
		Assert.assertEquals(candidateInput.getNumberElection(), candidateOutput.getNumberElection());
		Assert.assertEquals(candidateInput.getElectionId(), candidateOutput.getElectionOutput().getId());
		Assert.assertEquals(candidateInput.getPartyId(), candidateOutput.getPartyOutput().getId());
	}

	@Test
	public void shouldDelete() {
		given(candidateRepository.findById(anyLong())).willReturn(Optional.of(CandidateBuilder.getCandidate()));
		doNothing().when(candidateRepository).delete(any());
		GenericOutput genericOutput = candidateService.delete(1L);
		Assert.assertEquals("Candidate deleted", genericOutput.getMessage());
	}
}
