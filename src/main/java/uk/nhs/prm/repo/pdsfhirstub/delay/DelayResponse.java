package uk.nhs.prm.repo.pdsfhirstub.delay;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class DelayResponse {
    private Sleeper sleeper;
    private GenerateRandomNumber generateRandomNumber;

    public int getRetrievalResponseTime() throws InterruptedException {
        double randomNumber = generateRandomNumber.generate();
        if (randomNumber < 50) {
            sleeper.sleep(46);
            return 46;
        }
        if (randomNumber < 85) {
            sleeper.sleep(59);
            return 59;
        }
        if (randomNumber < 95) {
            sleeper.sleep(81);
            return 81;
        }
        if (randomNumber < 99) {
            sleeper.sleep(184);
            return 184;
        }
        if (randomNumber < 99.9) {
            sleeper.sleep(442);
            return 442;
        }
        sleeper.sleep(4477);
        return 4477;
    }

    public int getUpdateResponseTime() {
        return 0;
    }
}
