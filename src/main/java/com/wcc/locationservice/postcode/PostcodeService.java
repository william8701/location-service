package com.wcc.locationservice.postcode;

import java.util.Optional;

public interface PostcodeService {

    Optional<Postcode> findByPostcode(String postcode);

    Optional<Postcode> findById(Long id);

    Optional<Postcode> update(Long id, Postcode postcode);

}
