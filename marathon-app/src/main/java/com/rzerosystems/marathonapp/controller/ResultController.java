package com.rzerosystems.marathonapp.controller;


import com.rzerosystems.marathonapp.common.Constants;
import com.rzerosystems.marathonapp.exceptions.ResourceNotFoundException;
import com.rzerosystems.marathonapp.model.Result;
import com.rzerosystems.marathonapp.service.ResultService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(value = Constants.APP_PREFIX + Constants.VERSION_1_PATH + Constants.RESULT_PATH,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ResultController {


    private static Logger logger = LoggerFactory.getLogger(ResultController.class);

    private ResultService resultService;

    @GetMapping(produces = "application/json")
    @Operation(description = "Get Results")
    public ResponseEntity<List<Result>> getResults() {

        logger.info("... CALL RESULT SERVICE API");
        List<Result> resultList = resultService.getResults();

        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }

    @GetMapping(path="/{id}", produces = "application/json")
    @Operation(description = "Retrieve Result")
    public ResponseEntity<Result> getResult(@PathVariable int id){

        logger.info("... CALL RETRIEVE SERVICE API");
        Result result = resultService.getResultById(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(description = "Create Result")
    public ResponseEntity<Result> createResult(@Valid @RequestBody Result result) {

        resultService.save(result);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path="/{id}", produces = "application/json")
    @Operation(description = "Update Result")
    public ResponseEntity<Result> updateResult(@PathVariable int id, @Valid @RequestBody Result result) throws ResourceNotFoundException {

        Optional<Result> resultFound = Optional.ofNullable(resultService.getResultById(id));
        if(resultFound.isPresent()){
            resultService.update(id, result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path="/{id}")
    @Operation(description = "Delete Result")
    public ResponseEntity<Result> deleteResult(@PathVariable int id){
        resultService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Autowired
    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }
}
