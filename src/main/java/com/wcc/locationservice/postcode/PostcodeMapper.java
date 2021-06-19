package com.wcc.locationservice.postcode;

public class PostcodeMapper {
    public static Postcode DtoToEntity(PostcodeDto postcodeDto) {
        return new Postcode(
                postcodeDto.getId(),
                postcodeDto.getPostcode(),
                postcodeDto.getLatitude(),
                postcodeDto.getLongitude()
        );
    }

    public static PostcodeDto EntityToDto(Postcode postcode) {
        return new PostcodeDto(
                postcode.getId(),
                postcode.getPostcode(), postcode.getLatitude(),
                postcode.getLongitude()
        );
    }
}
