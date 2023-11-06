# Reactive Spring Boot with WebFlux and R2DBC



## Requirements

* x86-64
* Linux/Unix
* [Docker](https://www.docker.com/products/docker-desktop/)
* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven](https://maven.apache.org/)


## Startup

The script "up" creates our database container and starts up our application:
```
1. docker-compose -f docker/cars/docker-compose.yml up -d
2. mvn spring-boot:run
```

The associated `docker-compose` also contains initialization scripts for creating our database and inserting test rows.


## Shutdown

The script "down" removes our database container:
```
1.docker-compose -f docker/cars/docker-compose.yml down
```

## Load Testing using Bombardier
In this project we utilize [Bombardier](https://github.com/codesenberg/bombardier) to conduct load-testing.

The subject of our tests is the `/car-details` endpoint, which is responsible for retrieving various vehicle-related data from different repositories and responding with an aggregate object.

### Test 1: 10 Concurrent Connections, 1000 Requests (Baseline)
```plaintext
Command: bombardier -m GET localhost:8080/car-details/1 -c 10 -n 1000
Statistics:
  Reqs/sec: 509.64
  Latency: 21.40ms (Avg)
  HTTP codes: 2xx - 1000
  Throughput: 392.74KB/s
  ```

### Test 2: 100 Concurrent Connections, 1000 Requests
```plaintext
Command: bombardier -m GET localhost:8080/car-details/1 -c 100 -n 1000
Statistics:
  Reqs/sec: 844.22 (↑65.48% from Test 1)
  Latency: 116.03ms (↑440.74% from Test 1)
  HTTP codes: 2xx - 1000
  Throughput: 713.68KB/s (↑81.26% from Test 1)
```

### Test 3: 1000 Concurrent Connections, 2000 Requests
```plaintext
Command: bombardier -m GET localhost:8080/car-details/1 -c 100 -n 1000
Statistics:
  Reqs/sec: 770.96 (↑51.27% from Test 1)
  Latency: 1.17s (↑5392.52% from Test 1)
  HTTP codes: 2xx - 2000
  Throughput: 633.57KB/s (↑61.06% from Test 1)
```

## Test Result Analysis

### Baseline (C10, N1000)
The baseline test (Test 1), which serves as our reference point, simulates a moderate load with 10 concurrent connections and 1000 requests. In this scenario:

* The application achieved a request rate of 509.64 requests per second.
* The average latency was measured at 21.40ms.
* All 1000 requests resulted in successful HTTP 2xx responses.


### Test 2 (C100, N1000)
In Test 2, where 100 concurrent connections and 1000 requests were applied:

* The request rate increased significantly by 65.48% compared to the baseline (Test 1), suggesting improved scalability.
* Throughput also showed a notable increase of 81.26% compared to the baseline.
* However, the average latency experienced a substantial rise of 440.74% from the baseline, indicating a trade-off between response rate and response time.


### Test 3 (C1000, N2000)
In Test 3, with 1000 concurrent connections and 2000 requests:

* The request rate maintained a relatively high level, increasing by 51.27% compared to the baseline.
* The average latency saw a dramatic increase of 5392.52% compared to the baseline, suggesting significant delays in response times.
* Throughput showed a considerable increase of 61.06% compared to the baseline.
* This test also successfully processed all 2000 requests with HTTP 2xx responses.


## Comparison of test #3 with imperative Spring Boot
This section is dedicated to the comparison of our application with a more traditional, imperative and thus blocking [Spring Boot application utilizing JDBC](https://github.com/hvalfangst/Kotlin-Spring-Boot-with-Exposed).

### Traditional, blocking Spring Boot with JDBC
```plaintext
Command: bombardier -m GET localhost:8080/car-details/1 -c 1000 -n 2000
Statistics:
  Reqs/sec: 98.17
  Latency: 8.72s
  HTTP codes: 2xx - 362, Errors: Timeout - 1638
  Throughput: 43.78KB/s
  ```

### Reactive Spring Boot with WebFlux and R2DBC
```plaintext
Command: bombardier -m GET localhost:8080/car-details/1 -c 1000 -n 2000
Statistics:
 Reqs/sec: 770.96 (↑686.33% from imperative)
 Latency: 1.17s (↓86.57% from imperative)
 HTTP codes: 2xx - 2000 (↑450.28% from imperative)
 Throughput: 633.57KB/s (↑1,346.43% from imperative)
  ```

## Conclusion
These findings suggest that embracing a non-blocking, reactive architecture using WebFlux and R2DBC results in substantially improved application performance in environments with high concurrent loads.