# StudyBuddy
Software für Studierende
von Studierenden

<img width="500" alt="Logo_schmal" src="https://github.com/user-attachments/assets/81709b9a-c4d8-4042-b29a-eeda30371968" />

Semesterprojekt im Modul **Softwaretechnik** (Prof. Dr. Selcan Ipek-Ugay), BHT – B.Sc. IT-Sicherheit Online, WiSe 2026/27.

## Projektidee

StudyBuddy ist eine Lern- und Austauschplattform von Studierenden für Studierende. Nutzer melden sich mit ihrer Hochschul-E-Mail-Adresse an und können Notizen, Altklausuren, Vorlesungsunterlagen und Transkripte hochladen und miteinander teilen. Fragen lassen sich direkt an einer bestimmten Stelle im Dokument hinterlegen und von anderen beantworten. Für Lerngruppen gibt es passwortgeschützte eigene Bereiche. Ein Teddybär begleitet die Nutzer als Maskottchen und Avatar, der mit zunehmender Aktivität weitere Accessoires freischaltet.

## Kernfunktionen (Muss-Anforderungen)

| ID | Anforderung |
|----|-------------|
| F01 | Anmeldung ausschließlich mit E-Mail-Adresse der Uni-Domain |
| F03–F05 | Hochladen und Teilen von Notizen, Altklausuren, Vorlesungsunterlagen und Transkripten |
| F08–F09 | Passwortgeschützte eigene Bereiche, Inhalte nur mit Passwort sichtbar |
| F11 | Standard-Avatar (Teddybär) für jeden Nutzer |
| NF01 | Passwörter werden ausschließlich gehasht gespeichert (bcrypt/Argon2) |
| NF02 | Gesamte Kommunikation verschlüsselt über HTTPS/TLS |
| NF03 | DSGVO-konforme Verarbeitung personenbezogener Daten |
| NF04 | Beim Hochladen bestätigt der Nutzer die Rechte am Inhalt |

Die vollständige Anforderungsliste (funktional und nicht-funktional, mit Prioritäten) liegt unter [`docs/anforderungen/`](docs/anforderungen/).

## Projektstruktur

```
StudyBuddy/
├── docs/              Projektdokumentation (Anforderungen, Diagramme, Architektur)
├── src/               Quellcode
├── tests/             Unit- und Integrationstests
├── StudyBuddy_Bilder/ Logos, Avatar, Emojis und weitere Grafiken
├── LICENSE            Apache-2.0
├── README.md          Projektbeschreibung (diese Datei)
├── WORKFLOW.md    Team-Workflow, Branching-Strategie, Commit-Konventionen
└── .gitignore     Dateien, die nicht versioniert werden
```

## Team und Rollen

Jedes Teammitglied ergänzt seine Rolle über einen eigenen Feature-Branch (siehe [WORKFLOW.md](WORKFLOW.md)).

### Hannah Ribbers

- **Rolle:** Anforderungen und Sicherheit
- **Aufgaben:** Pflege der Anforderungsliste, Projektdokumentation

## Mitarbeit

Wie wir mit Branches, Commits und Pull Requests arbeiten, steht in [WORKFLOW.md](WORKFLOW.md).
