package com.example.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T>{

    private final List<T> db = new ArrayList<>();
    private int index = 0;

    @Override
    public Optional<T> findByID(int index) {
        return db.stream().filter(i -> i.getIndex() == index).findFirst();
    }

    @Override
    public T save(T entity) {

        var optionalENtity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        if (optionalENtity.isEmpty()){ // db에 data가 없을 때
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;

        } else { // db에 data가 존재할 때
            var preIndex = optionalENtity.get().getIndex();
            entity.setIndex(preIndex);
            deleteById(preIndex);

            db.add(entity);
            return entity;
        }
    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(i -> i.getIndex() == index).findFirst();
        if (optionalEntity.isPresent()){
            db.remove(optionalEntity.get());
        }
    }

    @Override
    public List<T> findAll() {
        return db;
    }
}
