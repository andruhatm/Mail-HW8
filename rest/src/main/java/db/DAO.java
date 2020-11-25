package db;

import java.util.List;

public interface DAO<T> {

	List<T> getAll();

	List<T> getByCompany(String str);

	boolean add(T entity);

	boolean delete(String str);
}
