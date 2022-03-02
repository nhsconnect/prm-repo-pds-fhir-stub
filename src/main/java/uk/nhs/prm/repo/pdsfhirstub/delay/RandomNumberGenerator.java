package uk.nhs.prm.repo.pdsfhirstub.delay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class RandomNumberGenerator {
    public double generate(){
        final Random r = new Random();
        return r.nextFloat() * 100;
    }
}
