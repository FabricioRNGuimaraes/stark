package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.entity.User;

@ManagedBean(name="userBean")
@ViewScoped
public class UserBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private List<User> users = new ArrayList<User>();
	private User user = new User();

	private Boolean newEntity = false;
	
	public void initialize() {

	}

	public void save(ActionEvent event) {

		if(!newEntity && user.getId() == null) {

			addInfoGrowlMessage("User " + user.getName() + " saved!");
//			dao.save(user);
		} else {
			
			addInfoGrowlMessage("User " + user.getName() + " updated!");
//			dao.update(user);
		}
//		users = dao.findAll();
	}
	
	public void remove(ActionEvent event) {
		
//		dao.remove(user);
//		users = dao.findAll();
		addInfoGrowlMessage("User " + user.getName() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		clean();
	}
	
	private void clean() {
		
		user = new User();
		newEntity = false;
	}
	
	public void prepareEdit() {
		
		newEntity = true;
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

	public Boolean getNewEntity() {
		return newEntity;
	}

	public void setNewEntity(Boolean newEntity) {
		this.newEntity = newEntity;
	}

}