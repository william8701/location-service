package com.wcc.locationservice.postcode;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PostcodeController {

    private final PostcodeService postcodeService;

    public PostcodeController(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    @GetMapping(path = "/api/postcode/{id}")
    public Postcode getPostcode(@PathVariable("id") Long id) {
        return postcodeService
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @PutMapping(path = "/api/postcode/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Postcode updatePostcode(@PathVariable("id") Long id, @RequestBody PostcodeDto requestBody) {
        Postcode postcode = PostcodeMapper.DtoToEntity(requestBody);

        return postcodeService
                .update(id, postcode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

}
