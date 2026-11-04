# Design-Entscheidungen zum Klassendiagramm v1.0

## 1. Wichtigste Designentscheidungen

Unser Diagramm ist nach den drei Hauptfunktionen von StudyBuddy aufgebaut: 
  1) Unterlagen teilen & Fragen stellen
  2) geschützte Lernbereiche für Lerngruppen
  3) Teddy-Avatar zur Motivation
Für Notizen, Altklausuren & Vorlesungsunterlagen nutzen wir eine gemeinsame Oberklasse `Dokument`, weil alle gleich hochgeladen & geteilt werden.
  - ein Attribut „typ“ hätte nicht gereicht, weil z. B. Altklausuren eigene Angaben wie Semester & Prüfer haben
Den Zugang zu Lernbereichen haben wir als eigene Klasse `Einladungslink` modelliert statt mit einem Passwort, weil Links ablaufen & zurückgezogen werden können.
Den Moodle-Import haben wir über ein Interface getrennt, damit wir ihn ohne echte Moodle-Verbindung testen können.

## 2. Offene Fragen

- Darf ein Dokument in mehreren Lernbereichen liegen, und was passiert mit den Dokumenten, wenn ein Lernbereich gelöscht wird?
- Wie bilden wir Lehrende und das Melden von Inhalten (LF70) ab?
    - Das KI-Review schlägt eine Klasse `Meldung` & ein Attribut `rolle` vor.
