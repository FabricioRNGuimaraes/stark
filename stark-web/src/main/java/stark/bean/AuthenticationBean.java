package stark.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="authenticationBean")
@SessionScoped
public class AuthenticationBean extends GenericBean {

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	
	public void initialize() {

//		dao = new UserDAO();
	}
	
	public String login() {
		addInfoGrowlMessage("User " + login + " logged in!");
		return "loginSuccess";
//		if(dao.login(login, password)) {
//		getFacesContext().getExternalContext().getSessionMap().put("username", login);
//			addInfoGrowlMessage("User " + login + " logged in!");
//			return "loginSuccess";
//		} else {
//			addErrorGrowlMessage("User or password are wrong!");
//		}
		
//		return "";
	}

	public String logout() {
		
//		getSession().invalidate();
//		getFacesContext().getExternalContext().invalidateSession();
		
		return "logoutSuccess";
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}