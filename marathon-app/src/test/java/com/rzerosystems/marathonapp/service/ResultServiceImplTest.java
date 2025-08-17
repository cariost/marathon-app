package com.rzerosystems.marathonapp.service;

import com.rzerosystems.marathonapp.model.Result;
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
class ResultServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ResultServiceImpl resultService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(resultService, "API_URL", "http://api.test/results");
    }

    @Test
    void getResults_returnsListFromApi() {
        Result result1 = new Result();
        Result result2 = new Result();
        Result[] resultArray = {result1, result2};
        when(restTemplate.getForObject("http://api.test/results", Result[].class)).thenReturn(resultArray);

        List<Result> result = resultService.getResults();

        assertEquals(Arrays.asList(resultArray), result);
        verify(restTemplate).getForObject("http://api.test/results", Result[].class);
    }

    @Test
    void getResultById_callsApiWithIdentifier() {
        Result expected = new Result();
        when(restTemplate.getForObject("http://api.test/results/1", Result.class)).thenReturn(expected);

        Result result = resultService.getResultById(1);

        assertEquals(expected, result);
        verify(restTemplate).getForObject("http://api.test/results/1", Result.class);
    }

    @Test
    void save_callsPostOnApi() {
        Result input = new Result();
        when(restTemplate.postForObject("http://api.test/results", input, Result.class)).thenReturn(input);

        Result result = resultService.save(input);

        assertEquals(input, result);
        verify(restTemplate).postForObject("http://api.test/results", input, Result.class);
    }

    @Test
    void update_callsPutOnApi() {
        Result input = new Result();

        resultService.update(5, input);

        verify(restTemplate).put("http://api.test/results/5", input, 5);
    }

    @Test
    void delete_callsDeleteOnApi() {
        resultService.delete(10);

        verify(restTemplate).delete("http://api.test/results/10", 10);
    }
}

