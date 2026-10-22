# Team-Workflow

## Unser Team

| Name | Rolle |
|------|-------|
| Hannah Ribbers | [Rolle] |
| [Name] | [Rolle] |
| [Name] | [Rolle] |
| [Name] | [Rolle] |

## Unsere Branching-Strategie

Wir arbeiten mit einem einfachen Feature-Branch-Modell:

- **`main`**: Hauptzweig des Projekts. Enthält immer einen lauffähigen, abgestimmten Stand. Auf `main` wird nicht direkt entwickelt.
- **`feature-[beschreibung]`**: ein Branch pro Funktion oder Aufgabe, z. B. `feature-login`, `feature-upload`, `feature-geschuetzte-bereiche`. Für persönliche Übungsaufgaben: `feature-[name]`.
- **`fix-[beschreibung]`**: für Fehlerbehebungen, z. B. `fix-login-validierung`.

Grundsätze:

- Jeder Branch hat genau einen klaren Zweck.
- Branches leben kurz (wenige Tage) und werden nach dem Merge gelöscht.
- Vor dem Weiterarbeiten wird `main` regelmäßig in den eigenen Branch geholt, damit Konflikte klein bleiben.
- Experimentieren ist ausdrücklich erwünscht: Ein Branch kostet nichts und kann jederzeit verworfen werden.

## Commit-Konventionen

Commit-Messages beginnen mit einem Typ, danach folgt eine kurze Beschreibung im Imperativ:

| Typ | Verwendung | Beispiel |
|-----|------------|----------|
| `feat:` | neue Funktion | `feat: Login mit Uni-Mailadresse prüfen` |
| `fix:` | Fehlerbehebung | `fix: Absturz bei leerem Passwortfeld beheben` |
| `docs:` | Dokumentation | `docs: Rolle von Hannah in README ergänzen` |
| `test:` | Tests hinzufügen oder ändern | `test: Unit-Tests für Passwort-Hashing` |
| `refactor:` | Code umbauen ohne neue Funktion | `refactor: Upload-Logik in eigene Klasse auslagern` |
| `chore:` | Projektpflege, Konfiguration | `chore: .gitignore um IntelliJ-Dateien erweitern` |

Regeln:

- Kleine, in sich abgeschlossene Commits statt eines großen Sammel-Commits.
- Keine Passwörter, API-Schlüssel oder Zugangsdaten committen (das Repository ist öffentlich).

## Workflow-Schritte

1. Aktuellen Stand holen: `git checkout main` und `git pull origin main`
2. Branch erstellen: `git checkout -b feature-beschreibung`
3. Entwicklung und lokale Tests
4. Änderungen vormerken und committen: `git add .` und `git commit -m "feat: aussagekräftige Nachricht"`
5. Branch pushen: `git push origin feature-beschreibung`
6. Pull Request auf GitHub erstellen und ein Teammitglied als Reviewer eintragen
7. Review: Das Teammitglied prüft die Änderungen und gibt konstruktives Feedback (konkret, sachlich, mit Verbesserungsvorschlag)
8. Nach Freigabe: Merge in `main` und Branch löschen

## Code-Review-Regeln

- Jeder Pull Request wird von mindestens einem anderen Teammitglied angesehen, bevor er gemergt wird.
- Feedback bezieht sich auf den Code, nicht auf die Person.
- Kommentare möglichst als Vorschlag formulieren („Wäre es übersichtlicher, wenn …?“).
- Wer den Pull Request erstellt hat, mergt ihn nach der Freigabe selbst.

## Reflexion

<!-- Entwurf, nach der Übung an eure tatsächlichen Erfahrungen anpassen -->

Bei der Einrichtung war die größte Hürde, dass alle Teammitglieder Git zunächst korrekt konfigurieren mussten (Name, E-Mail, Anmeldung bei GitHub per Token) und leere Ordner von Git nicht erfasst werden, weshalb wir Platzhalterdateien (`.gitkeep`) anlegen mussten. Die Arbeit mit eigenen Feature-Branches hat gut funktioniert, weil jede Person ungestört an ihrem Abschnitt arbeiten konnte und `main` dabei stabil blieb; Konflikte traten nur auf, wenn mehrere Personen dieselben Zeilen der README.md geändert hatten. Der wichtigste Vorteil der verteilten Versionskontrolle gegenüber zentralen Systemen wie SVN ist, dass jedes Teammitglied eine vollständige Kopie des Repositorys mit der gesamten Historie besitzt und dadurch auch offline committen kann, ohne von einem einzelnen Server abhängig zu sein. Am häufigsten werden wir `git pull`, `git checkout -b`, `git add`, `git commit` und `git push` verwenden. Über Teamarbeit mit Git haben wir gelernt, dass kleine Commits mit klaren Nachrichten und regelmäßiges Mergen die Zusammenarbeit deutlich erleichtern und dass Pull Requests ein guter Anlass sind, sich gegenseitig Feedback zu geben.
