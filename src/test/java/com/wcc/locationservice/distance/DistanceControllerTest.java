package com.wcc.locationservice.distance;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.wcc.locationservice.postcode.Postcode;
import com.wcc.locationservice.postcode.PostcodeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@WebMvcTest
class DistanceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PostcodeService postcodeService;

    @MockBean
    DistanceService distanceService;

    Postcode POSTCODE_1 = new Postcode(1L, "AB10 1XG", 57.144165, -2.114848);
    Postcode POSTCODE_2 = new Postcode(2L, "AB10 6RN", 57.13788, -2.121487);

    @Test
    public void testCalcDistance_success() throws Exception {

        double distance = 0.8055046803242006;
        Mockito.when(postcodeService.findByPostcode(ArgumentMatchers.eq(POSTCODE_1.getPostcode())))
                .thenReturn(Optional.of(POSTCODE_1));

        Mockito.when(postcodeService.findByPostcode(ArgumentMatchers.eq(POSTCODE_2.getPostcode())))
                .thenReturn(Optional.of(POSTCODE_2));

        Mockito.when(distanceService.calculateDistance(ArgumentMatchers.eq(POSTCODE_1.getLatitude()), ArgumentMatchers.eq(POSTCODE_1.getLongitude()), ArgumentMatchers.eq(POSTCODE_2.getLatitude()), ArgumentMatchers.eq(POSTCODE_2.getLongitude())))
                .thenReturn(distance);

        mockMvc
                .perform(get("/api/distance")
                        .header("Authorization", "Basic bG9jYXRpb25zZXJ2aWNlOmxvY2F0aW9uc2VydmljZQ==")
                        .param("postcode1", POSTCODE_1.getPostcode())
                        .param("postcode2", POSTCODE_2.getPostcode())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.locations[0].id", Matchers.equalTo(POSTCODE_1.getId()), Long.class))
                .andExpect(jsonPath("$.locations[1].id", Matchers.equalTo(POSTCODE_2.getId()), Long.class))
                .andExpect(jsonPath("$.distance", Matchers.equalTo(distance)))
                .andExpect(jsonPath("$.distanceStr", Matchers.equalTo(String.format("%skm", distance))));
    }

    @Test
    public void testCalcDistance_given_invalid_postcode1_then_return_not_found() throws Exception {

        Mockito.when(postcodeService.findByPostcode(ArgumentMatchers.eq(POSTCODE_1.getPostcode())))
                .thenReturn(Optional.empty());

        Mockito.when(postcodeService.findByPostcode(ArgumentMatchers.eq(POSTCODE_2.getPostcode())))
                .thenReturn(Optional.of(POSTCODE_2));

        mockMvc
                .perform(get("/api/distance")
                        .header("Authorization", "Basic bG9jYXRpb25zZXJ2aWNlOmxvY2F0aW9uc2VydmljZQ==")
                        .param("postcode1", "blah")
                        .param("postcode2", POSTCODE_2.getPostcode()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCalcDistance_given_invalid_postcode2_then_return_not_found() throws Exception {

        Mockito.when(postcodeService.findByPostcode(ArgumentMatchers.eq(POSTCODE_1.getPostcode())))
                .thenReturn(Optional.of(POSTCODE_1));

        Mockito.when(postcodeService.findByPostcode(ArgumentMatchers.eq(POSTCODE_2.getPostcode())))
                .thenReturn(Optional.empty());

        mockMvc
                .perform(get("/api/distance")
                        .header("Authorization", "Basic bG9jYXRpb25zZXJ2aWNlOmxvY2F0aW9uc2VydmljZQ==")
                        .param("postcode1", POSTCODE_1.getPostcode())
                        .param("postcode2", "blah"))
                .andExpect(status().isNotFound());
    }

}