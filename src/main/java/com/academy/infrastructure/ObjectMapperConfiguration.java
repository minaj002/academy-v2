package com.academy.infrastructure;


import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return source.format(formatter);
            }
        });
        converterFactory.registerConverter(new BidirectionalConverter<LocalDateTime, LocalDateTime>() {
            @Override
            public LocalDateTime convertTo(LocalDateTime source, Type<LocalDateTime> destinationType, MappingContext mappingContext) {
                return source;
            }

            @Override
            public LocalDateTime convertFrom(LocalDateTime source, Type<LocalDateTime> destinationType, MappingContext mappingContext) {
                return source;
            }
        });

        converterFactory.registerConverter(new BidirectionalConverter<LocalDate, LocalDate>() {
            @Override
            public LocalDate convertTo(LocalDate source, Type<LocalDate> destinationType, MappingContext mappingContext) {
                return source;
            }

            @Override
            public LocalDate convertFrom(LocalDate source, Type<LocalDate> destinationType, MappingContext mappingContext) {
                return source;
            }
        });

    }

    protected void fieldMapping(ClassMapBuilder<A, B> classMapBuilder) {
        classMapBuilder.byDefault();
    }

}
