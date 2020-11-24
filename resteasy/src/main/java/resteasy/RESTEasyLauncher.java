package resteasy;

import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.flywaydb.core.Flyway;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import server.JettyServer;


public class RESTEasyLauncher {
	public static void main(String[] args) throws Exception {

		Flyway flyway = Flyway.configure()
						.dataSource("jdbc:postgresql://localhost:5432/mail-lab7","postgres","кщще")
						.locations("/db.migration")
						.load();
		//flyway.clean();
		flyway.migrate();

		final Server server = JettyServer.build();

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		context.addServlet(HttpServletDispatcher.class, "/");
		context.addEventListener(new GuiceListener());

		final String hashConfig = RESTEasyLauncher.class.getResource("/hash_config").toExternalForm();
		final HashLoginService hashLoginService = new HashLoginService("login",hashConfig);
		final ConstraintSecurityHandler security = new SecurityHandlerBuilder().build(hashLoginService);

		server.addBean(hashLoginService);
		security.setHandler(context);
		server.setHandler(security);

		server.start();
	}
}
