package com.wcc.locationservice.postcode;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostcodeServiceImpl implements PostcodeService {

    private final PostcodeRepository postcodeRepository;

    public PostcodeServiceImpl(PostcodeRepository postcodeRepository) {
        this.postcodeRepository = postcodeRepository;
    }

    @Override
    public Optional<Postcode> findByPostcode(String postcode) {
        return postcodeRepository.findByPostcode(postcode);
    }

    @Override
    public Optional<Postcode> findById(Long id) {
        return postcodeRepository.findById(id);
    }

    @Override
    public Optional<Postcode> update(Long id, Postcode postcode) {
        return postcodeRepository.findById(id).map(postcodeFromDB -> {
            postcodeFromDB.setLatitude(postcode.getLatitude());
            postcodeFromDB.setLongitude(postcode.getLongitude());
            postcodeFromDB.setPostcode(postcode.getPostcode());
            return postcodeRepository.save(postcodeFromDB);
        });
    }

}
