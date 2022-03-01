package uk.nhs.prm.repo.pdsfhirstub.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.nhs.prm.repo.pdsfhirstub.delay.DelayResponse;
import uk.nhs.prm.repo.pdsfhirstub.response.RetrievalResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(StubPdsFhirController.class)
public class StubPdsFhirControllerTest {

    @Autowired
    private MockMvc mockMvc;

    String NHS_NUMBER = "1234567890";

    @MockBean
    private RetrievalResponse retrievalResponse;

    @MockBean
    private DelayResponse delayResponse;

    @Test
    void shouldCallPdsServiceWithNhsNumberOnDemographicsRequest() throws Exception {
        when(delayResponse.getResponseTime()).thenReturn(0);
        when(retrievalResponse.getResponse(NHS_NUMBER)).thenReturn("response");

        var contentAsString = mockMvc.perform(get("/Patient/" + NHS_NUMBER))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertThat(contentAsString).isEqualTo("response");
    }
}
