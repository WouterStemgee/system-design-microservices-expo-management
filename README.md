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
[Security](https://hub.docker.com/r/ebout/security),
- Tarik:
[Hall Management](https://hub.docker.com/r/tarikatac/hallmanagement),
[Tracking](https://hub.docker.com/r/tarikatac/tracking),
[Cloakroom](https://hub.docker.com/r/tarikatac/cloakroom)

1. De services kunnen opnieuw gebuild worden door deze met maven opnieuw te compileren:
  - `mvn package -DskipTests`
2. Vervolgens kan men de images opnieuw aanmaken via de Dockerfiles die terug te vinden zijn in de root directory van elke repository.
  - `docker build -t <image:tag> .`

## Deployment
## Testing

