package stark.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;


//@ManagedBean(name="applicationBean")
//@ApplicationScoped
@Named(value="applicationBean")
@Scope("singleton")
public class ApplicationBean  extends GenericBean {

	private static final long serialVersionUID = 1L;


	@Override
	public void initialize() {

	}


}
