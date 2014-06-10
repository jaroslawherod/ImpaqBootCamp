package boot.camp.springjsf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import boot.camp.repository.H2Repository;
import boot.camp.repository.IRepository;
import boot.camp.repository.RepositoryException;
import boot.camp.repository.RepositoryFactory;
import boot.camp.service.RepositoryService;

@Configuration
@ComponentScan("boot.camp.springjsf")
@EnableWebMvc
public class Config {
	
	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		 UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		 resolver.setPrefix("/WEB-INF/views/");
		 resolver.setSuffix(".jsp");
		 resolver.setViewClass(JstlView.class);
		 return resolver;
	}
	
	@Bean
	public RepositoryService repositoryService() throws ConfigServiceException {
		RepositoryFactory factory = new RepositoryFactory();
		String[] parameters = new String[]{"jdbc:h2:~/test", "sa", ""};
		IRepository repository;
		try {
			repository = factory.createRepository(H2Repository.class, parameters );
			return new RepositoryService(repository);
		} catch (RepositoryException e) {
			throw new ConfigServiceException(e);
		}
	}

}
