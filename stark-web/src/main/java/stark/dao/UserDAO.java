package stark.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import stark.entity.User;

//@Repository
public class UserDAO extends GenericDAO implements IUserDAO {

//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	protected EntityManager getEntityManager() {
//		return this.entityManager;
//
//	}
	
	Configuration configuration = new AnnotationConfiguration();
	Session session;
	
	public UserDAO() {
		
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		session = factory.openSession();
	}
	
	@Override
	public List<User> findAll() {
		
		try {
			
			return session.createQuery("from " + User.class.getName()).list();
		} catch(Exception e) {
			return Collections.emptyList();
		}
	}

	public boolean login(String login, String password) {
		
		List<User> users = session.createQuery("from " + User.class.getName()).list();
		
		if(users != null) {
			for(User userBD : users) {
				if(userBD.getName().trim().equalsIgnoreCase(login.trim()) && userBD.getPassword().trim().equalsIgnoreCase(password.trim())) {
					return true;
				}
			}
		}

		return false;
	}
	
}
