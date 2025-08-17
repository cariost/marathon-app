package com.rzerosystems.marathonapp.service;

import com.rzerosystems.marathonapp.model.Marathon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarathonServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MarathonServiceImpl marathonService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(marathonService, "API_URL", "http://api.test/marathons");
    }

    @Test
    void getMarathons_returnsListFromApi() {
        Marathon marathon1 = new Marathon();
        Marathon marathon2 = new Marathon();
        Marathon[] marathonsArray = {marathon1, marathon2};
        when(restTemplate.getForObject("http://api.test/marathons", Marathon[].class)).thenReturn(marathonsArray);

        List<Marathon> result = marathonService.getMarathons();

        assertEquals(Arrays.asList(marathonsArray), result);
        verify(restTemplate).getForObject("http://api.test/marathons", Marathon[].class);
    }
}

