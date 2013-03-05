package stark.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;

import stark.entity.StudentCourse;

//@Repository
public class StudentCourseDAO extends GenericDAO { // implements IStudentDAO

//	@PersistenceContext
	private EntityManager entityManager;
//	
//	protected EntityManager getEntityManager() {
//		return this.entityManager;
//
//	}
	
	Configuration configuration = new AnnotationConfiguration();
	Session session;
	
	public StudentCourseDAO() {
		
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		session = factory.openSession();
	}
	
	public List<StudentCourse> findAll() {
		try {
			
			return session.createQuery("from " + StudentCourse.class.getName()).list();
		} catch(Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
