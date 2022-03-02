package uk.nhs.prm.repo.pdsfhirstub.delay;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class DelayResponse {
    private Sleeper sleeper;
    private RandomNumberGenerator randomNumberGenerator;

    public int getRetrievalResponseTime() throws InterruptedException {
        return generateRandomDelay(46, 59, 81, 184, 442, 4477);
    }

    public int getUpdateResponseTime() throws InterruptedException {
        return generateRandomDelay(597, 780, 1145, 2410, 8000, 10001);
    }

    private int generateRandomDelay(int p50Delay, int p85Delay, int p95Delay, int p99Delay, int p99_9Delay, int maxDelay) throws InterruptedException {
        double randomNumber = randomNumberGenerator.generate();
        if (randomNumber < 50) {
            return setDelay("50", randomNumber, p50Delay);
        }
        if (randomNumber < 85) {
            return setDelay("85", randomNumber, p85Delay);
        }
        if (randomNumber < 95) {
            return setDelay("95", randomNumber, p95Delay);
        }
        if (randomNumber < 99) {
            return setDelay("99", randomNumber, p99Delay);
        }
        if (randomNumber < 99.9) {
            return setDelay("99.9", randomNumber, p99_9Delay);
        }
        return setDelay("100", randomNumber, maxDelay);
    }

    private int setDelay(String percentile, double randomNumber, int p50Delay) throws InterruptedException {
        log.info("Generated random variable {} is in the {} percentile", percentile, randomNumber);
        sleeper.sleep(p50Delay);
        return p50Delay;
    }
}
