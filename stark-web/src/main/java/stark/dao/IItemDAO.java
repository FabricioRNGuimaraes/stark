package stark.dao;

import java.util.List;

import stark.entity.Item;

public interface IItemDAO extends IGenericDAO {

	List<Item> findAll();
}
