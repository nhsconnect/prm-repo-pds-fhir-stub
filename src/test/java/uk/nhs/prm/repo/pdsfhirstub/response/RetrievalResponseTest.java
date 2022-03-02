package uk.nhs.prm.repo.pdsfhirstub.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class RetrievalResponseTest {

    @Test
    void shouldReturnResponseWithNhsNumberFromRequest() {
        var retrievalResponse = new RetrievalResponse();
        var response = retrievalResponse.getResponse("123");
        assertThat(response).contains("\"id\": \"123\",\n");
        assertThat(response).contains("\"system\": \"https://fhir.nhs.uk/Id/nhs-number\",\n" +
                "            \"value\": \"123\"\n" +
                "        }\n");
    }
}