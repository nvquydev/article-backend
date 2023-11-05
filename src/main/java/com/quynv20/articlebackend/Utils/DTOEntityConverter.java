package com.quynv20.articlebackend.Utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public final class DTOEntityConverter {

    // Generic method to convert an entity to a DTO
    public static <D, E> D convertToDTO(E entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            // Handle exception or rethrow as a runtime exception
            throw new RuntimeException("Error during DTO conversion", e);
        }
    }

    // Generic method to convert a DTO to an entity
    public static <D, E> E convertToEntity(D dto, Class<E> entityClass) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dto, entity);
            return entity;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            // Handle exception or rethrow as a runtime exception
            throw new RuntimeException("Error during entity conversion", e);
        }
    }
}
