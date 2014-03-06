package stark.dao;

import java.util.List;

import stark.entity.GenericEntity;

public interface IGenericDAO<T extends GenericEntity> {

	boolean save(T entity);
	boolean remove(T entity);
	boolean update(T entity);
	T findById(Integer id);
	List<T> findAll();
	List<T> findAllActive();
}
