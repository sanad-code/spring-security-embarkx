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

Getting Started with Spring Security and Form Based Authentication | Spring Security | Video #3

- Configuring a spring project with spring security.
- How autoconfiguration magic works in spring boot.
- Check the form based authentication mechanism.