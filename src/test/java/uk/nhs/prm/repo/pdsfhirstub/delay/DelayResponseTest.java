package uk.nhs.prm.repo.pdsfhirstub.delay;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DelayResponseTest {
    @Mock
    GenerateRandomNumber generateRandomNumber;

    @Mock
    Sleeper sleeper;

    @InjectMocks
    private DelayResponse delayResponse;

    @Test
    void shouldReturn4477msDelayWhenRandomNumberOver99_99() throws InterruptedException {
        when(generateRandomNumber.generate()).thenReturn(99.9987284);

        assertThat(delayResponse.getRetrievalResponseTime()).isEqualTo(4477);
        verify(sleeper).sleep(4477);
    }

    @Test
    void shouldReturn442msDelayWhenRandomNumberOver99() throws InterruptedException {
        when(generateRandomNumber.generate()).thenReturn(99.8126778);

        assertThat(delayResponse.getRetrievalResponseTime()).isEqualTo(442);
        verify(sleeper).sleep(442);
    }

    @Test
    void shouldReturn184msDelayWhenRandomNumberOver95() throws InterruptedException {
        when(generateRandomNumber.generate()).thenReturn(96.1);

        assertThat(delayResponse.getRetrievalResponseTime()).isEqualTo(184);
        verify(sleeper).sleep(184);
    }

    @Test
    void shouldReturn81msDelayWhenRandomNumberOver85() throws InterruptedException {
        when(generateRandomNumber.generate()).thenReturn(85.1);

        assertThat(delayResponse.getRetrievalResponseTime()).isEqualTo(81);
        verify(sleeper).sleep(81);
    }

    @Test
    void shouldReturn59msDelayWhenRandomNumberOver50() throws InterruptedException {
        when(generateRandomNumber.generate()).thenReturn(50.1);

        assertThat(delayResponse.getRetrievalResponseTime()).isEqualTo(59);
        verify(sleeper).sleep(59);
    }

    @Test
    void shouldReturn46msDelayWhenRandomNumberUnder50() throws InterruptedException {
        when(generateRandomNumber.generate()).thenReturn(49.9);

        assertThat(delayResponse.getRetrievalResponseTime()).isEqualTo(46);
        verify(sleeper).sleep(46);
    }
}
