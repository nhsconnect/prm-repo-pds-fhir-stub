package uk.nhs.prm.repo.pdsfhirstub.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public String getPatientGpStatus(@PathVariable("nhsNumber") String nhsNumber) throws InterruptedException {
        log.info("Received PDS Retrieval request");
        var retrievalResponseTime = delayResponse.getRetrievalResponseTime();

        log.info("Delayed by " + retrievalResponseTime + "ms");
        return retrievalResponse.getResponse(nhsNumber);
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

