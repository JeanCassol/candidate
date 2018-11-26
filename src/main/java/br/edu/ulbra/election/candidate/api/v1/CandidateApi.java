package br.edu.ulbra.election.candidate.api.v1;

import br.edu.ulbra.election.candidate.input.v1.CandidateInput;
import br.edu.ulbra.election.candidate.output.v1.CandidateOutput;
import br.edu.ulbra.election.candidate.output.v1.GenericOutput;
import br.edu.ulbra.election.candidate.service.CandidateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/candidate")
public class CandidateApi {

    private final CandidateService candidateService;

    @Autowired
    public CandidateApi(CandidateService candidateService){
        this.candidateService = candidateService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get candidates List")
    public List<CandidateOutput> getAll(){
        return candidateService.getAll();
    }

    @GetMapping("/party/{partyId}")
    @ApiOperation(value = "Get candidate by party Id")
    public CandidateOutput getByIdParty(@PathVariable Long partyId){
        return candidateService.getByPartyId(partyId);
    }

    @GetMapping("/election/{electionId}")
    @ApiOperation(value = "Get candidate by election Id")
    public CandidateOutput getByIdElection(@PathVariable Long electionId){
        return candidateService.getByElectionId(electionId);
    }
    
    @GetMapping("all/election/{electionId}")
    @ApiOperation(value = "Get All candidate by election Id")
    public List<CandidateOutput> getAllByIdElection(@PathVariable Long electionId){
        return candidateService.getAllByElectionId(electionId);
    }

	@GetMapping("/{candidateId}")
	@ApiOperation(value = "Get candidate Id")
	public CandidateOutput getById(@PathVariable Long candidateId) {
		return candidateService.getById(candidateId);
	}
    
    @PostMapping("/")
    @ApiOperation(value = "Create new candidate")
    public CandidateOutput create(@RequestBody CandidateInput candidateInput){
        return candidateService.create(candidateInput);
    }

    @PutMapping("/{candidateId}")
    @ApiOperation(value = "Update candidate")
    public CandidateOutput update(@PathVariable Long candidateId, @RequestBody CandidateInput candidateInput){
        return candidateService.update(candidateId, candidateInput);
    }

    @DeleteMapping("/{candidateId}")
    @ApiOperation(value = "Delete candidate")
    public GenericOutput delete(@PathVariable Long candidateId){
        return candidateService.delete(candidateId);
    }
}
