package com.vetsetgo.service;

import java.util.List;

// Abstraction: Interface for persistent data storage
public interface DataHandler<T> {
    void save(List<T> data, String filename);
    List<T> load(String filename, Class<T[]> clazz);
}