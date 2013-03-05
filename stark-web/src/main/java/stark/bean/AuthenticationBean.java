package stark.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import stark.dao.UserDAO;

@ManagedBean(name="authenticationBean")
@SessionScoped
public class AuthenticationBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	
	private UserDAO dao;

	public String login() {

		if(dao.login(login, password)) {
			addInfoGrowlMessage("User " + login + " logged in!");
			return "loginSuccess";
		} else {
			addErrorGrowlMessage("User or password are wrong!");
		}
		
		return "";
	}

	public String logout() {
		
//		ServletAuthentication.logout(getHttpRequest());
//		getSession().invalidate();
		
		return "logoutSuccess";
	}
	
	public void initialize() {

		dao = new UserDAO();
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