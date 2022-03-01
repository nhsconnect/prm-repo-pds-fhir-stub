package uk.nhs.prm.repo.pdsfhirstub.delay;

import org.springframework.stereotype.Component;

@Component
public class Sleeper {
    public void sleep(int timeToSleep) throws InterruptedException {
        Thread.sleep(timeToSleep);
    }
}
