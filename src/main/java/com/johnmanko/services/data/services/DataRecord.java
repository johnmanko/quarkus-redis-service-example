package com.johnmanko.services.data.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

public class DataRecord implements Serializable {

    public static final String DATA_KEY_PREFIX = "data:";

    private String key;
    private String value;

    public DataRecord() {}
    
    public DataRecord(
            String key,
            String value) {

        this.key = key;
        this.value = value;
        
    }
    
    @JsonIgnore
    public String getObjectKey() {
        return buildKey(this.getKey());
    }

    public static String buildKey(String dataKey) {
        return DATA_KEY_PREFIX.concat(dataKey);
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

}
