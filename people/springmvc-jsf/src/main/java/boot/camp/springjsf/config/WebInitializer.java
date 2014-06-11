package boot.camp.springjsf.config;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

;

public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(Config.class);
		context.setServletContext(servletContext);
		context.refresh();

		servletContext.addListener(new RequestContextListener());

		servletContext.addListener(new ContextLoaderListener(context));
		servletContext.setInitParameter("defaultHtmlEscape", "true");

		Dynamic springServlet = servletContext.addServlet("dispatcher",
				new DispatcherServlet(context));
		springServlet.addMapping("/");
		springServlet.setLoadOnStartup(1);

//		 Dynamic facesServlet = servletContext.addServlet("faces", new FacesServlet());
//		 facesServlet.addMapping("*.jsf");
//		 facesServlet.setLoadOnStartup(1);
	}
}
