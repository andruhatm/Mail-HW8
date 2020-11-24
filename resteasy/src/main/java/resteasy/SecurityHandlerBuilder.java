package resteasy;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.util.security.Constraint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityHandlerBuilder {
	private final static String ROLE_MANAGER = "manager";
	private final static String ROLE_GUEST = "guest";

	private final ConstraintSecurityHandler security = new ConstraintSecurityHandler();

	public final ConstraintSecurityHandler build(LoginService loginService){
		security.setLoginService(loginService);

		final List<ConstraintMapping> constraintMappings = new ArrayList<>();
		List<String> list = new ArrayList<>();
		list.add("/add");
		list.add("/addProduct");
		list.add("/delete");
		constraintMappings.addAll(constraintFullMapping(
						bindConstraint(ROLE_MANAGER),
						list
		));

		List<String> list2 = new ArrayList<>();
		list2.add("/getProducts");
		list2.add("/get");
		list2.add("/getProductsByCompany");
		constraintMappings.addAll(constraintGetMapping(
						bindConstraint(ROLE_MANAGER,ROLE_GUEST),
						list2
		));

		security.setConstraintMappings(constraintMappings);
		security.setAuthenticator(new BasicAuthenticator());
		security.setDenyUncoveredHttpMethods(true);
		return security;
	}

	private static Constraint bindConstraint(String... userRoles){
		final Constraint starterConstraint = new Constraint();
		starterConstraint.setName(Constraint.__BASIC_AUTH);
		starterConstraint.setRoles(userRoles);
		starterConstraint.setAuthenticate(true);

		return starterConstraint;
	}

	private static Collection<ConstraintMapping> constraintGetMapping(Constraint constraint,
																																		Collection<String> paths){
		return constraintMapping(constraint,paths,"GET");
	}

	private static Collection<ConstraintMapping> constraintFullMapping(Constraint constraint,
																																		 Collection<String> paths){
		return constraintMapping(constraint,paths,"*");
	}

	private static Collection<ConstraintMapping> constraintMapping(Constraint constraint,
																																 Collection<String> paths,
																																 String method){
		return paths.stream()
						.map(path ->{
							final ConstraintMapping mapping = new ConstraintMapping();
							mapping.setConstraint(constraint);
							mapping.setPathSpec(path);
							mapping.setMethod(method);
							return mapping;
						}
				).collect(Collectors.toList());
	}
}