package stark.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import stark.entity.ClassRoom;
import stark.entity.Course;

//@Repository
public class ClassRoomDAO extends GenericDAO {//implements ICourseDAO {

//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	protected EntityManager getEntityManager() {
//		return this.entityManager;
//
//	}
	
	Configuration configuration = new AnnotationConfiguration();
	Session session;
	
	public ClassRoomDAO() {
		
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		session = factory.openSession();
	}
	
//	@Override
	public List<ClassRoom> findAll() {
		
		try {
			
			return session.createQuery("from " + ClassRoom.class.getName() + " c where c.active = :active").setParameter("active", true).list();
		} catch(Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}
