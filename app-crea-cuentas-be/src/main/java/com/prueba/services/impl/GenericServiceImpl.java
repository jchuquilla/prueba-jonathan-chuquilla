package com.prueba.services.impl;

import com.prueba.repositories.GenericRepo;
import com.prueba.services.GenericService;

import java.util.List;

public abstract class GenericServiceImpl<T, ID> implements GenericService<T, ID> {

    protected abstract GenericRepo<T, ID> getRepo();

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) {
        getRepo().findById(id).orElseThrow(() -> new RuntimeException("No se encontro el registro para el ID: " + id));
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public T save(T t) {
        return getRepo().save(t);
    }

    @Override
    public void delete(ID id) {
        getRepo().findById(id).orElseThrow(() -> new RuntimeException("No se encontró registro con el ID: " + id));
        getRepo().deleteById(id);
    }

    @Override
    public T update(ID id, T t) {
        getRepo().findById(id).orElseThrow(() -> new RuntimeException("No se encontró registro con el ID: " + id));
        return getRepo().save(t);
    }
}
