package com.rzerosystems.marathonapp.service;

import com.rzerosystems.marathonapp.model.Result;

import java.util.List;

public interface ResultService {

    List<Result> getResults();

    Result getResultById(int identifier);

    Result save(Result result);

    void update(int identifier, Result result);

    void delete(int identifier);
}
