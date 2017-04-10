package com.weststein.infrastructure;


import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class ObjectMapperConfiguration <A, B> {

    public abstract Class<A> getA();
    public abstract Class<B> getB();

    public void mapping(MapperFactory mapperFactory) {

        ClassMapBuilder<A, B> classMapBuilder = mapperFactory.classMap(getA(), getB());
        setupConverters(mapperFactory.getConverterFactory());
        fieldMapping(classMapBuilder);
        classMapBuilder.register();

    }

    protected void setupConverters(ConverterFactory converterFactory) {

        converterFactory.registerConverter(new BidirectionalConverter<String, LocalDate>() {
            @Override
            public LocalDate convertTo(String source, Type<LocalDate> destinationType, MappingContext mappingContext) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                return LocalDate.parse(source, formatter);
            }

            @Override
            public String convertFrom(LocalDate source, Type<String> destinationType, MappingContext mappingContext) {
                return source.toString();
            }
        });

    }

    protected void fieldMapping(ClassMapBuilder<A, B> classMapBuilder) {
        classMapBuilder.byDefault();
    }

}
