package com.wcc.locationservice.postcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DefaultPostcodeServiceTest {

    private PostcodeService postcodeService;

    @MockBean
    PostcodeRepository postcodeRepository;

    @BeforeEach
    void initUseCase() {
        postcodeService = new PostcodeServiceImpl(postcodeRepository);
    }

    @Test
    public void testFindByPostcode_success() {
        Postcode samplePostcode = new Postcode(1L, "AB10 1XG", 57.144165, -2.114848);
        Mockito.when(postcodeRepository.findByPostcode(ArgumentMatchers.any()))
                .thenReturn(Optional.of(samplePostcode));

        Optional<Postcode> postcode = postcodeService.findByPostcode("AB10 1XG");

        assertThat(postcode.isPresent()).isEqualTo(true);
        assertThat(postcode).isEqualTo(Optional.of(samplePostcode));
    }

    @Test
    public void testFindByPostcode_empty() {
        Mockito.when(postcodeRepository.findByPostcode(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        Optional<Postcode> postcode = postcodeService.findByPostcode("AB10 1XG");

        assertThat(postcode.isPresent()).isEqualTo(false);
    }

    @Test
    void testFindById_success() {
        Postcode samplePostcode = new Postcode(1L, "AB10 1XG", 57.144165, -2.114848);
        Mockito.when(postcodeRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(samplePostcode));

        Optional<Postcode> postcode = postcodeService.findById(1L);

        assertThat(postcode.isPresent()).isEqualTo(true);
        assertThat(postcode).isEqualTo(Optional.of(samplePostcode));
    }

    @Test
    void testFindById_empty() {
        Mockito.when(postcodeRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Optional<Postcode> postcode = postcodeService.findById(1L);

        assertThat(postcode.isPresent()).isEqualTo(false);
    }

    @Test
    void testUpdate_success() {
        Postcode existingPostcode = new Postcode(1L, "AB10 1XG", 57.144165, -2.114848);
        Postcode updatedPostcode = new Postcode(1L, "AB10 1XH", 58.144165, -3.114848);

        Mockito.when(postcodeRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(existingPostcode));
        Mockito.when(postcodeRepository.save(ArgumentMatchers.any(Postcode.class)))
                .thenReturn(updatedPostcode);

        Optional<Postcode> postcode = postcodeService.update(1L, existingPostcode);

        assertThat(postcode.isPresent()).isEqualTo(true);
        assertThat(postcode.get()).isEqualTo(updatedPostcode);
    }

    @Test
    void testUpdate_notfound() {
        Postcode existingPostcode = new Postcode(1L, "AB10 1XG", 57.144165, -2.114848);

        Mockito.when(postcodeRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Optional<Postcode> postcode = postcodeService.update(1L, existingPostcode);

        assertThat(postcode.isPresent()).isEqualTo(false);
    }

}