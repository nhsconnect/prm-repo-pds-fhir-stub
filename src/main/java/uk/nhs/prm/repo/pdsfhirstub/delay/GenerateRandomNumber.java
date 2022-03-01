package uk.nhs.prm.repo.pdsfhirstub.delay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class GenerateRandomNumber {
    protected double generate(){
        final Random r = new Random();
        log.info(String.valueOf(r.nextFloat()*100));
        return r.nextFloat()*100;
    }
}
