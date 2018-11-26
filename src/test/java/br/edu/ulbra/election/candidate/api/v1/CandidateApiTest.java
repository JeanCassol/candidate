package br.edu.ulbra.election.candidate.api.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import br.edu.ulbra.election.candidate.TestConfig;
import br.edu.ulbra.election.candidate.builder.CandidateBuilder;
import br.edu.ulbra.election.candidate.output.v1.GenericOutput;
import br.edu.ulbra.election.candidate.service.CandidateService;

@RunWith(SpringRunner.class)
@WebMvcTest(CandidateApi.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
public class CandidateApiTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidateService candidateService;

    private final Gson gson = new Gson();
    private String URL_BASE = "/v1/candidate/";

    @Test
    public void getAll() throws Exception {
        given(candidateService.getAll())
                .willReturn(CandidateBuilder.getCandidateOutputList());

        mockMvc.perform(get(URL_BASE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo("Candidate Name")))
                .andExpect(jsonPath("$[0].numberElection", equalTo(99)));
    }

    @Test
    public void getOne() throws Exception{
        given(candidateService.getById(anyLong()))
            .willReturn(CandidateBuilder.getCandidateOutput());
        mockMvc.perform(get(URL_BASE + "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Candidate Name")))
                .andExpect(jsonPath("$.numberElection", equalTo(99)));
    }

    @Test
    public void create() throws Exception{
        given(candidateService.create(any()))
                .willReturn(CandidateBuilder.getCandidateOutput());

        mockMvc.perform(post(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(CandidateBuilder.getCandidateInput()))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Candidate Name")))
                .andExpect(jsonPath("$.numberElection", equalTo(99)));
    }

    @Test
    public void update() throws Exception{
        given(candidateService.update(anyLong(), any()))
                .willReturn(CandidateBuilder.getCandidateOutput());

        mockMvc.perform(put(URL_BASE + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(CandidateBuilder.getCandidateInput()))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Candidate Name")))
                .andExpect(jsonPath("$.numberElection", equalTo(99)));
    }

    @Test
    public void deleteVoter() throws Exception{
        given(candidateService.delete(anyLong()))
                .willReturn(new GenericOutput("OK"));

        mockMvc.perform(delete(URL_BASE + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(CandidateBuilder.getCandidateInput()))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", equalTo("OK")));
    }

}
