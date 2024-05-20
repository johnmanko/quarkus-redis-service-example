package com.johnmanko.services.data.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.Collection;

@RequestScoped
public class DataService {
    
    @Inject
    private RedisService redisService;        
   
    public Collection<DataRecord> getAllValues() throws RedisException {
        
        return this.redisService.getAllValues();
        
    }
    
}
