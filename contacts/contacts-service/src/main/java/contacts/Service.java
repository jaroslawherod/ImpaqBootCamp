package contacts;

import java.util.List;

public interface Service<T> {
    public T create(T obj) throws Exception;

    public List<T> findAll() throws Exception;
}