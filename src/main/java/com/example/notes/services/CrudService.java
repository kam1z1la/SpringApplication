package com.example.notes.services;

import java.util.Map;

public interface CrudService<K, V> {
    Map<K, V> listAll();

    V add(V entity);

    void deleteById(K id) throws IllegalAccessException;

    void update(V entity) throws IllegalAccessException;

    V getById(K id) throws IllegalAccessException;
}
