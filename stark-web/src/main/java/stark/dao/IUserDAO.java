package stark.dao;

import java.util.List;

import stark.entity.User;

public interface IUserDAO extends IGenericDAO{

	List<User> findAll();
}
