# docker
Service deployment configuration files

- Development: docker-compose (local)
- Production: kubernetes cluster

## Development Flow
1. Build Spring Boot micro-service with maven
2. Create docker image for each micro-service from its Dockerfile
   - [https://github.com/fabric8io/docker-maven-plugin](docker-maven-plugin)
3. Push docker image to a docker Registry (DockerHub)
4. Start docker-compose file containing all the required docker images for each service
