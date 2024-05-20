
package com.johnmanko.services.data.services;

public class DataException extends Exception {
    
    private final DataRecord data;
    
    public DataException(DataRecord data) {
        this.data = data;
    }

    /**
     * @return the existing
     */
    public DataRecord getData() {
        return data;
    }   
    
    
}
