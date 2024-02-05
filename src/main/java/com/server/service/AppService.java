package com.server.service;


import java.util.List;

public interface AppService<T, tId> {
    void create(T t);
    List<T> readAll();


    T read(tId id);


    boolean update(T t, tId id);


    boolean delete(tId id);

}
