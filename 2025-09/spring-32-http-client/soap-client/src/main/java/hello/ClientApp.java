package hello;

import hello.wsdl.Country;
import hello.wsdl.GetCountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class ClientApp {

    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }

    @Bean
    CommandLineRunner lookup(CountryClient quoteClient) {
        return args -> {
            String country = "Spain";

            if (args.length > 0) {
                country = args[0];
            }
            GetCountryResponse response = quoteClient.getCountry(country);
            if (response.getCountry() != null) {
                Country countryDetails = response.getCountry();
                log.warn("GetCountry {} : Country[name={}, population={}, capital={}, currency={}]",
                        country, countryDetails.getName(), countryDetails.getPopulation(),
                        countryDetails.getCapital(), countryDetails.getCurrency());
            } else {
                log.warn("GetCountry {} : no country found in response", country);
            }
        };
    }

}
