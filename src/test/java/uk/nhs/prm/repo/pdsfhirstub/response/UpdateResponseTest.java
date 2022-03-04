package uk.nhs.prm.repo.pdsfhirstub.response;

import org.junit.jupiter.api.Test;
import uk.nhs.prm.repo.pdsfhirstub.pdspatchrequest.PdsPatch;
import uk.nhs.prm.repo.pdsfhirstub.pdspatchrequest.PdsPatchIdentifier;
import uk.nhs.prm.repo.pdsfhirstub.pdspatchrequest.PdsPatchRequest;
import uk.nhs.prm.repo.pdsfhirstub.pdspatchrequest.PdsPatchValue;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class UpdateResponseTest {

    @Test
    void shouldReturnResponseWithNhsNumberFromRequest() {
        var updateResponse = new UpdateResponse();
        var response = updateResponse.getResponse("123", buildPatchRequest(), 200);
        assertThat(response).contains("\"id\": \"123\",\n");
        assertThat(response).contains("\"system\": \"https://fhir.nhs.uk/Id/nhs-number\",\n" +
                "            \"value\": \"123\"\n" +
                "        }\n");
    }

    @Test
    void shouldReturnResponseWithManagingOrganisationField() {
        var updateResponse = new UpdateResponse();
        var response = updateResponse.getResponse("123", buildPatchRequest(), 200);
        assertThat(response).contains("managingOrganization");
        assertThat(response).contains("A1000");
    }

    @Test
    void shouldReturnResponseWithUPDATED_MOFIfPatchRequestIsEmpty() {
        var updateResponse = new UpdateResponse();
        var response = updateResponse.getResponse("123", PdsPatchRequest.builder().build(), 200);
        assertThat(response).contains("managingOrganization");
        assertThat(response).contains("MOF_UPDATED");
    }

    @Test
    void shouldReturnServiceUnavailableResponseIfDelayIsOver10s() {
        var updateResponse = new UpdateResponse();
        var response = updateResponse.getResponse("123", buildPatchRequest(), 10001);
        assertThat(response).contains("code\": \"SERVICE_UNAVAILABLE");
        assertThat(response).doesNotContain("managingOrganization");
    }

    private PdsPatchRequest buildPatchRequest() {
        var identifier = new PdsPatchIdentifier("A1000");
        var patch = new PdsPatch(new PdsPatchValue(identifier));
        return new PdsPatchRequest(singletonList(patch));
    }

}