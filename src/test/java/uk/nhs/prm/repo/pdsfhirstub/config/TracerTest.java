package uk.nhs.prm.repo.pdsfhirstub.config;

import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import static org.assertj.core.api.Assertions.assertThat;

class TracerTest {

    @Test
    void shouldSetTraceIdInMDC() {
        var tracer = new Tracer();
        tracer.setTraceId("someId");
        assertThat(MDC.get("traceId")).isEqualTo("someId");
    }
}