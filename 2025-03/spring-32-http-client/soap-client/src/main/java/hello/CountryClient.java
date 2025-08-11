package hello;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import hello.wsdl.GetCountryRequest;
import hello.wsdl.GetCountryResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountryClient extends WebServiceGatewaySupport {

	public GetCountryResponse getCountry(String country) {

		GetCountryRequest request = new GetCountryRequest();
		request.setName(country);

		log.info("Requesting location for {}", country);

		GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8080/ws/countries", request,
						new SoapActionCallback("GetCountryRequest"));

		return response;
	}

}
