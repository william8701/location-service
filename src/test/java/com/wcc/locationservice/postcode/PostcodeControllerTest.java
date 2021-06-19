package com.wcc.locationservice.postcode;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcc.locationservice.distance.DistanceService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@WebMvcTest
class PostcodeControllerTest {

    @MockBean
    PostcodeService postcodeService;

    @MockBean
    DistanceService distanceService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetPostcode_success() throws Exception {
        Postcode postcode = new Postcode(1L, "AB10 1XG", 57.144165, -2.114848);

        Mockito.when(postcodeService.findById(ArgumentMatchers.eq(postcode.getId())))
                .thenReturn(Optional.of(postcode));

        mockMvc.perform(get("/api/postcode/1").header("Authorization", "Basic bG9jYXRpb25zZXJ2aWNlOmxvY2F0aW9uc2VydmljZQ=="))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(postcode.getId()), Long.class))
                .andExpect(jsonPath("$.postcode", Matchers.equalTo(postcode.getPostcode())))
                .andExpect(jsonPath("$.latitude", Matchers.equalTo(postcode.getLatitude())))
                .andExpect(jsonPath("$.longitude", Matchers.equalTo(postcode.getLongitude())));
    }

    @Test
    public void testGetPostcode_notfound() throws Exception {
        Mockito.when(postcodeService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/postcode/1").header("Authorization", "Basic bG9jYXRpb25zZXJ2aWNlOmxvY2F0aW9uc2VydmljZQ=="))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdatePostcode_success() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PostcodeDto requestData = new PostcodeDto(1L, "AB10 1XH", 57.144165, -3.114849);
        String inputJson = objectMapper.writeValueAsString(requestData);

        Postcode updatedPostcode = new Postcode(1L, "AB10 1XH", 57.144165, -3.114849);
        Mockito.when(postcodeService.update(ArgumentMatchers.eq(1L), ArgumentMatchers.any()))
                .thenReturn(Optional.of(updatedPostcode));

        mockMvc
                .perform(put("/api/postcode/1")
                        .header("Authorization", "Basic bG9jYXRpb25zZXJ2aWNlOmxvY2F0aW9uc2VydmljZQ==")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(requestData.getId()), Long.class))
                .andExpect(jsonPath("$.postcode", Matchers.equalTo(requestData.getPostcode())))
                .andExpect(jsonPath("$.latitude", Matchers.equalTo(requestData.getLatitude())))
                .andExpect(jsonPath("$.longitude", Matchers.equalTo(requestData.getLongitude())));
    }

    @Test
    public void testUpdatePostcode_notfound() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PostcodeDto requestData = new PostcodeDto(1L, "AB10 1XH", 57.144165, -3.114849);
        String inputJson = objectMapper.writeValueAsString(requestData);
        Mockito.when(postcodeService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        mockMvc
                .perform(put("/api/postcode/1")
                        .header("Authorization", "Basic bG9jYXRpb25zZXJ2aWNlOmxvY2F0aW9uc2VydmljZQ==")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isNotFound());
    }

}