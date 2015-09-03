package com.waleedkhan.sample.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waleedkhan.sample.model.Batch;
import com.waleedkhan.sample.model.Status;
import com.waleedkhan.sample.service.BatchService;
import com.waleedkhan.sample.api.ApiPaths;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import static com.waleedkhan.sample.api.ApiParams.STATUS;

@RestController
@RequestMapping(value = ApiPaths.BATCHES)
public class BatchController {
    @Inject
    BatchService batchService;

    @RequestMapping(method = GET)
    public ResponseEntity<List<Batch>> getBatches() {
        return new ResponseEntity<>(batchService.getBatches(), OK);
    }

    @RequestMapping(method = PUT)
    public ResponseEntity<Void> updateBatches(
            @RequestParam(STATUS) Status status,
            @RequestBody List<String> batchIds) {
        batchService.updateBatches(batchIds, status);

        return new ResponseEntity<>(OK);
    }
}
