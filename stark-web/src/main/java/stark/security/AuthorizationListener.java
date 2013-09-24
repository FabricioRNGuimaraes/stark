package stark.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.primefaces.context.RequestContext;

public class AuthorizationListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
//		FacesContext facesContext = event.getFacesContext();
//		String currentPage = facesContext.getViewRoot().getViewId();
//
//		boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1);
//		HttpSession session = (HttpSession) facesContext.getExternalContext()
//				.getSession(false);
//
//		if (session == null) {
//			NavigationHandler nh = facesContext.getApplication()
//					.getNavigationHandler();
//			nh.handleNavigation(facesContext, null, "loginPage");
//		}
//
//		else {
//			Object currentUser = session.getAttribute("username");
//
//			if (!isLoginPage && (currentUser == null || currentUser == "")) {
//				NavigationHandler nh = facesContext.getApplication()
//						.getNavigationHandler();
//				nh.handleNavigation(facesContext, null, "loginPage");
//			}
//		}
		
		System.out.println(event);

	}

	@Override
	public void beforePhase(PhaseEvent event) {
	
//		FacesContext facesContext = phaseEvent.getFacesContext();
//        Iterator<FacesMessage> listIterator = facesContext.getMessages();
//        List<String> errorMessages = new ArrayList<String>();
//        List<String> infoMessages = new ArrayList<String>();
//        while (listIterator.hasNext()) {
//            FacesMessage msg = listIterator.next();
//            if (FacesMessage.SEVERITY_ERROR.equals(msg.getSeverity())) {
//                errorMessages.add("'" + msg.getSummary().replace("\\", "\\\\").replace("'", "\'") + "'");
//            } else if (FacesMessage.SEVERITY_INFO.equals(msg.getSeverity())) {
//                infoMessages.add("'" + msg.getSummary() + "'");
//            }
//        }
//        RequestContext requestContext = RequestContext.getCurrentInstance();
//        if (!errorMessages.isEmpty()) {
//            requestContext.execute(String.format("Message.showErrors(%s);", errorMessages));
//        } else if(!infoMessages.isEmpty()) {
//            requestContext.execute(String.format("Message.showInfos(%s);", infoMessages));
//        }

		System.out.println(event);
	}

	@Override
	public PhaseId getPhaseId() {
		// return PhaseId.RESTORE_VIEW;
		
//		return PhaseId.RENDER_RESPONSE;
		return null;
	}

}
