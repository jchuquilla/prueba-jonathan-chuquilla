package com.prueba.services;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface GenericService<T, ID> {

    List<T> findAll();
    List<T> findAll(Sort sort);
    T findById(ID id);
    T save(T t);
    void delete(ID id);
    T update(ID id, T t);

}
