package wrongPackage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Этот компонент не будет найден, т.к. нарушена иерархия пакетов
@Component
public class PreparationUnKnown implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(PreparationUnKnown.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("Это не увидим никогда");
    }
}
