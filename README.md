# How Security works in Spring Boot | Spring Security Series | Video #2

![1.png](src%2Fmain%2Fresources%2Fsc%2F1.png)

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

# Getting Started with Spring Security and Form Based Authentication | Spring Security | Video #3

- Configuring a spring project with spring security.
- How autoconfiguration magic works in spring boot.
- Check the form based authentication mechanism.

## Steps

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

# Basic Authentication and writing our own Security Filter | Spring Security Series | Video 4

- Form based authentication is not good for apis.
- There is basic authentication can be used in this case.
- SpringBootWebSecurityConfiguration is the class that is responsible for the default security configuration.
- We can create our custome security filter chain.
- There is a session that is managed based on cookies.
- You can stop this session management by configuring the session policy.