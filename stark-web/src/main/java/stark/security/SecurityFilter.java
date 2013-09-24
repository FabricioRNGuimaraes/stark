package stark.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	
		System.out.println(filterConfig);

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//	
//		HttpSession httpSession = httpRequest.getSession();
//		String username = (String) httpSession.getAttribute("username");
//		Principal principal = httpRequest.getUserPrincipal();
//		
//		if(username != null && !username.isEmpty()) {
//			
////			System.out.println(principal.getName());
//		} else {
//			httpSession.invalidate();
//			httpResponse.sendRedirect("/loginError.xhtml");
//		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("destroi filter");

	}
	
	private void removehttpCache(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html; charset=UTF-8");
        response.addHeader("pragma", "no-cache, no-store, must-revalidate, max-age=0, s-max-age=0, proxy-revalidate");
        response.addDateHeader("Expires", 0);
        response.addHeader("cache-control", "no-cache, no-store, must-revalidate, max-age=0, s-max-age=0, proxy-revalidate");
    }

}
