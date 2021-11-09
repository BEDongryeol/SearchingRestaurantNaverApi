package com.example.restaurant.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {

    Optional<T> findByID(int index);
    T save(T data);
    void deleteById(int index);
    List<T> findAll();

}
