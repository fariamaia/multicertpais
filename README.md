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
- Github as SCM with WebHook with Jenkins

the major decisions rationale;

- Java 21 is the newest LTS version
- Open Liberty (Jakarta 10 ) already provide all facilities needed, in a standard and documented,  and more:
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

## How to Access

### Jenkins 
 - [Link](https://http://ec2-13-53-38-168.eu-north-1.compute.amazonaws.com:8080/ "http://ec2-13-53-38-168.eu-north-1.compute.amazonaws.com:8080/") - http://ec2-13-53-38-168.eu-north-1.compute.amazonaws.com:8080/ 
(credentials in email)

### EC2 VMs  
 
 Credential file in email, it was reuse the same file for both VMs.
 
 - Jenkins VM
 
```bash
 ssh -i "jenkins.pem" admin@ec2-13-53-38-168.eu-north-1.compute.amazonaws.com
```

 - Live Demo VM
 
 ```bash
 ssh -i "jenkins.pem" admin@ec2-13-49-73-104.eu-north-1.compute.amazonaws.com
```
 - AWS Console Access ( EC2)
 
 [Link](https://eu-north-1.console.aws.amazon.com/ec2/home?region=eu-north-1#Instances: "https://eu-north-1.console.aws.amazon.com/ec2/home?region=eu-north-1#Instances:") - https://eu-north-1.console.aws.amazon.com/ec2/home?region=eu-north-1#Instances:
(credentials in email)

## TODO List

List some points to be done.

- Static code Analysis tools integrated in Jenkins, only done at IDE level.
- More Unit Testing coverage, also use mockito and CDI for more complete testing
- api-ninja API key should be in configuration
- Integrate with Telemetry tool
- Slim Live Demo Open Liberty instance
- Execute non root Live Demo Open Liberty instance with access to privileged ports.
- Create a pool of REST connections for API access through Factory or singleton
- Research a more standardized way to perform data mapping between different domain's models.

