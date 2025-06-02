# Kafka Spring Boot Multi module Calculator service

A distributed calculator service using Spring Boot, Apache Kafka, and Docker. The service consists of two modules:
- REST API service for receiving calculation requests
- Calculator service for processing operations

## Prerequisites

- Docker Desktop
- Java 17 or later (for local development only)

## Quick Start

1. Clone the repository
2. Open a terminal in the project root directory
3. Run the following commands:

```powershell
# Build the application (using maven wrapper)
.\mvnw.cmd clean install

# Manually run tests
.\mvnw.cmd test

# Start all services
docker-compose up --build -d
```

The following services will be available:
- REST API: http://localhost:8080
- Calculator Service: http://localhost:8081
- Kafdrop (Kafka UI): http://localhost:9000

## Usage example using Bruno or Postman

Send POST requests to `http://localhost:8080/api/calculate` with the following JSON structure:

```json
{
    "operationType": "sum",
    "a": 10,
    "b": 5
}
```
headers: `Content-Type: application/json`


Available operations:
- `sum`: Addition
- `subtract`: Subtraction
- `multiply`: Multiplication
- `divide`: Division

Example using curl:
```powershell
curl -X POST http://localhost:8080/api/calculate `
-H "Content-Type: application/json" `
-d "{\"operationType\":\"sum\",\"a\":10,\"b\":5}"
```

## Monitoring

- View Kafka topics and messages using Kafdrop at http://localhost:9000
- Check application logs:
  ```powershell
  # REST service logs
  docker logs rest
  
  # Calculator service logs
  docker logs calculator
  ```
- Local log files are available in:
  - `./logs/rest/rest-service.log`
  - `./logs/calculator/calculator-service.log`

## Useful Kafka Commands

```powershell
# List all topics
docker exec kafka /opt/kafka/bin/kafka-topics.sh --bootstrap-server kafka:9092 --list

# View topic details using 'requests' topic as an example
docker exec kafka /opt/kafka/bin/kafka-topics.sh --bootstrap-server kafka:9092 --describe --topic requests
```

## Stopping the Application

```powershell
# Stop all services
docker-compose down

# To also remove volumes (clean start)
docker-compose down -v
```

## Architecture

- REST Service:
  - Receives HTTP requests
  - Produces messages to 'requests' topic
  - Consumes messages from 'responses' topic

- Calculator Service:
  - Consumes messages from 'requests' topic
  - Performs calculations
  - Produces results to 'responses' topic

- Kafka:
  - Handles message queuing between services
  - Ensures reliable message delivery
  - Manages topic partitioning and replication

- Kafdrop:
  - Web UI for monitoring Kafka clusters
  - Displays broker information and configuration
  - Allows viewing topic details and messages
  - Supports creating and modifying topics
  - Provides message browsing with JSON formatting


## Troubleshooting

1. If services fail to start:
   ```powershell
   # View service logs
   docker-compose logs
   
   # Rebuild and restart
   docker-compose down
   docker system prune -f
   docker-compose up --build
   ```

2. If Kafka is not responding:
   ```powershell
   # Restart Kafka container
   docker-compose restart kafka
   ```