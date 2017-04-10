package com.weststein.infrastructure;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrikoObjectMapper {

    private final MapperFacade mapperFacade;

    @Autowired
    public OrikoObjectMapper(List<ObjectMapperConfiguration> configs) {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        for(ObjectMapperConfiguration config: configs) {
            config.mapping(mapperFactory);
        }

        this.mapperFacade = mapperFactory.getMapperFacade();
    }

    public <IN, OUT> OUT map(IN in, Class<OUT> outClass) {
        return mapperFacade.map(in, outClass);
    }

}
