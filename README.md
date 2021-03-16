# System Design: Micro-services Project - Expo Management
## Overzicht
- `/`: broncode repositories micro-services
- `/deployment/services`: cluster service configuratie bestanden
- `/deployment/deployments`: cluster deployment configuratie bestanden
- `/deployment/docker-compose.yaml`: docker deployment file

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
  - `/deployment/services$ kubectl apply -f .`
2. Vervolgens kan men de deployment bestanden toepassen op de cluster.
  - `/deployment/deployments$ kubectl apply -f .`

### Optioneel: docker-compose
- Om de services lokaal te deployen kan men via het bestand `docker-compose.yaml` alle images pullen en opstarten.
  - `docker-compose up -d`
  
### Front-end UI
- De front-end UI hebben we voor het gemak verpakt in een docker image. Deze kan uitgevoerd worden via het volgende commando:
  - `docker run --name front-end -d -p 80:80 wouterstemgee/front-end:latest`
  - de front-end UI is dan beschikbaar op `localhost:80`
- Deze front-end verstuurt alle REST aanvragen naar de API Gateway die dient te luisteren op `localhost:8080`. 
- Om de API Gateway op de cluster te bereiken is er dus lokale port forwarding vereist.

## Testing
Aan de hand van onze front-end UI kunnen alle business use cases getest worden. Verdere instructies en uitleg is op de front-end zelf te vinden.

## Documentation
> Project documentation and UML diagrams

### Architecture Document
[Google docs](https://docs.google.com/document/d/19IyMZ5CPBJ2a_0zrgjndphT1LqtRRcMJi5LXigjrXhw/edit?usp=sharing)

### Spring Boot
[Spring Initializr](https://start.spring.io/)

### Databanken
- hall management: SQL: Tabel met hallID, capaciteit, oppervlakte,... en tabel met verhuurde momenten: hallID, start- en einddatum.
- parking: SQL databank: tabel Capacity (tellers voor totaal, gereserveerd, niet-gereserveerd), tabel Parking (parkeerticket ID, tijdstip binnenrijden), Price (prijs per uur)
- Event management: NoSQL document-based store (MongoDB): eventID, hallID’s (array), start, einde, status
- tracking: SQL databank: taskID, omschrijving, status, eventID
- ticket: SQL databank: ticketID, eventID, status
- badge: SQL databank: badgeID, saldo, eventID
- cloakroom: NoSQL document-based store (MongoDB): aantal beschikbare kapstokken, array van kleerobjecten met badgeID, cloakID
- food&drinks: NoSQL document store: orders en products, geen SQL gebruiken om overtollige tabellen te vermijden (OrderItem, Order, Price, …) die geen extra functionaliteit bieden. Ook eventID bijhouden in order tabel. Mongodb
- multimedia: NoSQL column-based store: alle devices bijhouden met hun kenmerken. (Eventueel Cassandra?), verschillende devices kunnen verschillende kenmerken bevatten.


### All API Endpoints (niet enkel Gateway API endpoints)
- getProgress()
   - GET: /progress/{eventId}
- updateProgress()
   - POST /progress/{progressId}
   - JSON Body: status
   - status
      - 0: NOT STARTED
      - 1: IN PROGRESS
      - 2: FINISHED
- getHallAvailability()
   - GET /reservation/availability
- createReservation()
   - PUT /reservation/create
- executeReservationPayment()
   - POST /reservation/payment
- createParkingTicket()
   - PUT /parking/create
- validateParkingTicket()
   - POST /parking/validate
- exitParking()
   - POST /parking/exit
- getEventInformation(): 
   - GET /event/{eventId}/
- endEvent()
   - POST /event/{eventId}
- getEntranceTicketAvailability()
   - GET /ticket/availability
- buyEntranceTicket()
   - POST /ticket/buy
   - JSON body: eventId, status
- validateEntranceTicket()
   - PUT /ticket/{ticketId}/validate
- rechargeBadge()
   - PUT /badge/{badgeId}/recharge
   - JSON body: amount
- decreaseBalance()
   - PUT /badge/{badgeId}/decrease
   - JSON body: amount
- createOrder()
   - PUT /food_and_drinks
   - JSON body: [<itemId, amount>, ...]
- addCloakroomItem()
   - PUT: /cloakroom
   - JSON body: badgeId, itemId’s
- removeCloakroomItem()
   - DELETE /cloakroom/{itemId}
- getAllCloakroomItems()
   - GET /cloakroom
- getCloakroomItemByBadgeId()
   - GET /cloakroom/{badgeId}
- updateInformationBoards()
   - POST /multimedia
   - JSON body: boardId, message
- triggerEmergency()
   - POST /security/emergency
   - JSON body: emergency type, severity, source
      - emergency type
         - FIRE
         - CO
         - MANUAL (noodknop ingedrukt)
         - NUCLEAR ATTACK
      - severity
         - WARNING
         - DANGEROUS
      - source
         - smoke detector, CO detector, noodknop, gasmelder, ...
