package com.example.lewjun.service;

import java.util.List;
import java.util.Optional;

public interface IBaseService<T, ID> {
    T save(T t);

    List<T> queryAll();

    void delete(T t);

    void deleteById(ID id);

    Optional<T> findById(ID id);

    T update(T t);
}
