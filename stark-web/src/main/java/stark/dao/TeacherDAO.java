package stark.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import stark.entity.Teacher;

//@Repository
public class TeacherDAO extends GenericDAO implements ITeacherDAO {

//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	protected EntityManager getEntityManager() {
//		return this.entityManager;
//
//	}
	
	Configuration configuration = new AnnotationConfiguration();
	Session session;
	
	public TeacherDAO() {
		
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		session = factory.openSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> findAll() {
		
		try {
			
			return session.createQuery("from " + Teacher.class.getName() + " t where t.active = :active").setParameter("active", true).list();
		} catch(Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
