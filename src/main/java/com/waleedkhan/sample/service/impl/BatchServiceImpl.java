package com.waleedkhan.sample.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.waleedkhan.sample.dao.BatchDao;
import com.waleedkhan.sample.model.Batch;
import com.waleedkhan.sample.model.Status;
import com.waleedkhan.sample.service.BatchService;

@Service
public class BatchServiceImpl implements BatchService {

    private final BatchDao batchDao;

    @Inject
    public BatchServiceImpl(BatchDao batchDao) {
        this.batchDao = batchDao;
    }

    @Override
    public void updateBatches(List<String> batchIds, Status status) {
        batchDao.updateBatches(batchIds, status);
    }

    @Override
    public List<Batch> getBatches() {
        return batchDao.getBatches();
    }
}
