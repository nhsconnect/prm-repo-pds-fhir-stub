package uk.nhs.prm.repo.pdsfhirstub.config;

import lombok.NoArgsConstructor;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
public class Tracer {

    public void setTraceId(String traceId) {
        MDC.put("traceId", traceId);
    }
}
