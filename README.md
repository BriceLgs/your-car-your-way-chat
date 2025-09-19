## Your Car Your Way — POC Chat (Spring Boot + Angular)

Ce projet est un POC (Proof of Concept) démontrant une messagerie temps réel via WebSocket (STOMP) entre un backend Spring Boot et un frontend Angular, avec persistance des messages en base MySQL et un endpoint REST pour l’historique.

### Aperçu

- **Backend**: Spring Boot (WebSocket STOMP, REST, JPA)
- **Frontend**: Angular (client STOMP)
- **Base de données**: MySQL
- **Communication temps réel**: `/ws` + topics STOMP

### Architecture du dépôt

```
backend/                      # Service Spring Boot
frontend/ycyw-poc-frontend/   # Application Angular
```

### Pré-requis

- Java 17+
- Maven (ou le wrapper `mvnw`/`mvnw.cmd` fourni)
- Node.js 18+ et npm
- MySQL 8+ (base accessible en local)

### Installation et configuration

1) Créer une base MySQL (ex: `ycyw_poc`).
2) Configurer les identifiants dans `backend/src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/ycyw_poc?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=VOTRE_UTILISATEUR
spring.datasource.password=VOTRE_MOT_DE_PASSE
spring.jpa.hibernate.ddl-auto=update
```

3) Installer les dépendances frontend:

```bash
cd frontend/ycyw-poc-frontend
npm install
```

### Démarrage rapide

- Backend (Windows PowerShell):

```bash
cd backend
./mvnw.cmd spring-boot:run
```

- Frontend (dans un autre terminal):

```bash
cd frontend/ycyw-poc-frontend
npx ng serve
```

- Ouvrir le navigateur sur `http://localhost:4200`.

### API et WebSocket

- WebSocket handshake: `ws://localhost:8080/ws`
- Destination application: `/app`
- Topic de diffusion: `/topic/chat`
- Envoyer un message: publier sur `/app/chat.send` un JSON de type `Message`.

Exemple de payload:

```json
{
  "utilisateurId": 123,
  "typeContact": "chat",
  "contenu": "Bonjour"
}
```

- Historique des conversations (REST):
  - `GET http://localhost:8080/api/conversations/{utilisateurId}` → liste de messages triés par date croissante

### Tests rapides (POC)

1. Démarrez le backend (8080) et le frontend (4200)
2. Ouvrez 2 onglets sur `http://localhost:4200`
3. Envoyez un message depuis un onglet
4. Vérifiez qu’il apparaît dans tous les onglets et qu’il est persisté

### Dépannage

- Port 8080 déjà utilisé: arrêt d’un autre service, ou changer le port Spring (`server.port`).
- Erreur de connexion MySQL: vérifiez `spring.datasource.*` et que la base existe.
- WebSocket ne se connecte pas: confirmez l’URL `ws://localhost:8080/ws` et les CORS côté backend.

### Scripts utiles

- Backend: `./mvnw.cmd clean package` (Windows) ou `./mvnw clean package` (Unix)
- Frontend dev: `npx ng serve`
- Frontend build prod: `npx ng build`
