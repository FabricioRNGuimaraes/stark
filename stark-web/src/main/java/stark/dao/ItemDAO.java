package stark.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import stark.entity.AbstractEntity;
import stark.entity.Item;
import stark.entity.ItemBought;
import stark.entity.Student;

//@Repository
public class ItemDAO extends GenericDAO implements IItemDAO {

//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	protected EntityManager getEntityManager() {
//		return this.entityManager;
//
//	}
	
	Configuration configuration = new AnnotationConfiguration();
	Session session;
	
	public ItemDAO() {
		
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		session = factory.openSession();
	}
	
	@Override
	public List<Item> findAll() {
		
		try {
			
			return session.createQuery("from " + Item.class.getName()).list();
		} catch(Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public boolean remove(AbstractEntity entity) {
		
		Item item = (Item) entity;
		
		List<ItemBought> itens = session.createQuery("from " + ItemBought.class.getName() + " s where s.item = :item").setParameter("item", item.getId()).list();
		
		for(ItemBought itemBought : itens) {
			
			super.remove(itemBought);
//			session.flush();
		}
		super.remove(item);
		return true;
	}
	
}
