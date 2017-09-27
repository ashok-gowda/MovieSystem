package authenciator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component("authenticationInterceptor")
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	 @Override
	 public boolean preHandle(HttpServletRequest request,
	   HttpServletResponse response, Object handler) throws Exception
	 {
	  String uri = request.getRequestURI();
	  if(!(uri.startsWith("/user/")|| uri.startsWith("/resources")))
	  {
	   String userData = (String) request.getSession().getAttribute("LOGGEDIN_USER");
	   if(userData == null)
	   {
	    response.sendRedirect("/user/");
	    return false;
	   }   
	  }
	  return true;
	 }
}
