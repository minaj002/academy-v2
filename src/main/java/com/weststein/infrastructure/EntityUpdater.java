package com.weststein.infrastructure;

import com.weststein.repository.Person;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Objects;

@Component
public class EntityUpdater {

    public void apply(Person from, Person to){

        BeanWrapper wrapperFrom = new BeanWrapperImpl(from);
        BeanWrapper wrapperTo = new BeanWrapperImpl(to);

        for (Field field:from.getClass().getDeclaredFields()) {
            if("dirty".equals(field.getName())){
                continue;
            }
            Object value = wrapperFrom.getPropertyValue(field.getName());
            if(Objects.nonNull(value)&& (value instanceof String || value instanceof Long)) {
                wrapperTo.setPropertyValue(field.getName(), value);
            } else if(Objects.nonNull(value)&& (value.getClass().isEnum() )){
                wrapperTo.setPropertyValue(field.getName(), value);
            }
            else if(Objects.nonNull(value)) {
                for (Field insideField: value.getClass().getDeclaredFields()) {
                    Object insideValue = wrapperFrom.getPropertyValue(field.getName()+"."+insideField.getName());
                    if(Objects.nonNull(insideValue)) {
                        wrapperTo.setPropertyValue(field.getName()+"."+insideField.getName(), insideValue);
                    }
                }
            }
        }
    }
}
