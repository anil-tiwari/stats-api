# Analytics API Microservice
Summary 
* This microservice is built using Java 21, Spring WebFlux, and integrates with ClickHouse, Redis, and Swagger for efficient data processing and API interaction. It provides APIs to retrieve click and view statistics for advertising campaigns, along with authentication using JWT.

# Features
### Event Data Retrieval
Fetch click and view events for specific campaign IDs from ClickHouse.
### Redis Caching: 
Improves performance by caching frequently accessed data.
### Authentication: 
JWT-based authentication for secure API access.
### Swagger Integration: 
Interactive API documentation for easy testing.
### Reactive Programming: 
Built with Spring WebFlux for non-blocking I/O and streaming response.

# Tools and Technologies
### Languages and Frameworks
* Java 21
* Spring Boot 2.7.7
* Spring WebFlux
# Databases
### ClickHouse
High-performance OLAP database for real time aggregation and event data storage.
#### Redis
In-memory data structure store used as a caching layer.

# API Documentation
### Swagger
Provides an interactive interface for testing APIs.
# Endpoints
## Authentication
### POST /login
* Authenticates the user and generates a JWT token.
* The JWT token includes:
  * clientName: Name of the client.
  * clientId: Unique client identifier.
  * loginTimestamp: Timestamp of the login.
  * exp: Token expiry (30 minutes).
## Campaign APIs
* GET /ad/v1/{campaignID}/clicks 
  * Retrieves the total click count for a given campaign ID.
* GET /ad/v1/{campaignID}/views 
  * Retrieves the total view count for a given campaign ID.
* GET /ad/v1/{campaignID}/clicks-data 
  * Retrieves detailed click event data for a given campaign ID.
* GET /ad/v1/{campaignID}/views-data 
  * Retrieves detailed view event data for a given campaign ID.

# Database Configuration
### ClickHouse
ClickHouse is used to store click and view events. Ensure the database schema for click_events matches the following:
* campaign_id :String, 
* client_id   :String, 
* user_id     :String, 
* product_id  :String, 
* click_timestamp :UInt64

### Redis
Redis is configured to cache frequently requested data to reduce database queries and improve performance.

# Setup Instructions
### Prerequisites 
* Install JAVA 21
* Install redis 
* Install clickhouse 
* Configure gradle

# Usage
### Authentication Flow
* Call the POST /login endpoint with valid credentials.
* Use the returned JWT token in the Authorization header for subsequent API requests.
### API Testing
* Use Swagger at http://localhost:8080/swagger-ui.html for testing and exploring APIs.
### Monitoring and Observability
* Prometheus and Grafana can be integrated for monitoring metrics.
* Logs are configured using the Spring Boot logging framework.
