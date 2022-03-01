package uk.nhs.prm.repo.pdsfhirstub.delay;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DelayResponseTest {

    @Mock
    private DelayResponse delayResponse;

    @Test
    void shouldReturnXDelayWhenRandomNumberOver50() {
        when(delayResponse.generateRandomNumber()).thenReturn(70.1);
        when(delayResponse.getResponseTime()).thenCallRealMethod();

        assertThat(delayResponse.getResponseTime()).isEqualTo(5000);
    }

    @Test
    void shouldReturnXDelayWhenRandomNumberUnder50() {
        when(delayResponse.generateRandomNumber()).thenReturn(20.22);
        when(delayResponse.getResponseTime()).thenCallRealMethod();

        assertThat(delayResponse.getResponseTime()).isEqualTo(2000);
    }
}
