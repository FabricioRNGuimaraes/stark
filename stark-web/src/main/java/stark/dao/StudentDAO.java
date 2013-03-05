package stark.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import stark.entity.AbstractEntity;
import stark.entity.Student;

//@Repository
public class StudentDAO extends GenericDAO implements IStudentDAO {

//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	protected EntityManager getEntityManager() {
//		return this.entityManager;
//
//	}
	
	Configuration configuration = new AnnotationConfiguration();
	Session session;
	
	public StudentDAO() {
		
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
//		session.close();
		return true;
		
//		session.persist(entity);
//		return true;
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
//		session.close();
		return true;
		
//		session.persist(entity);
//		return true;
	}
	
	@Override
	public boolean update(AbstractEntity entity) {
		
		Transaction tx = session.beginTransaction();
		try {
			
			session.update(entity);
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
	public List<Student> findAll() {
		
		try {
			
			return session.createQuery("from " + Student.class.getName() + " s where s.active = :active").setParameter("active", true).list();
		} catch(Exception e) {
			return Collections.emptyList();
		}
	}

}
