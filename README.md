# How Security works in Spring Boot | Spring Security Series | Video #2

![1.png](src/main/resources/sc/1.png)

- Authentication filter intercept the authentication request and validate the user credentials, by creating authentication object.
- The authentication object wraps the username and password.
- The authentication object is handled by authentication manager. authentication manager decides what to do with these credentials.
- The authentication manager delegates the authentication object to the authentication provider.
- The authentication provider validates the credentials and returns the authentication object.
- Authentication provider needs password encoder to validate the password.
- Authentication provider also need user details service to get the user details and roles. It returns the user details object.
- After authentication provider validates the credentials it will fill out the missing data of authentication object like roles.
- The request is sent back to authentication manager.
- Then it will return to authentication filter.
- Finally, security context is created and the user is authenticated.
- The security context is aware of authenticated user during the session.
- So the details of authenticated user remains in the security context.

## Getting Started with Spring Security and Form Based Authentication | Spring Security | Video #3

- Configuring a spring project with spring security.
- How autoconfiguration magic works in spring boot.
- Check the form based authentication mechanism.

### Steps

- create controller, it is working.
- add authentication dependency to pom.
- try to access the controller again it will ask for username and password.
- /login is the default login page.
- /logout is the default logout page. http://localhost:8080/logout
- By default, every thing is secured and requires authentication.
- The default is form based authentication.
- There are default login and logout form.
- Default username and password is created from you.
- You can open the dev tools to see the login and logout request details.
- You can 
- add property to fix the username and password, this style is good for dev environment only.

## Basic Authentication and writing our own Security Filter | Spring Security Series | Video 4

- Form based authentication is not good for apis.
- There is basic authentication can be used in this case.
- SpringBootWebSecurityConfiguration is the class that is responsible for the default security configuration.
- We can create our custome security filter chain.
- There is a session that is managed based on cookies.
- You can stop this session management by configuring the session policy.

## In Memory Authentication Line By Line | Spring Security Series | Video #5

- In memory authentication is not good for production.
- We need to add bean of UserDetailsService that returns InMemoryUserDetailsManager.

## Role Based Authorization Line By Line | Spring Security Full Course Series | Video #6

- we need @EnableMethodSecurity to enable method level security.
- @PreAuthorize("hasRole('ROLE_ADMIN')") is used to secure the method.
- 401 is fail authentication 403 is fail authorization.

## Enabling H2 Database Line By Line | Spring Security Full Course Series | Video #7

- we need to add h2, jpa dependencies to pom
- we need to add h2 and datasource to property file

## Database Authentication In Spring Boot Line By Line | Spring Security Full Course Tutorial | #8

- spring-security is opensource in github, you can search for users.ddl
- for jdbc we need to create users schema and table.
- During saving role to databse the name is appended with ROLE_XXXX, and we do the check with name only without ROLE_.
- Hashing is done by password encoder.
- bcrypt involves using salting it is a secure algorithm for hashing and one of the most popular ones.
- Salting increase the security.


## Udemy Course

### Section 3 Basic Authentication

- It is simplest form of authentication
- Send username and password in the request header as authorization header.
- Credentials are encoded in base64.
- Format is Basic username:password in base64.
- File SpringBootWebSecurityConfiguration is responsible for the default security configuration.
  - The method defaultSecurityFilterChain is responsible for the default security configuration
    it returns SecurityFilterChain object.
  - It simply mark all requestes as authenticated and enable both form and basic authentication.
  - If we specify our class SecurityFilterChain this default class will be ignored.
- Creating our security config file we need annotation @EnableWebSecurity and @Configuration.
- Then we create @Bean of type SecurityFilterChain that takes HttpSecurity object, this object build is the return of method.

- Basic authentication vs form authentication
- with only basic authentication configured /login will give error.
- we need to enable form login with defaults
- we can sesssion from statfull to stateless.
- We can premit certain requestes and deny others.

### Section 4 Secure Notes Project

- We created Note entity and added lombok and JPA dependencies.
- Adding repos and services.
- Adding note controller. 
- @AuthenticationPrincipal UserDetails userDetails | userDetails.getUsername() to get username.

- Note how in compose the port has 127.0.0.1 to make it easy to access the database from local workbench.
- `CREATE SCHEMA notes CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;`
- For logging `logging.level.org.springframework.security: TRACE`

### Section 5 Authentication Providers - In Memory Authentication

- Authentication provider handles the actual verification of credentials.
- Authentication provider is an interface. Multiple implementations can be configured.
- Key responsibilities
  - Authenticate the user, depend on your configuration.
  - Create Authentication Token, generate authentication object to encapsulate the user credentials and roles.
- Importance
  - Flexibility, you can use multiple authentication providers (Ldap, Oauth, InMemory, etc.).
  - Separation of concerns, we decouple the authentication logic from the rest of the application.
  - Extensibility, we can create our own authentication provider or integrating with 3rd party providers.
  - Security of credentials, we can use different hashing algorithms and salting techniques.

#### Authentication Provider

- DaoAuthenticationProvider is the most commonly used authentication provider and the default one if none is specified.
- It uses UserDetailsService to retrieve the user details.
- InMemoryUserDetailsManager is the simplest implementation of UserDetailsService.
- LdapAuthenticationProvider is used to authenticate against an LDAP server, it needs configuration.
- ActiveDirectoryLdapAuthenticationProvider is used to authenticate against an Active Directory server.
- PreAuthenticatedAuthenticationProvider is used to authenticate requests that have already been authenticated by a different system.
- OAuth2AuthenticationProvider is used to authenticate requests that have already been authenticated by an OAuth2 server.

- Authentication provider has two methods authenticate and supports.
- supports method is used to check if the authentication provider can authenticate the given authentication object.
- supports allow authentication manager to select the correct authentication provider.

#### InMemory Authentication

![InMemory](src/main/resources/sc/2.png)

- It is not recommended for production. store and manage the user details in memory.
- It is useful for testing and development, small applications, and prototypes.
- Benefits, simplicity, speed and ease of use.
- In application.properties we can add only one user and password.
- InMemoryUserDetailsManager is the implementation of UserDetailsService and allow us to add multiple users.

### Section 6 User Management

![Core Classes](src/main/resources/sc/3.png)

- UserDetails is an interface that represents the user details.
- UserDetails is later encapsulated into Authentication object.
- It provides the necessary information to authenticate the user like username, password, authorities, account status, etc.
- User is a simple implementation of UserDetails.
- User is often used to create a UserDetails object. Check code in SecurityConfig. User.withUsername("user").password("password").roles("USER").build();
- UserDetailsService is an interface that provides the user details.
- CredentialsContainer is an interface that provides the method eraseCredentials() to erase the user credentials after authentication.

![UserDetails](src/main/resources/sc/4.png)

- UserDetailsService is an interface that responsible for retrieving the user-specific data.
- It has only one method loadUserByUsername(String username).
- UserDetailsManager is an interface that extends UserDetailsService.
- It provides additional methods to manage the user details like createUser, updateUser, deleteUser, change password, etc.
- JdbcUserDetailsManager is an implementation of UserDetailsManager using JDBC-based datasource.
- JdbcUserDetailsManager has predefined SQL queries to manage the user details.
- InMemoryUserDetailsManager is an implementation of UserDetailsManager that stores the user details in memory.
- InMemoryUserDetailsManager is useful for testing and development, it implements all methods in UserDetailsManager and UserDetailsService.
- 

