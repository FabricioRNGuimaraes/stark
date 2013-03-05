package stark.dao;

import java.util.List;

import stark.entity.AbstractEntity;

public interface IGenericDAO {

	boolean save(AbstractEntity entity);
	boolean remove(AbstractEntity entity);
	boolean update(AbstractEntity entity);
	AbstractEntity findById(Class<AbstractEntity> classe, Integer id);
	List<AbstractEntity> findAll(Class<AbstractEntity> classe);
	List<AbstractEntity> findAll(Class<AbstractEntity> classe, Boolean active);
}
