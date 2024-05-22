package com.johnmanko.services.data.services;

import io.quarkus.logging.Log;
import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.KeyCommands;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class RedisService {

    ReactiveRedisDataSource ds;

    KeyCommands<String> keyCommands;
    ValueCommands<String, DataRecord> valueCommands;

    public RedisService(RedisDataSource ds) {

        keyCommands = ds.key();
        valueCommands = ds.value(DataRecord.class);

    }

    public List<String> getAllKeys() throws RedisException {

        try {
            return this.keyCommands.keys(DataRecord.buildKey("*"));
        } catch (Exception ex) {
            log("Error obtaining keys", ex);
            throw new RedisException(ex);
        }

    }

    public Collection<DataRecord> getAllValues() throws RedisException {
        try {
            List<String> keys = getAllKeys();
            if (keys == null || keys.isEmpty()) {
                return Collections.emptyList();
            }
            return this.valueCommands.mget(keys.toArray(new String[0])).values().stream().filter(v -> v != null).collect(Collectors.toList());
        } catch (RedisException ex) {
            log("Error obtaining values", ex);
            throw ex;
        } catch (Exception ex) {
            log("Error obtaining values", ex);
            throw new RedisException(ex);
        }
    }

    public void setData(DataRecord data) throws RedisException {

        try {
            this.valueCommands.set(data.getObjectKey(), data);
        } catch (Exception ex) {
            log("Error setting data ".concat(data.getObjectKey()), ex);
            throw new RedisException(ex);
        }

    }

    public Optional<DataRecord> getData(String key) throws RedisException {

        DataRecord o = null;

        try {
            o = this.valueCommands.get(DataRecord.buildKey(key));
        } catch (Exception ex) {
            log("Error obtaining data for key ".concat(key), ex);
            throw new RedisException(ex);
        }

        return Optional.ofNullable(o);

    }

    private void log(String message, Exception ex) {
        Log.error(message, ex);
    }

}
