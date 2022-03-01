package uk.nhs.prm.repo.pdsfhirstub.delay;

public class Sleeper {
    public void sleep(int timeToSleep) throws InterruptedException {
        Thread.sleep(timeToSleep);
    }
}
