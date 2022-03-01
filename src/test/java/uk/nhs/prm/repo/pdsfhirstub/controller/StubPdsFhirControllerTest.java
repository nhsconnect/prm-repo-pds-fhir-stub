package uk.nhs.prm.repo.pdsfhirstub.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.nhs.prm.repo.pdsfhirstub.delay.DelayResponse;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(StubPdsFhirController.class)
public class StubPdsFhirControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DelayResponse delayResponse;

    @Test
    void shouldCallPdsServiceWithNhsNumberOnDemographicsRequest() throws Exception {
        when(delayResponse.getResponseTime()).thenReturn(0);
        mockMvc.perform(get("/Patient/" + "1234567890"))
                .andExpect(status().isOk());
    }
}
