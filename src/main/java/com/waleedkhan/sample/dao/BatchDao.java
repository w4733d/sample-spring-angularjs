package com.waleedkhan.sample.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.waleedkhan.sample.dao.MyBatisMapper;
import com.waleedkhan.sample.model.Batch;
import com.waleedkhan.sample.model.Status;

@MyBatisMapper
public interface BatchDao {
    String BATCH_IDS = "batchIds";
    String STATUS = "status";

    List<Batch> getBatches();

    void updateBatches(
            @Param(BATCH_IDS) List<String> batchIds,
            @Param(STATUS) Status status);
}
