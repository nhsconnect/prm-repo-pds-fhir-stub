package uk.nhs.prm.repo.pdsfhirstub.response;

import org.springframework.stereotype.Component;

@Component
public class UpdateResponse {

    public String getResponse(String nhsNumber) {
        return nhsNumber;
    }
}
