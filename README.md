# MultiCert Technical Exercise "Pais"

Just a technical exercise resolution for a job interview.

## Table of Contents

- [Design](#design)
- [How to Build](#how-to-build)
- [How to Run](#how-to-run)
- [TODO List](#todo-list)


## Design

After analysing the Exercise description

- Java JDK 21
- IBM Open Liberty as Jakarta 10 Web application & Microprofile 6 runtime
- Jakarta 10 Web application & Microprofile 6 runtime
- Jenkins as CI/CD pipeline
- AWS as VM provider ( 1 for Jenkins , 1 demo )

the major decisions rationale;

- Java 21 is the newest LTS version
- Open Liberty already provide all facilities needed, in a standard and documented,  and more:
	- JAX RS REST Application
	- JAX RS REST Client for external REST invocation
	- JAX RS JSON Binding
	- Jakarta Scheduler Service
	- Microprofile 6 ( provide a openapi ui for web browser invocations)
- Platform Management independency 
- Jenkins usage was only due past experience.
- AWS EC2 VMs usage was also due to past experience.


## How to Build

### Prerequisites

Prerequisites that users need to have installed before they can use your project.

- Java 21
- Maven

### Build process

Provide instructions on how to install or set up your project.

```bash
mvn clean package
```

## How to Run

__Option 1 - Live Demo__
 - Website: [Link](https://ec2-13-49-73-104.eu-north-1.compute.amazonaws.com/openapi/ui "https://ec2-13-49-73-104.eu-north-1.compute.amazonaws.com/openapi/ui") - https://ec2-13-49-73-104.eu-north-1.compute.amazonaws.com/openapi/ui 
- API: [Link](https://ec2-13-49-73-104.eu-north-1.compute.amazonaws.com/api "https://ec2-13-49-73-104.eu-north-1.compute.amazonaws.com/api") - https://ec2-13-49-73-104.eu-north-1.compute.amazonaws.com/api

__Option 2 - Developer environment__

```bash
mvn liverty:run
```

 - Website: [Link](https://localhost:9443/openapi/ui "https://localhost:9443/openapi/ui") - https://localhost:9443/openapi/ui 
- API: [Link](https://localhost:9443/api "https://localhost:9443/api") - https://localhost:9443/api


## TODO List

List the key features of your project.

- Static code Analysis tools integrated in Jenkins
- Unit Testing
- api-ninja API key should be in configuration
- Integrate with Telemetry tool
