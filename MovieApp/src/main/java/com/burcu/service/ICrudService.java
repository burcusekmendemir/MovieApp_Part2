package com.burcu.service;

import com.burcu.entity.User;

import java.util.List;
import java.util.Optional;

public interface ICrudService <T,ID> {
    T save(T t);
    T update(T t);
    Iterable<T> saveAll(Iterable<T> t);
    T deleteById(ID id); //statusunu değiştirip yapacağız
    Optional<T> findById(ID id);
    List<T> findAll();

}
