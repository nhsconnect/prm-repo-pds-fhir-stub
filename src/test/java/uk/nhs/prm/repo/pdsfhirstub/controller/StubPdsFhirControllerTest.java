package uk.nhs.prm.repo.pdsfhirstub.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.nhs.prm.repo.pdsfhirstub.config.Tracer;
import uk.nhs.prm.repo.pdsfhirstub.delay.DelayResponse;
import uk.nhs.prm.repo.pdsfhirstub.response.RetrievalResponse;
import uk.nhs.prm.repo.pdsfhirstub.response.UpdateResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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
    private UpdateResponse updateResponse;

    @MockBean
    private DelayResponse delayResponse;

    @MockBean
    private Tracer tracer;

    @Test
    void shouldDelayResponseAndReturnPdsRetrievalResponse() throws Exception {
        when(delayResponse.getRetrievalResponseTime()).thenReturn(0);
        when(retrievalResponse.getResponse(NHS_NUMBER)).thenReturn("retrieval-response");

        var response = mockMvc.perform(
                get("/Patient/" + NHS_NUMBER).header("traceId", "The-Trace-Id"))
                .andExpect(status().isOk()).andReturn().getResponse();

        verify(delayResponse).getRetrievalResponseTime();
        verify(tracer).setTraceId("The-Trace-Id");
        assertThat(response.getContentAsString()).isEqualTo("retrieval-response");
        assertThat(response.getHeaderValue("ETag")).isEqualTo("W/\"1\"");
    }

    @Test
    void shouldDelayResponseAndReturnPdsUpdateResponse() throws Exception {
        when(delayResponse.getUpdateResponseTime()).thenReturn(0);
        when(updateResponse.getResponse(NHS_NUMBER, 10001)).thenReturn("update-response");

        var response = mockMvc.perform(
                patch("/Patient/" + NHS_NUMBER).header("traceId", "The-Trace-Id"))
                .andExpect(status().isOk()).andReturn().getResponse();

        verify(delayResponse).getUpdateResponseTime();
        verify(tracer).setTraceId("The-Trace-Id");
        assertThat(response.getContentAsString()).isEqualTo("update-response");
        assertThat(response.getHeaderValue("ETag")).isEqualTo("W/\"1\"");
    }

    @Test
    void shouldReturn503ResponseIfDelayIsOver10Seconds() throws Exception {
        when(delayResponse.getUpdateResponseTime()).thenReturn(10001);
        when(updateResponse.getResponse(NHS_NUMBER, 10001)).thenReturn("update-response");

        var response = mockMvc.perform(
                        patch("/Patient/" + NHS_NUMBER).header("traceId", "The-Trace-Id"))
                .andExpect(status().isServiceUnavailable()).andReturn().getResponse();

        verify(delayResponse).getUpdateResponseTime();
        verify(tracer).setTraceId("The-Trace-Id");
        assertThat(response.getContentAsString()).isEqualTo("update-response");
        assertThat(response.getHeaderValue("ETag")).isEqualTo("W/\"1\"");
    }
}
