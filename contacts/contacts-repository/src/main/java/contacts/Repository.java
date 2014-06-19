package contacts;

import java.util.List;

public interface Repository<T> {
    T save(T p) throws Exception;

    List<T> findAll() throws Exception;
}