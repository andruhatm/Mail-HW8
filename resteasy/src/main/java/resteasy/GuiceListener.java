package resteasy;

import api.*;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import server.JacksonMessageBodyHandler;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;

public class GuiceListener extends GuiceResteasyBootstrapServletContextListener {

	@Override
	protected List<? extends Module> getModules(ServletContext context) {
		return Collections.singletonList(new GuiceModule());
	}

	@SuppressWarnings("rawtypes")
	private static final class GuiceModule extends AbstractModule {
		@SuppressWarnings("PointlessBinding")
		@Override
		protected void configure() {
			bind(JacksonMessageBodyHandler.class).toInstance(new JacksonMessageBodyHandler());
			bind(ProductAddingFormREST.class);
			bind(ProductAddingREST.class);
			bind(GetProductsREST.class);
			bind(DeleteProductByNameREST.class);
			bind(GetProductsByCompanyREST.class);
		}
	}
}
