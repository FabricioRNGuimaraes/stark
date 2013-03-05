package stark.dao;

import java.util.List;

import stark.entity.ItemBought;

public interface IItemBoughtDAO extends IGenericDAO {

	List<ItemBought> findAll();
}
