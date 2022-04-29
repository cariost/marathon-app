package com.rzerosystems.marathonapp.controller;


import com.rzerosystems.marathonapp.common.Constants;
import com.rzerosystems.marathonapp.model.Marathon;
import com.rzerosystems.marathonapp.service.MarathonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(Constants.APP_PREFIX + Constants.VERSION_1_PATH + Constants.MARATHON_PATH)
public class MarathonController {

    private static Logger logger = LoggerFactory.getLogger(MarathonController.class);

    @Autowired
    private MarathonService marathonService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Get Marathons")
    public ResponseEntity<List<Marathon>> getMarathons() {

        logger.info("... CALL GET MARATHONS API");
        List<Marathon> marathonResponseList = marathonService.getMarathons();

        return ResponseEntity.status(HttpStatus.OK).body(marathonResponseList);
    }
}