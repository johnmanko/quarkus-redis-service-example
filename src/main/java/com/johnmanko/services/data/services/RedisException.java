package com.johnmanko.services.data.services;

public class RedisException extends Exception {
    
    private Exception source;
    
    public RedisException(Exception ex) {
        this.source = ex;
    }

    /**
     * @return the source
     */
    public Exception getSource() {
        return source;
    }
    
}
