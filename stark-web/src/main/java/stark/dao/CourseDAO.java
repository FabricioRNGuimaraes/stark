package stark.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import stark.entity.Course;

//@Repository
public class CourseDAO extends GenericDAO {//implements ICourseDAO {

//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	protected EntityManager getEntityManager() {
//		return this.entityManager;
//
//	}
	
	Configuration configuration = new AnnotationConfiguration();
	Session session;
	
	public CourseDAO() {
		
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		session = factory.openSession();
	}
	
//	@Override
	public List<Course> findAll() {
		
		try {
			
			return session.createQuery("from " + Course.class.getName() + " c where c.active = :active")
										.setParameter("active", true).list();
		} catch(Exception e) {
			return Collections.emptyList();
		}
	}
}
