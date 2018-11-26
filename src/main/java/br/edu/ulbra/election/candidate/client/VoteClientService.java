package br.edu.ulbra.election.candidate.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ulbra.election.candidate.output.v1.VoteOutput;

@Service
public class VoteClientService {
	private final VoteClient voteClient;

    @Autowired
    public VoteClientService(VoteClient voteClient){
        this.voteClient = voteClient;
    }

    public VoteOutput getByElectionId(Long id){
        return this.voteClient.getByElectionId(id);
    }

    @FeignClient(value="vote-service", url="${url.vote-service}")
    private interface VoteClient {

        @GetMapping("/v1/vote/election/{electionId}")
        VoteOutput getByElectionId(@PathVariable(name = "electionId") Long electionId);
    }
}