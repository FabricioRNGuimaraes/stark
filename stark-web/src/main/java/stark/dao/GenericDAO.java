package stark.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import stark.entity.GenericEntity;

public class GenericDAO<T extends GenericEntity> implements IGenericDAO<T>, Serializable {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	protected final Class<T> classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	private EntityManagerFactory entityManagerFactory  = Persistence.createEntityManagerFactory("persistenceUnit");;
	private EntityManager entityManager;	
	
	protected EntityManager getEntityMananger() {
		return entityManager;
	}
	
	public GenericDAO() {
		entityManager = entityManagerFactory.createEntityManager();
//		em.close();
//		emf.close();
	}
	
	@Override
	public boolean save(T entity) {
		
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.flush();
			return true;
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
			
		} finally {
			entityManager.getTransaction().commit();
		}
	}

	@Override
	public boolean update(T entity) {
		
		try {
			
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.flush();
			return true;
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
			
		} finally {
			entityManager.getTransaction().commit();
		}
	}
	
	@Override
	public boolean remove(T entity) {
		
		try {
			
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			entityManager.flush();
			return true;
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
			
		} finally {
			entityManager.getTransaction().commit();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		
		List<T> entities = entityManager.createQuery("from " + classe.getName()).getResultList();
		return entities;
	}

	@Override
	public T findById(Integer id) {
		
		T entity = entityManager.find(classe, id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllActive() {
		
		List<T> entities = entityManager.createQuery("from " + classe.getName() +  " t where t.active = :active").setParameter("active", true).getResultList();
		return entities;
	}
}
