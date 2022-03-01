package uk.nhs.prm.repo.pdsfhirstub.delay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class DelayResponse {
    public int getResponseTime() {
        double randomNumber = generateRandomNumber();

        if (randomNumber > 50) {
            log.info("5000 DELAY");
            return 5000;
        } else {
            log.info("2000 DELAY");
            return 2000;
        }
    }

    protected double generateRandomNumber(){
        final Random r = new Random();
        log.info(String.valueOf(r.nextFloat()*100));
        return r.nextFloat()*100;
    }
}
