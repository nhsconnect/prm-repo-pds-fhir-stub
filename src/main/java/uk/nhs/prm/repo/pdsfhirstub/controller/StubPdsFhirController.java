package uk.nhs.prm.repo.pdsfhirstub.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("Patient/{nhsNumber}")
    public ResponseEntity<String> getPatientGpStatus(@PathVariable("nhsNumber") String nhsNumber) throws InterruptedException {
        log.info("Received PDS Retrieval request");
        var retrievalResponseTime = delayResponse.getRetrievalResponseTime();

        log.info("Delayed by " + retrievalResponseTime + "ms");
        HttpHeaders headers = new HttpHeaders();
        headers.add("ETag", "W/1");
        return new ResponseEntity<>(
                retrievalResponse.getResponse(nhsNumber), headers, HttpStatus.OK);
    }

    @PatchMapping("Patient/{nhsNumber}")
    @ResponseStatus(HttpStatus.OK)
    public String updatePatientGpStatus(@PathVariable("nhsNumber") String nhsNumber) {
        log.info("Received PDS Update request");
        var updateResponseTime = delayResponse.getUpdateResponseTime();

        log.info("Delayed by " + updateResponseTime + "ms");
        return updateResponse.getResponse(nhsNumber);
    }
}

