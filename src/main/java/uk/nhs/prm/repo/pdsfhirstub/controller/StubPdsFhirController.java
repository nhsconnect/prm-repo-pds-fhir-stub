package uk.nhs.prm.repo.pdsfhirstub.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.nhs.prm.repo.pdsfhirstub.delay.DelayResponse;
import uk.nhs.prm.repo.pdsfhirstub.pdspatchrequest.PdsPatchRequest;
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
        var requestDelay = delayResponse.getRetrievalResponseTime();

        log.info("Retrieval Request processed with delay " + requestDelay + "ms");

        return generateResponse(retrievalResponse.getResponse(nhsNumber), HttpStatus.OK);
    }

    @PatchMapping("Patient/{nhsNumber}")
    public ResponseEntity<String> updatePatientGpStatus(@PathVariable("nhsNumber") String nhsNumber, @RequestBody PdsPatchRequest pdsPatchRequest) throws InterruptedException {
        log.info("Received PDS Update request");
        var requestDelay = delayResponse.getUpdateResponseTime();

        log.info("Update Request processed with delay " + requestDelay + "ms");
        return generateResponse(updateResponse.getResponse(nhsNumber, pdsPatchRequest, requestDelay), getUpdateStatus(requestDelay));
    }

    private ResponseEntity<String> generateResponse(String body, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.setETag("W/\"1\"");
        return new ResponseEntity<>(body, headers, status);
    }

    private HttpStatus getUpdateStatus(int requestDelay) {
        return requestDelay > 10000 ? HttpStatus.SERVICE_UNAVAILABLE : HttpStatus.OK;
    }

}
