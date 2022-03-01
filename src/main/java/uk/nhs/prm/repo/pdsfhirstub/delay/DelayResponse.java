package uk.nhs.prm.repo.pdsfhirstub.delay;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class DelayResponse {
    private GenerateRandomNumber generateRandomNumber;

    public int getResponseTime() {
        double randomNumber = generateRandomNumber.generate();
        if (randomNumber < 50) {
            return 46;
        }
        if (randomNumber < 85) {
            return 59;
        }
        if (randomNumber < 95) {
            return 81;
        }
        if (randomNumber < 99) {
            return 184;
        }
        if (randomNumber < 99.9) {
            return 442;
        }
        return 4477;
    }
}
