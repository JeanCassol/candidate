package br.edu.ulbra.election.candidate.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

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
/*
    @Test
    public void shouldReturnEmptyList(){
        given(voterRepository.findAll())
                .willReturn(new ArrayList<>());
        List<CandidateOutput> voterOutputList = voterService.getAll();
        Assert.assertEquals(0, voterOutputList.size());
    }

    @Test
    public void shouldFindAllElements(){
        given(voterRepository.findAll())
                .willReturn(VoterBuilder.getVoterList());
        List<VoterOutput> voterOutputList = voterService.getAll();
        Assert.assertEquals(1, voterOutputList.size());
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailGetByIdNotFound(){
        given(voterRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        voterService.getById(1L);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailGetByIdNull(){
        given(voterRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        voterService.getById(null);
    }

    @Test
    public void shouldGetById(){
        given(voterRepository.findById(anyLong()))
                .willReturn(Optional.of(CandidateBuilder.getCandidate()));
        CandidateOutput candidateOutput = voterService.getById(1L);
        Assert.assertEquals((Long)1L, candidateOutput.getNumberElection());
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailCreateInvalidEmail(){
    	CandidateInput candidateInput = CandidateBuilder.getcandidateInput();
    	candidateInput.setName(null);
        voterService.create(candidateInput);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailCreateEmptyEmail(){
        VoterInput voterInput = CandidateBuilder.getVoterInput();
        voterInput.setEmail("");
        voterService.create(voterInput);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailCreateInvalidName(){
        VoterInput voterInput = CandidateBuilder.getVoterInput();
        voterInput.setName(null);
        voterService.create(voterInput);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailCreateEmptyName(){
        VoterInput voterInput = CandidateBuilder.getVoterInput();
        voterInput.setName("");
        voterService.create(voterInput);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailCreateEmptyPassword(){
        VoterInput voterInput = CandidateBuilder.getVoterInput();
        voterInput.setPassword("");
        voterService.create(voterInput);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailCreatePasswordsDoesntMatch(){
        VoterInput voterInput = CandidateBuilder.getVoterInput();
        voterInput.setPassword("123");
        voterService.create(voterInput);
    }
*/
    
    @Test
    public void shouldCreate(){
        CandidateInput candidateInput = CandidateBuilder.getCandidateInput();
        given(candidateRepository.save(any()))
                .willReturn(CandidateBuilder.getCandidate());
        CandidateOutput candidateOutput = candidateService.create(candidateInput);
        Assert.assertEquals(candidateInput.getName(), candidateOutput.getName());
        Assert.assertEquals(candidateInput.getElectionId(), candidateOutput.getNumberElection());
    }
/*
    @Test(expected = GenericOutputException.class)
    public void shouldFailUpdateVoterIdNull(){
        voterService.update(null, null);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailUpdateInvalidEmail(){
        VoterInput voterInput = CandidateBuilder.getVoterInput();
        voterInput.setEmail(null);
        voterService.update(1L, voterInput);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailUpdateEmptyEmail(){
        VoterInput voterInput = CandidateBuilder.getVoterInput();
        voterInput.setEmail("");
        voterService.update(1L, voterInput);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailUpdateInvalidName(){
        VoterInput voterInput = CandidateBuilder.getVoterInput();
        voterInput.setName(null);
        voterService.update(1L, voterInput);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailUpdateEmptyName(){
        VoterInput voterInput = CandidateBuilder.getVoterInput();
        voterInput.setName("");
        voterService.update(1L, voterInput);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailUpdateEmptyPassword(){
        VoterInput voterInput = CandidateBuilder.getVoterInput();
        voterInput.setPasswordConfirm("");
        voterService.update(1L, voterInput);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailUpdatePasswordsDoesntMatch(){
        VoterInput voterInput = CandidateBuilder.getVoterInput();
        voterInput.setPassword("123");
        voterService.update(1L, voterInput);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailUpdateIdNotFound(){
        given(voterRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        voterService.update(1L, CandidateBuilder.getVoterInput());
    }

    @Test
    public void shouldUpdateWithoutPassword(){
        VoterInput voterInput = CandidateBuilder.getVoterInput();
        voterInput.setPassword(null);
        given(voterRepository.findById(anyLong()))
                .willReturn(Optional.of(CandidateBuilder.getVoter()));
        given(voterRepository.save(any()))
                .willReturn(CandidateBuilder.getVoter());

        VoterOutput voterOutput = voterService.update(1L, voterInput);
        Assert.assertEquals(voterInput.getName(), voterOutput.getName());
        Assert.assertEquals(voterInput.getEmail(), voterOutput.getEmail());
    }
*/
    
    @Test
    public void shouldUpdate(){
    	CandidateInput candidateInput = CandidateBuilder.getCandidateInput();
        given(candidateRepository.findById(anyLong()))
                .willReturn(Optional.of(CandidateBuilder.getCandidate()));
        given(candidateRepository.save(any()))
                .willReturn(CandidateBuilder.getCandidate());
        CandidateOutput candidateOutput = candidateService.update(1L, candidateInput);
        Assert.assertEquals(candidateInput.getName(), candidateOutput.getName());
        Assert.assertEquals(candidateInput.getElectionId(), candidateOutput.getNumberElection());
    }
/*
    @Test(expected = GenericOutputException.class)
    public void shouldFailDeleteVoterIdNull(){
        voterService.delete(null);
    }

    @Test(expected = GenericOutputException.class)
    public void shouldFailDeleteIdNotFound(){
        given(voterRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        voterService.delete(1L);
    }
*/
    
    @Test
    public void shouldDelete(){
        given(candidateRepository.findById(anyLong()))
                .willReturn(Optional.of(CandidateBuilder.getCandidate()));
        doNothing().when(candidateRepository).delete(any());
        GenericOutput genericOutput = candidateService.delete(1L);
        Assert.assertEquals("Voter deleted", genericOutput.getMessage());
    }


}
