package com.academy.infrastructure;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrikoObjectMapper {

    private final MapperFacade mapperFacade;

    @Autowired
    public OrikoObjectMapper(Optional<List<ObjectMapperConfiguration>> configs) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        if(configs.isPresent()) {
            for (ObjectMapperConfiguration config : configs.get()) {
                config.mapping(mapperFactory);
            }
        }
        this.mapperFacade = mapperFactory.getMapperFacade();
    }

    public <IN, OUT> OUT map(IN in, Class<OUT> outClass) {
        return mapperFacade.map(in, outClass);
    }

    public <IN, OUT> List<OUT> map(List<IN> in, Class<OUT> outClass) {
        return mapperFacade.mapAsList(in, outClass);
    }

}
