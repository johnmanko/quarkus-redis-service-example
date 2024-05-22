package com.johnmanko.services.data.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.Optional;

@RequestScoped
public class DataService {

    @Inject
    private RedisService redisService;

    public Collection<DataRecord> getAllValues() throws RedisException {

        return this.redisService.getAllValues();

    }

    public DataRecord setData(DataRecord data) throws RedisException {

        Optional<DataRecord> maybeExisting = this.getData(data.getKey());

        if (maybeExisting.isPresent()) {
            return maybeExisting.get();
        }

        this.redisService.setData(data);

        return data;

    }

    public Optional<DataRecord> getData(String key) throws RedisException {

        return this.redisService.getData(key);

    }
}
