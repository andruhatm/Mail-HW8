package server;

import org.eclipse.jetty.server.*;

@SuppressWarnings({"NotNullNullableValidation"})
public enum JettyServer {
	;

	public static Server build() {
		final Server server = new Server();
		final ServerConnector serverConnector = new ServerConnector(server, new HttpConnectionFactory());
		serverConnector.setHost("localhost");
		serverConnector.setPort(3466);
		server.setConnectors(new Connector[]{serverConnector});
		return server;
	}
}
