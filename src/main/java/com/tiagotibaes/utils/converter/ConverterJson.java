package com.tiagotibaes.utils.converter;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConverterJson {

    private final ObjectMapper objectMapper;

    public String objectToJson(final Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException iox){
            throw new RuntimeException(iox);
        }
    }

    public <T> T toObject(final String pathJson, Class<T> classObject){
        try {
            return  objectMapper.readValue(pathJson, classObject);
        }catch (IOException iox){
            log.error("Error parsing Json to Object: {}", pathJson, iox);
            return null;
        }
    }

    public <T> T jsonToObject(final String pathJson, Class<T> classObject){
        return toObject(pathJson, classObject);
    }

    private String readJson(final String pathJson){
        try{
            return Resources.toString(this.getClass().getClassLoader().getResource(pathJson).toURI().toURL(), Charsets.UTF_8);
        }catch(IOException | URISyntaxException e){
            throw new RuntimeException((e));
        }
    }


}
