# Uitrol instructies
> Groep 2
## Overzicht
- `/services`: cluster service configuratie bestanden
- `/deployments`: cluster deployment configuratie bestanden
- `/code`: broncode repositories
- `docker-compose.yaml`: docker deployment file

## Building
Alle services zijn reeds gebuild en verpakt in een docker image. Deze staan publiek beschikbaar op onze persoonlijke DockerHub repositories.
- Thomas:
[Badge](https://hub.docker.com/r/thomasdevriese/badge),
[Ticket](https://hub.docker.com/r/thomasdevriese/ticket),
[Food-And-Drinks](https://hub.docker.com/r/thomasdevriese/food-and-drinks)
- Wouter: 
[Event Reservation](https://hub.docker.com/r/wouterstemgee/event-reservation), 
[Event Management](https://hub.docker.com/r/wouterstemgee/event-management), 
[Parking](https://hub.docker.com/r/wouterstemgee/parking), 
[API Gateway](https://hub.docker.com/r/wouterstemgee/api-gateway)
- Elias:
[Multimedia](https://hub.docker.com/r/ebout/multimedia),
[Security](https://hub.docker.com/r/ebout/security)
- Tarik:
[Hall Management](https://hub.docker.com/r/tarikatac/hallmanagement),
[Tracking](https://hub.docker.com/r/tarikatac/tracking),
[Cloakroom](https://hub.docker.com/r/tarikatac/cloakroom)

1. De services kunnen opnieuw gebuild worden met Maven: `mvn package -DskipTests`
2. Vervolgens kan men de images opnieuw aanmaken via de Dockerfiles die terug te vinden zijn in de root directory van elke repository in `/code`: `docker build -t <image:tag> .`

## Deployment
### Cluster
1. Om alle ClusterIP's en NodePort's in te stellen dient men eerst alle service configuratie bestanden toe te passen op de cluster.
  - `/services$ kubectl apply -f .`
2. Vervolgens kan men de deployment bestanden toepassen op de cluster.
  - `/deployments$ kubectl apply -f .`

### Optioneel: docker-compose
- Om de services lokaal te deployen kan men via het bestand `docker-compose.yaml` alle images pullen en opstarten.
  - `docker-compose up -d`
  
### Front-end UI
- De front-end UI hebben we voor het gemak verpakt in een docker image. Deze kan uitgevoerd worden via het volgende commando:
  - `docker run --name front-end -d -p 80:80 wouterstemgee/front-end:latest`
  - de front-end UI is dan beschikbaar op `localhost:80`
- Deze front-end verstuurt alle REST aanvragen naar de API Gateway die dient te luisteren op `localhost:8080`. 
- Om de API Gateway op de cluster te bereiken is er dus lokale port forwarding vereist.
  - `ssh -L 8080:<public_cluster_ip>:<cluster_node_port>`

## Testing
Aan de hand van onze front-end UI kunnen alle business use cases getest worden.

