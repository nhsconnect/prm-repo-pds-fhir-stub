package uk.nhs.prm.repo.pdsfhirstub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


@Slf4j
@RestController
public class StubPdsFhirController {

    @GetMapping("Patient/{nhsNumber}")
    @ResponseStatus(HttpStatus.OK)
    public String getPatientGpStatus(@PathVariable("nhsNumber") String nhsNumber) throws InterruptedException {
        log.info("Request for pds record received from {}");
        int decideDelay= decideGetDelay();

        if (decideDelay > 50) {
            Thread.currentThread().sleep(5000);
        }else {
            Thread.currentThread().sleep(2000);
        }

        log.info("Finishing after some delay");
        return pdsResponse();
    }

    private int decideGetDelay(){
        final Random r = new Random();
        int low = 0;
        int high = 100;
        return r.nextInt(high-low) + low;
    }

    private String pdsResponse(){
        return "some-response";
    }


}

