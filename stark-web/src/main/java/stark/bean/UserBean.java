package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import stark.entity.User;
import stark.enums.UserTypeEnum;
import stark.service.IUserService;

@Named(value="userBean")
@Scope("view")
public class UserBean extends GenericBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private IUserService userService;

	private List<User> users = new ArrayList<User>();
	private User user = new User();
	private List<UserTypeEnum> userTypes = new ArrayList<UserTypeEnum>();

	private Boolean newEntity = false;
	
	@PostConstruct
	public void initialize() {
		
		users = userService.findAllActive();
		
		for(UserTypeEnum type : UserTypeEnum.values()) {
			userTypes.add(type);
		}
	}

	public void save(ActionEvent event) {

		if(validate()) {
			
			if(!newEntity && user.getId() == null) {
	
				addInfoGrowlMessage("User " + user.getName() + " saved!");
				userService.save(user);
				users.add(user);
			} else {
	
				userService.update(user);
				addInfoGrowlMessage("User " + user.getName() + " updated!");
			}
			
			clean(null);
		}
	}
	
	public void remove(ActionEvent event) {
		
		userService.remove(user);
		users.remove(user);
		addInfoGrowlMessage("User " + user.getName() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		user = new User();
		newEntity = false;
	}
	
	public void prepareEdit() {
		newEntity = true;
	}
	
	private boolean validate() {
		
		if(user == null || user.getName() == null || user.getName().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()) {
			addErrorGrowlMessage("Check Fields with *");
			return false;
		}
		
		if(!newEntity && userService.findByName(user.getName()) != null) {
			addErrorGrowlMessage("User already registred");
			return false;			
		}
		
		return true;
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

	public List<UserTypeEnum> getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(List<UserTypeEnum> userTypes) {
		this.userTypes = userTypes;
	}

	public Boolean getNewEntity() {
		return newEntity;
	}

	public void setNewEntity(Boolean newEntity) {
		this.newEntity = newEntity;
	}

}