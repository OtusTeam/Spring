package ru.otus.work;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.work.domain.Cargo;
import ru.otus.work.domain.Container;
import ru.otus.work.gateway.Warehouse;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
@EnableIntegration
@IntegrationComponentScan
@Configuration
@ComponentScan
public class WorkApplication {

    @Bean
    public IntegrationFlow warehouseFlow() {
        return IntegrationFlows.from("cargoChannel")
                .split()
                .handle("warehouseService", "prepareContainer")
                .handle("detectDangerService", "detectCargo")
                .aggregate()
                .channel("containerChannel")
                .get();
    }

    @Bean
    public PublishSubscribeChannel cargoChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(500).maxMessagesPerPoll(5).get();
    }

    public static void main(String[] args) {

        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(WorkApplication.class);

        Warehouse warehouse = ctx.getBean(Warehouse.class);

        Collection<Cargo> cargos = new ArrayList<>();
        Cargo cargo = new Cargo();
        cargo.setType("Battarey");
        cargos.add(cargo);

        cargo = new Cargo();
        cargo.setType("Flour");
        cargos.add(cargo);

        cargo = new Cargo();
        cargo.setType("Fuel");
        cargos.add(cargo);

        Collection<Container> containers = warehouse.process(cargos);

        System.out.println(containers);
    }
}
