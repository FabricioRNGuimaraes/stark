package stark.dao;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import stark.entity.AbstractEntity;
import stark.entity.Teacher;

public class GenericDAO implements IGenericDAO, Serializable {

	Configuration configuration = new AnnotationConfiguration();
	Session session;
	
	public GenericDAO() {
		
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		session = factory.openSession();
	}
	
	@Override
	public boolean save(AbstractEntity entity) {
		
		Transaction tx = session.beginTransaction();
		try {
			
			session.save(entity);
			tx.commit();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		return true;
		
	}
	
	@Override
	public boolean remove(AbstractEntity entity) {
		
		Transaction tx = session.beginTransaction();
		try {
			
			session.delete(entity);
			tx.commit();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		return true;
		
	}
	
	@Override
	public boolean update(AbstractEntity entity) {
		
		Transaction tx = session.beginTransaction();
		try {
			
			session.merge(entity);
			tx.commit();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			tx.rollback();
			return false;
		}
//		session.close();
		return true;
		
//		session.persist(entity);
//		return true;
	}
	
	@Override
	public AbstractEntity findById(Class<AbstractEntity> classe, Integer id) {
		
		try {
			return (AbstractEntity) session.load(classe, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AbstractEntity> findAll(Class<AbstractEntity> classe, Boolean active) {
		
		try {
			
			return session.createQuery("from " + classe.getName() + " t where t.active = :active").setParameter("active", active).list();
		} catch(Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AbstractEntity> findAll(Class<AbstractEntity> classe) {
		
		try {
			
			return session.createQuery("from " + classe.getName()).list();
		} catch(Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}
