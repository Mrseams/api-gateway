# Api  Gateway
This is a Spring Boot-based for the api Gateway.

This will start the following services:

- Keycloak (Authentication Server) - http://localhost:9000
    - Admin Console Credentials:
        - Username: admin
        - Password: admin
- Grafana Dashboard - http://localhost:3001
- Prometheus - http://localhost:9090
- Loki (Log Aggregation) - http://localhost:3100
- Tempo (Distributed Tracing) - http://localhost:3110

## Monitoring and Observability

The application is configured with comprehensive observability features:

- **Metrics**: Available through Prometheus endpoint and visualized in Grafana
- **Logging**: Aggregated using Loki with structured logging
- **Distributed Tracing**: Using Micrometer and Tempo
- **Health Checks**: Available through Spring Boot Actuator

## API Documentation

The API documentation is available through SpringDoc OpenAPI (Swagger UI) when the application is running.

## Security

The service is secured using OAuth2 Resource Server configuration, integrating with Keycloak for authentication and authorization.

## Circuit Breaker

The application implements circuit breaker patterns using Resilience4j for improved fault tolerance.

## Additional Notes

- The application uses MySQL as the database backend for Keycloak
- Configuration can be modified through application properties and Docker Compose environment variables
- Monitoring dashboards are automatically provisioned in Grafana

## Support

For any questions or issues, please open a GitHub issue in the repository.