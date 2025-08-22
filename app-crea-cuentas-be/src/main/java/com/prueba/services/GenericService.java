package com.prueba.services;

import java.util.List;

public interface GenericService<T, ID> {

    List<T> findAll();
    T findById(ID id);
    T save(T t);
    void delete(ID id);
    T update(ID id, T t);

}
