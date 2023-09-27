package com.example.CinemaApp.Service;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;


public class TicketIdGenerator implements IdentifierGenerator {

    public static final String generatorName = "myGenerator";
    private static final long LIMIT = 10000000000L;
    private static long last = 0;
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object object) {
        long id = System.currentTimeMillis() % LIMIT;
        if ( id <= last ) {
            id = (last + 1) % LIMIT;
        }
        return last = id;
    }
}
