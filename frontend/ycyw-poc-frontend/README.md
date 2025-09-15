# POC Chat YCYW – Frontend

Ce frontend Angular communique avec le backend Spring Boot via WebSocket (STOMP) et expose un endpoint REST pour récupérer l’historique des messages persistés en base MySQL.

## Prérequis

- Node.js 18+ et npm
- Angular CLI (`npm i -g @angular/cli`)
- Backend Spring Boot démarré sur `http://localhost:8080` avec MySQL accessible

## Démarrage

```bash
npm install
ng serve
```

Ouvrez `http://localhost:4200` dans le navigateur.

## WebSocket

- Endpoint handshake: `ws://localhost:8080/ws`
- Application destination: `/app`
- Topic de diffusion: `/topic/chat`
- Envoyer un message: publier sur `/app/chat.send` un JSON de type `Message`:

```json
{
  "utilisateurId": 123,
  "typeContact": "chat",
  "contenu": "Bonjour"
}
```

Le message est sauvegardé en base et renvoyé tel quel sur `/topic/chat`.

## REST – Historique de conversation

- `GET http://localhost:8080/api/conversations/{utilisateurId}`
- Renvoie la liste des messages triés par date croissante.

## Configuration Backend (rappel)

Dans `backend/src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/ycyw_poc?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=... 
spring.datasource.password=...
spring.jpa.hibernate.ddl-auto=update
```

Créez la base `ycyw_poc` et ajustez les identifiants. Évitez de committer les mots de passe en clair.

## Scripts utiles

- Lancer en dev: `ng serve`
- Build prod: `ng build`

## Tests rapides (POC)

1. Démarrer le backend sur 8080
2. Démarrer ce frontend sur 4200
3. Ouvrir 2 onglets sur `http://localhost:4200`
4. Envoyer un message depuis un onglet → il apparaît dans les deux onglets et est stocké en base

