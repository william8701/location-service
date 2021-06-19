package com.wcc.locationservice.distance;

import com.wcc.locationservice.postcode.Postcode;
import com.wcc.locationservice.postcode.PostcodeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@RestController
public class DistanceController {

    private final PostcodeService postcodeService;

    private final DistanceService distanceService;

    public DistanceController(PostcodeService postcodeService, DistanceService distanceService) {
        this.postcodeService = postcodeService;
        this.distanceService = distanceService;
    }

    @GetMapping(path = "/api/distance", produces = "application/json")
    public DistanceCalcResultDto calcDistance(@RequestParam String postcode1, @RequestParam String postcode2) {
        Postcode postcodeObj1 = postcodeService
                .findByPostcode(postcode1)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));

        Postcode postcodeObj2 = postcodeService
                .findByPostcode(postcode2)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));

        double distance = distanceService.calculateDistance(
                postcodeObj1.getLatitude(), postcodeObj1.getLongitude(),
                postcodeObj2.getLatitude(), postcodeObj2.getLongitude());

        return new DistanceCalcResultDto(Arrays.asList(postcodeObj1, postcodeObj2), distance, String.format("%skm", distance));
    }
}
