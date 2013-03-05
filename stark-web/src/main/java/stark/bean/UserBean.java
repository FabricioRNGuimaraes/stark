package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.dao.UserDAO;
import stark.entity.User;

@ManagedBean(name="userBean")
@ViewScoped
public class UserBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private List<User> users = new ArrayList<User>();
	private User user = new User();

	private UserDAO dao;
	
	private Boolean isEditEntity = false;
	
	public void initialize() {

		dao = new UserDAO();
		users = dao.findAll();
	}

	public void save(ActionEvent event) {

		if(!isEditEntity && user.getId() == null) {

			addInfoGrowlMessage("User " + user.getName() + " saved!");
			dao.save(user);
			users = dao.findAll();
		} else {
			
			addInfoGrowlMessage("User " + user.getName() + " updated!");
			dao.update(user);
		}
	}
	
	public void remove(ActionEvent event) {
		
		dao.remove(user);
		users = dao.findAll();
		addInfoGrowlMessage("User " + user.getName() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		clean();
	}
	
	private void clean() {
		
		user = new User();
		isEditEntity = false;
	}
	
	public void prepareEdit() {
		
		isEditEntity = true;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsEditEntity() {
		return isEditEntity;
	}

	public void setIsEditEntity(Boolean isEditEntity) {
		this.isEditEntity = isEditEntity;
	}

}