package com.tiagotibaes.utils.converter;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ConverterObjects {

    private final ModelMapper mapper;

    public ConverterObjects() {
        this.mapper = new ModelMapper();
    }

    public <S, T> List<T> converterListOfObjectsToListOfObjects(List<S> source, Class<T> outPutClass) {
        if (null == source || source.isEmpty()) {
            throw new RuntimeException("The list cannot be empty");
        }
        return source.stream().map(entity -> mapper.map(entity, outPutClass)).collect(Collectors.toList());
    }

    public <S, T> T converterObjectToObject(S source, Class<T> outPutClass) {
        if (null == source) {
            throw new RuntimeException("Source cannot be null");
        }
        return mapper.map(source, outPutClass);
    }

}
