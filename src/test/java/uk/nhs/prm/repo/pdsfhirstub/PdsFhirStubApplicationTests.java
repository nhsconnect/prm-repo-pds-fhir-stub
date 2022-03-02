package uk.nhs.prm.repo.pdsfhirstub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PdsFhirStubApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	void integration() {
		var forEntity = restTemplate.getForEntity(createURLWithPort("/Patient/9691927179"), String.class);
		assertThat(forEntity).isNotNull();
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
