package anseev.demo.service;

import java.util.List;

public interface ServiceTemplate<T, V> {
    T save(T obj);
    void delete(T obj);
    T update(T obj);
    T get(V id);
    List<T> getAll();
}
