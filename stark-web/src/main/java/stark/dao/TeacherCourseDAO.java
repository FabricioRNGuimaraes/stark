package stark.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import stark.entity.StudentCourse;
import stark.entity.TeacherCourse;

//@Repository
public class TeacherCourseDAO extends GenericDAO { // implements IStudentDAO

//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	protected EntityManager getEntityManager() {
//		return this.entityManager;
//
//	}
	
	Configuration configuration = new AnnotationConfiguration();
	Session session;
	
	public TeacherCourseDAO() {
		
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		session = factory.openSession();
	}
	
	public List<TeacherCourse> findAll() {

		try {
			
			return session.createQuery("from " + TeacherCourse.class.getName()).list();
		} catch(Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
