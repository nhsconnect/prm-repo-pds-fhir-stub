package uk.nhs.prm.repo.pdsfhirstub.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.nhs.prm.repo.pdsfhirstub.config.Tracer;
import uk.nhs.prm.repo.pdsfhirstub.delay.DelayResponse;
import uk.nhs.prm.repo.pdsfhirstub.response.RetrievalResponse;
import uk.nhs.prm.repo.pdsfhirstub.response.UpdateResponse;

@Slf4j
@AllArgsConstructor
@RestController
public class StubPdsFhirController {
    private DelayResponse delayResponse;
    private RetrievalResponse retrievalResponse;
    private UpdateResponse updateResponse;
    private Tracer tracer;

    @GetMapping("Patient/{nhsNumber}")
    public ResponseEntity<String> getPatientGpStatus(@PathVariable("nhsNumber") String nhsNumber, @RequestHeader(value = "traceId", required = false) String traceId) throws InterruptedException {
        tracer.setTraceId(traceId);
        log.info("Received PDS Retrieval request");
        var requestDelay = delayResponse.getRetrievalResponseTime();

        log.info("Retrieval Request processed with delay " + requestDelay + "ms");

        return generateResponse(retrievalResponse.getResponse(nhsNumber));
    }

    @PatchMapping("Patient/{nhsNumber}")
    public ResponseEntity<String> updatePatientGpStatus(@PathVariable("nhsNumber") String nhsNumber, @RequestHeader(value = "traceId", required = false) String traceId) throws InterruptedException {
        tracer.setTraceId(traceId);
        log.info("Received PDS Update request");
        var requestDelay = delayResponse.getUpdateResponseTime();

        log.info("Update Request processed with delay " + requestDelay + "ms");
        return generateResponse(updateResponse.getResponse(nhsNumber));
    }

    private ResponseEntity<String> generateResponse(String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setETag("W/\"1\"");
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }
}

