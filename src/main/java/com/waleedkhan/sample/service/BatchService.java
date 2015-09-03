package com.waleedkhan.sample.service;

import java.util.List;

import com.waleedkhan.sample.model.Batch;
import com.waleedkhan.sample.model.Status;

public interface BatchService {
    void updateBatches(List<String> batchIds, Status status);

    List<Batch> getBatches();
}
