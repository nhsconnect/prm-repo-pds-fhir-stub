package uk.nhs.prm.repo.pdsfhirstub.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdateResponse {

    public String getResponse(String nhsNumber, int delay) {
        log.info("gere us tge " + delay);
        if (delay > 10000) {
            return serviceUnavailableResponse();
        }
        return mofUpdatedResponse().replaceAll("NHS_NUMBER", nhsNumber);
    }

    private String mofUpdatedResponse() {
        return "{\n" +
                "    \"address\": [\n" +
                "        {\n" +
                "            \"extension\": [\n" +
                "                {\n" +
                "                    \"extension\": [\n" +
                "                        {\n" +
                "                            \"url\": \"type\",\n" +
                "                            \"valueCoding\": {\n" +
                "                                \"code\": \"PAF\",\n" +
                "                                \"system\": \"https://fhir.hl7.org.uk/CodeSystem/UKCore-AddressKeyType\"\n" +
                "                            }\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"value\",\n" +
                "                            \"valueString\": \"19974416\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"url\": \"https://fhir.hl7.org.uk/StructureDefinition/Extension-UKCore-AddressKey\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"id\": \"dBxTY\",\n" +
                "            \"line\": [\n" +
                "                \"12 AVENUE VIVIAN\",\n" +
                "                \"SCUNTHORPE\"\n" +
                "            ],\n" +
                "            \"period\": {\n" +
                "                \"start\": \"2003-11-24\"\n" +
                "            },\n" +
                "            \"postalCode\": \"DN15 8JW\",\n" +
                "            \"use\": \"home\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"birthDate\": \"1992-01-31\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"id\": \"NHS_NUMBER\",\n" +
                "    \"identifier\": [\n" +
                "        {\n" +
                "            \"extension\": [\n" +
                "                {\n" +
                "                    \"url\": \"https://fhir.hl7.org.uk/StructureDefinition/Extension-UKCore-NHSNumberVerificationStatus\",\n" +
                "                    \"valueCodeableConcept\": {\n" +
                "                        \"coding\": [\n" +
                "                            {\n" +
                "                                \"code\": \"01\",\n" +
                "                                \"display\": \"Number present and verified\",\n" +
                "                                \"system\": \"https://fhir.hl7.org.uk/CodeSystem/UKCore-NHSNumberVerificationStatus\",\n" +
                "                                \"version\": \"1.0.0\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"system\": \"https://fhir.nhs.uk/Id/nhs-number\",\n" +
                "            \"value\": \"NHS_NUMBER\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"managingOrganization\": {\n" +
                "        \"identifier\": {\n" +
                "            \"period\": {\n" +
                "                \"start\": \"2021-12-03\"\n" +
                "            },\n" +
                "            \"system\": \"https://fhir.nhs.uk/Id/ods-organization-code\",\n" +
                "            \"value\": \"UPDATED_MOF\"\n" +
                "        },\n" +
                "        \"type\": \"Organization\"\n" +
                "    },\n" +
                "    \"meta\": {\n" +
                "        \"security\": [\n" +
                "            {\n" +
                "                \"code\": \"U\",\n" +
                "                \"display\": \"unrestricted\",\n" +
                "                \"system\": \"http://terminology.hl7.org/CodeSystem/v3-Confidentiality\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"versionId\": \"6\"\n" +
                "    },\n" +
                "    \"name\": [\n" +
                "        {\n" +
                "            \"family\": \"SPARKS\",\n" +
                "            \"given\": [\n" +
                "                \"John\"\n" +
                "            ],\n" +
                "            \"id\": \"pYssk\",\n" +
                "            \"period\": {\n" +
                "                \"start\": \"2010-01-10\"\n" +
                "            },\n" +
                "            \"prefix\": [\n" +
                "                \"MR\"\n" +
                "            ],\n" +
                "            \"use\": \"usual\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"resourceType\": \"Patient\"\n" +
                "}";
    }

    public String serviceUnavailableResponse() {
        return "{\n" +
                "  \"resourceType\": \"OperationOutcome\",\n" +
                "  \"issue\": [\n" +
                "    {\n" +
                "      \"severity\": \"error\",\n" +
                "      \"code\": \"timeout\",\n" +
                "      \"details\": {\n" +
                "        \"coding\": [\n" +
                "          {\n" +
                "            \"system\": \"https://fhir.nhs.uk/R4/CodeSystem/Spine-ErrorOrWarningCode\",\n" +
                "            \"version\": \"1\",\n" +
                "            \"code\": \"SERVICE_UNAVAILABLE\",\n" +
                "            \"display\": \"Service unavailable\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"diagnostics\": \"The downstream domain processing has not completed within the configured timeout period. Using the same 'X-Request-ID' header, retry your request after the time specified by the 'Retry-After' response header.\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }


}
