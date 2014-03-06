package stark.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

public abstract class GenericBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
//	protected static Logger log = LoggerFactory.getLogger(AbstractBean.class);
	
	@PostConstruct
	public abstract void initialize();

	protected void executeJavascript(String script) {
		getRequestContext().execute(script);
	}
	
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

	protected FacesContext getFacesContext() { 
		return FacesContext.getCurrentInstance(); 
	}
	
	private RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}
	
//	protected ServletContext getServletContext() {
//		ServletContext s = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//		return s;
//	}
//	
	protected HttpServletRequest getHttpRequest(){
		HttpServletRequest request  = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return request;
	}
//	
//	protected HttpServletResponse getHttpResponse(){
//		HttpServletResponse response  = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//		return response;
//	}
//	
	protected HttpSession getSession(){
		return getHttpRequest().getSession();
	}	
}