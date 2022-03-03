package uk.nhs.prm.repo.pdsfhirstub.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UpdateResponseTest {

    @Test
    void shouldReturnResponseWithNhsNumberFromRequest() {
        var updateResponse = new UpdateResponse();
        var response = updateResponse.getResponse("123");
        assertThat(response).contains("\"id\": \"123\",\n");
        assertThat(response).contains("\"system\": \"https://fhir.nhs.uk/Id/nhs-number\",\n" +
                "            \"value\": \"123\"\n" +
                "        }\n");
    }

    @Test
    void shouldReturnResponseWithManagingOrganisationField() {
        var updateResponse = new UpdateResponse();
        var response = updateResponse.getResponse("123");
        assertThat(response).contains("managingOrganization");
    }

}