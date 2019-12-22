# Documentation
> Project documentation and UML diagrams
## Architecture Document
[Google docs](https://docs.google.com/document/d/19IyMZ5CPBJ2a_0zrgjndphT1LqtRRcMJi5LXigjrXhw/edit?usp=sharing)

## Spring Boot
[Spring Initializr](https://start.spring.io/)

## Databanken
- hall management: SQL: Tabel met hallID, capaciteit, oppervlakte,... en tabel met verhuurde momenten: hallID, start- en einddatum.
- parking: SQL databank: tabel Capacity (tellers voor totaal, gereserveerd, niet-gereserveerd), tabel Parking (parkeerticket ID, tijdstip binnenrijden), Price (prijs per uur)
- Event management: NoSQL document-based store (MongoDB): eventID, hallID’s (array), start, einde, status
- tracking: SQL databank: taskID, omschrijving, status, eventID
- ticket: SQL databank: ticketID, eventID, status
- badge: SQL databank: badgeID, saldo, eventID
- cloakroom: NoSQL document-based store (MongoDB): aantal beschikbare kapstokken, array van kleerobjecten met badgeID, cloakID
- food&drinks: NoSQL document store: orders en products, geen SQL gebruiken om overtollige tabellen te vermijden (OrderItem, Order, Price, …) die geen extra functionaliteit bieden. Ook eventID bijhouden in order tabel. Mongodb
- multimedia: NoSQL column-based store: alle devices bijhouden met hun kenmerken. (Eventueel Cassandra?), verschillende devices kunnen verschillende kenmerken bevatten.


## All API Endpoints (niet enkel Gateway API endpoints)
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
