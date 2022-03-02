package uk.nhs.prm.repo.pdsfhirstub.delay;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
    RandomNumberGenerator randomNumberGenerator;

    @Mock
    Sleeper sleeper;

    @InjectMocks
    private DelayResponse delayResponse;

    @Nested
    @DisplayName("GET Response Delay")
    class DelayResponseGetTest {

        @Test
        void shouldReturn4477msDelayWhenRandomNumberOver99_99() throws InterruptedException {
            when(randomNumberGenerator.generate()).thenReturn(99.9987284);

            assertThat(delayResponse.getRetrievalResponseTime()).isEqualTo(4477);
            verify(sleeper).sleep(4477);
        }

        @Test
        void shouldReturn442msDelayWhenRandomNumberOver99() throws InterruptedException {
            when(randomNumberGenerator.generate()).thenReturn(99.8126778);

            assertThat(delayResponse.getRetrievalResponseTime()).isEqualTo(442);
            verify(sleeper).sleep(442);
        }

        @Test
        void shouldReturn184msDelayWhenRandomNumberOver95() throws InterruptedException {
            when(randomNumberGenerator.generate()).thenReturn(96.1);

            assertThat(delayResponse.getRetrievalResponseTime()).isEqualTo(184);
            verify(sleeper).sleep(184);
        }

        @Test
        void shouldReturn81msDelayWhenRandomNumberOver85() throws InterruptedException {
            when(randomNumberGenerator.generate()).thenReturn(85.1);

            assertThat(delayResponse.getRetrievalResponseTime()).isEqualTo(81);
            verify(sleeper).sleep(81);
        }

        @Test
        void shouldReturn59msDelayWhenRandomNumberOver50() throws InterruptedException {
            when(randomNumberGenerator.generate()).thenReturn(50.1);

            assertThat(delayResponse.getRetrievalResponseTime()).isEqualTo(59);
            verify(sleeper).sleep(59);
        }

        @Test
        void shouldReturn46msDelayWhenRandomNumberUnder50() throws InterruptedException {
            when(randomNumberGenerator.generate()).thenReturn(49.9);

            assertThat(delayResponse.getRetrievalResponseTime()).isEqualTo(46);
            verify(sleeper).sleep(46);
        }
    }

    @Nested
    @DisplayName("Update Response Delay")
    class DelayResponseUpdateTest {

        @Test
        void shouldReturn10001msDelayWhenRandomNumberOver99_99() throws InterruptedException {
            when(randomNumberGenerator.generate()).thenReturn(99.9987284);

            assertThat(delayResponse.getUpdateResponseTime()).isEqualTo(10001);
            verify(sleeper).sleep(10001);
        }

        @Test
        void shouldReturn8000msDelayWhenRandomNumberOver99() throws InterruptedException {
            when(randomNumberGenerator.generate()).thenReturn(99.8126778);

            assertThat(delayResponse.getUpdateResponseTime()).isEqualTo(8000);
            verify(sleeper).sleep(8000);
        }

        @Test
        void shouldReturn2410msDelayWhenRandomNumberOver95() throws InterruptedException {
            when(randomNumberGenerator.generate()).thenReturn(96.1);

            assertThat(delayResponse.getUpdateResponseTime()).isEqualTo(2410);
            verify(sleeper).sleep(2410);
        }

        @Test
        void shouldReturn1145msDelayWhenRandomNumberOver85() throws InterruptedException {
            when(randomNumberGenerator.generate()).thenReturn(85.1);

            assertThat(delayResponse.getUpdateResponseTime()).isEqualTo(1145);
            verify(sleeper).sleep(1145);
        }

        @Test
        void shouldReturn780msDelayWhenRandomNumberOver50() throws InterruptedException {
            when(randomNumberGenerator.generate()).thenReturn(50.1);

            assertThat(delayResponse.getUpdateResponseTime()).isEqualTo(780);
            verify(sleeper).sleep(780);
        }

        @Test
        void shouldReturn597msDelayWhenRandomNumberUnder50() throws InterruptedException {
            when(randomNumberGenerator.generate()).thenReturn(49.9);

            assertThat(delayResponse.getUpdateResponseTime()).isEqualTo(597);
            verify(sleeper).sleep(597);
        }
    }
}
