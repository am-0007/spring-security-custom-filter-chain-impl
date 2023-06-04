# spring-security-custom-filter-chain-impl

This code snippet provides a security configuration for a Spring application. It includes three main components:

Security Configuration class:
  This class sets up the security filters and rules for HTTP requests. It disables CSRF protection, defines authorization rules for specific URLs, and adds custom filters for authentication.

Custom Authentication filter chain class:
  This class extends Spring's UsernamePasswordAuthenticationFilter and handles the authentication process. It reads the username and password from the request, authenticates the user, and generates a JWT token upon successful authentication.

Jwt Authentication Filter chain class:
  This class extends OncePerRequestFilter and validates JWT tokens for incoming requests. It intercepts requests, checks the validity of the token, and allows authenticated requests to proceed. It also handles invalid or expired tokens by sending appropriate error responses.

Overall, this configuration provides authentication and authorization using JWT tokens for the specified URLs, ensuring secure access to protected resources.
