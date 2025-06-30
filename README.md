# MIfahrzentrale – Webanwendung für Mitfahrgelegenheiten

Dies ist ein studentisches Projekt aus dem Modul "Webanwendungen", das im Rahmen des Medieninformatik-Studiums an der Hochschule RheinMain entstanden ist. Die Anwendung wurde gemeinsam mit einer Kommilitonin entwickelt und bietet eine Plattform zur Vermittlung von Fahrgemeinschaften innerhalb der Hochschule.

## Arbeitsprobe – empfohlener Stand

Die vollständige Version mit funktionsfähigem CSS-basiertem Frontend befindet sich im Branch [`working-version`]([https://github.com/marvin-wernli/wba-project/tree/working-version]).
Im Main Branch wurde ein Refactoring mit Vue.js begonnen, das jedoch noch nicht abgeschlossen ist.

## Projektfeatures

- Anlegen und Verwalten von Benutzern, Orten und Mitfahrangeboten
- Benutzeroberfläche mit HTML, CSS und JavaScript
- Backend-Anbindung über REST-API (Spring Boot)
- Persistente Session-Verwaltung

## Technologien

- **Frontend:** HTML, CSS, JavaScript (später auch Vue.js)
- **Backend:** Java, Spring Boot, REST
- **Build-Tool:** Gradle
- **Datenbank:** Integrierte H2-Datenbank (In-Memory / Datei-basiert)
- **Versionierung:** Git

## Setup

Voraussetzungen: Java 21, Gradle, Node.js

1. Repository klonen:
   ```bash
   git clone https://github.com/marvin-wernli/wba-project.git
   cd wba-project
   ```

2. Branch wechseln
   Für den vollständigen Stand mit CSS-Frontend (empfohlen):
   ```bash
   git checkout working-version
   ```
   
  Für den aktueller Stand mit Vue-Frontend (nicht funktionsfähig):
  ```bash
  git checkout main
  ```

3. Backend starten
  ```bash
  ./gradlew bootRun
  ```

Optional: Frontend starten (nur im main branch)
  ```bash
  cd .\frontend\
  npm install
  npm run dev
  ```

## How to use

 Hinweis: Die Startseite http://localhost:8080/ zeigt lediglich eine Whitelabel Error Page. Bitte rufe die folgenden spezifischen URLs direkt im Browser auf, um die Anwendung zu nutzen.

- Benutzerverwaltung
  Zugriff:
  Unter http://localhost:8080/benutzer findest du eine Liste aller vorhandenen Benutzer:innen.

  Neuen Benutzer anlegen:
  Klicke auf „Neuer Benutzer“, um ein Benutzerformular aufzurufen.
  Hinweis: Das Passwort muss zwingend entweder "17" oder "siebzehn" enthalten (Validierungsregel).

- Orte verwalten
  Zugriff: Über die Navigationsleiste oder direkt via http://localhost:8080/ort.

  Anlegen eines Ortes: Gib den gewünschten Ortsnamen ein – die Koordinaten (Längen- und Breitengrad) werden automatisch ermittelt.

- Touren erstellen
  Zugriff: Über den Menüpunkt „Touren“ oder direkt via http://localhost:8080/tour.

  Voraussetzungen: Für das Anlegen einer Tour müssen mindestens ein Benutzer und ein Ort vorhanden sein.

## 📁 Branch-Struktur

| Branch         | Beschreibung                                |
|----------------|---------------------------------------------|
| `main`         | Aktueller Stand mit Vue-Frontend (nicht funktionsfähig) |
| `working-version` | Vollständiger Stand mit CSS-Frontend (empfohlen)       |

## 👥 Mitwirkende

- Marvin Wernli
- Sina Larissa Oblong

---

