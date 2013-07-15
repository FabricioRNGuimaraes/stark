package stark.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public abstract class AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
//	protected static Logger log = LoggerFactory.getLogger(AbstractBean.class);
	
	@PostConstruct
	public abstract void initialize();

	protected void addInfoGrowlMessage(String message) {
		getFacesContext().addMessage(null, createFacesMessage(message, FacesMessage.SEVERITY_INFO));
	}
	
	protected void addErrorGrowlMessage(String message) {
		getFacesContext().addMessage(null, createFacesMessage(message, FacesMessage.SEVERITY_ERROR));
	}
	
	private FacesMessage createFacesMessage(String message, Severity severity) {
		FacesMessage facesMessage = new FacesMessage(message);
		facesMessage.setSeverity(severity);
		
		return facesMessage;
	}

	private FacesContext getFacesContext() { 
		return FacesContext.getCurrentInstance(); 
	}
	
//	protected ServletContext getServletContext() {
//		ServletContext s = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//		return s;
//	}
//	
//	protected HttpServletRequest getHttpRequest(){
//		HttpServletRequest request  = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		return request;
//	}
//	
//	protected HttpServletResponse getHttpResponse(){
//		HttpServletResponse response  = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//		return response;
//	}
//	
//	protected HttpSession getSession(){
//		return getHttpRequest().getSession();
//	}	
}