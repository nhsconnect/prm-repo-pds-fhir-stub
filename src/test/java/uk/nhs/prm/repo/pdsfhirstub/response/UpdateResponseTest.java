package uk.nhs.prm.repo.pdsfhirstub.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateResponseTest {

    @Test
    void shouldReturnResponseWithNhsNumberFromRequest() {
        var updateResponse = new UpdateResponse();
        var response = updateResponse.getResponse("123", 200);
        assertThat(response).contains("\"id\": \"123\",\n");
        assertThat(response).contains("\"system\": \"https://fhir.nhs.uk/Id/nhs-number\",\n" +
                "            \"value\": \"123\"\n" +
                "        }\n");
    }

    @Test
    void shouldReturnResponseWithManagingOrganisationField() {
        var updateResponse = new UpdateResponse();
        var response = updateResponse.getResponse("123", 200);
        assertThat(response).contains("managingOrganization");
    }

    @Test
    void shouldReturnServiceUnavailableResponseIfDelayIsOver10s() {
        var updateResponse = new UpdateResponse();
        var response = updateResponse.getResponse("123", 10001);
        assertThat(response).contains("code\": \"SERVICE_UNAVAILABLE");
        assertThat(response).doesNotContain("managingOrganization");
    }

}