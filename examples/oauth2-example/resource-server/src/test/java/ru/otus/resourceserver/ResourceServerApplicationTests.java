package ru.otus.resourceserver;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import ru.otus.resourceserver.config.OAuthProps;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ResourceServerApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OAuthProps oAuthProps;

    @Data
    static class TokenResponse {
        private String access_token;
        private String token_type;
        private String refresh_token;
        private String expires_in;
        private String scope;
    }

    @Test
    void shouldSuccessfullyAccessTheEndpointAfterLoginOnAuthorizationServer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(oAuthProps.getClientId(), oAuthProps.getClientSecret());

        ResponseEntity<TokenResponse> tokenResponse = restTemplate.postForEntity(
                "http://localhost:8090/oauth/token?client_id=" + oAuthProps.getClientId() +
                        "&grant_type=password&username=user1&password=user1",
                new HttpEntity<>(headers),
                TokenResponse.class);


/*
		System.out.println(tokenResponse.getStatusCode());
		System.out.println(tokenResponse.getHeaders());
		System.out.println(tokenResponse.getBody());
*/
        assertThat(tokenResponse.getBody()).isNotNull();

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(tokenResponse.getBody().getAccess_token());


		ResponseEntity<String> currentUserResponse = restTemplate.exchange(
				"http://localhost:8080/api/current-user",
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		System.out.println(currentUserResponse.getStatusCode());
		System.out.println(currentUserResponse.getHeaders());
		System.out.println(currentUserResponse.getBody());
		assertThat(currentUserResponse.getBody())
                .isNotNull().isEqualTo("{\"userName\":\"user1\",\"firstName\":\"Василий\",\"fatherName\":\"Григорьевич\"}");
    }

}
