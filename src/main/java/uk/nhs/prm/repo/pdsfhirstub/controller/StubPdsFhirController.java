package uk.nhs.prm.repo.pdsfhirstub.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.nhs.prm.repo.pdsfhirstub.delay.DelayResponse;

@Slf4j
@AllArgsConstructor
@RestController
public class StubPdsFhirController {
    private DelayResponse delayResponse;

    @GetMapping("Patient/{nhsNumber}")
    @ResponseStatus(HttpStatus.OK)
    public String getPatientGpStatus(@PathVariable("nhsNumber") String nhsNumber) throws InterruptedException {
        log.info("Request for pds record received");
        Thread.sleep(delayResponse.getResponseTime());

        log.info("Finishing after some delay");
        return pdsResponse();
    }

    private String pdsResponse() {
        return "some-response";
    }
}

