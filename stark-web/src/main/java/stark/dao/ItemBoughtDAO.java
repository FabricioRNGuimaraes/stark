package stark.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import stark.entity.ItemBought;

//@Repository
public class ItemBoughtDAO extends GenericDAO implements IItemBoughtDAO {

//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	protected EntityManager getEntityManager() {
//		return this.entityManager;
//
//	}
	
	Configuration configuration = new AnnotationConfiguration();
	Session session;
	
	public ItemBoughtDAO() {
		
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		session = factory.openSession();
	}
	
	@Override
	public List<ItemBought> findAll() {
		
		try {
			
			return session.createQuery("from " + ItemBought.class.getName()).list();
		} catch(Exception e) {
			return Collections.emptyList();
		}
	}

}
