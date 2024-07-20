# How Security works in Spring Boot | Spring Security Series | Video #2

![1.png](src%2Fmain%2Fresources%2Fsc%2F1.png)

- Authintication filter intercept the authentication request and validate the user credentials, by creating authentication object.
- The authentication object wraps the username and password.
- The authentication object is handled by authentication manager. authentication manager decides what to do with these credentials.
- The authentication manager delegates the authentication object to the authentication provider.
- The authentication provider validates the credentials and returns the authentication object.
- Authentication provider needs password encoder to validate the password.
- Authentication provider also need user details service to get the user details.