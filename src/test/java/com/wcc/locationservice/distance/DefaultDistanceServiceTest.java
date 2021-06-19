package com.wcc.locationservice.distance;

import com.wcc.locationservice.postcode.Postcode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DefaultDistanceServiceTest {

    private DistanceService distanceService;

    @BeforeEach
    void initUseCase() {
        distanceService = new DistanceServiceImpl();
    }

    @Test
    public void testCalcDistance() {
        Postcode postcode1 = new Postcode(1L, "AB10 1XG", 57.144165, -2.114848);
        Postcode postcode2 = new Postcode(2L, "AB10 6RN", 57.13788, -2.121487);

        double distance = distanceService.calculateDistance(
                postcode1.getLatitude(), postcode1.getLongitude(),
                postcode2.getLatitude(), postcode2.getLongitude()
        );

        assertThat(distance).isEqualTo(0.8055046803242006);
    }

}