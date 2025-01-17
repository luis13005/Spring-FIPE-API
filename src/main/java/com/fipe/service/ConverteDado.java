package com.fipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDado implements IConverte{
   private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T converteDado(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> converteDadoLista(String json, Class<T> classe) {
        try {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, classe);
            return mapper.readValue(json,listType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
