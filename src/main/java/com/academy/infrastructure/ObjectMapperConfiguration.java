package com.academy.infrastructure;


import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class ObjectMapperConfiguration<A, B> {

    public abstract Class<A> getA();

    public abstract Class<B> getB();

    public void mapping(MapperFactory mapperFactory) {

        ClassMapBuilder<A, B> classMapBuilder = mapperFactory.classMap(getA(), getB());
        setupConverters(mapperFactory.getConverterFactory());
        fieldMapping(classMapBuilder);
        classMapBuilder.register();

    }

    protected void setupConverters(ConverterFactory converterFactory) {

        converterFactory.registerConverter(new BidirectionalConverter<String, Date>() {
            @Override
            public Date convertTo(String source, Type<Date> destinationType, MappingContext mappingContext) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                try {
                    return new SimpleDateFormat("dd-MM-yyyy").parse(source);
                } catch (ParseException e) {
                    return null;
                }
            }

            @Override
            public String convertFrom(Date source, Type<String> destinationType, MappingContext mappingContext) {
                return new SimpleDateFormat("dd-MM-yyyy").format(source);
            }
        });


    }

    protected void fieldMapping(ClassMapBuilder<A, B> classMapBuilder) {
        classMapBuilder.byDefault();
    }

}
