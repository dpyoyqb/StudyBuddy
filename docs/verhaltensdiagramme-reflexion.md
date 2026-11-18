# Verhaltensdiagramme – Gesamtreflexion (Übungsblatt 6, Aufgabe 3)

## 1. Überblick über die erstellten Diagramme

- **Aktivitätsdiagramm:** `aktivitaetsdiagramm-dokument-hochladen.png` – Use Case „Dokument hochladen und teilen“ (LF20)
- **Sequenzdiagramm 1:** `sequenzdiagramm-1.png` – Anmeldung mit Hochschul-E-Mail (LF10)
- **Sequenzdiagramm 2:** `sequenzdiagramm-2.png` – Lernbereich per Einladungslink beitreten (LF40)
- **Zustandsdiagramm:** `zustandsdiagramm-dokument.png` – Lebenszyklus eines Dokuments inkl. Melden/Moderation (LF20, LF70)
- PlantUML-Quellen liegen unter `docs/uml/`

## 2. Was zeigen unsere Diagramme?

Der wichtigste Ablauf in StudyBuddy ist das Hochladen & Teilen von Dokumenten, weil alle anderen Funktionen (Fragen, Lernbereiche, Avatar-Punkte) darauf aufbauen. 
Zentral sind die Interaktionen zwischen `Lernbereich`, `Einladungslink` & `Nutzer` sowie die Anmeldung, weil hier die Sicherheitsanforderungen (Hochschul-Domain, gehashte Passwörter, nur Mitglieder sehen Inhalte) tatsächlich durchgesetzt werden. 
Beim Modellieren haben wir gemerkt, dass ein Dokument mehr Zustände hat als gedacht → vor allem durch das Melden und Moderieren.

## 3. Unterschied statisch vs. dynamisch

Das Klassendiagramm aus Woche 5 zeigt die Struktur: welche Klassen gibt es, welche Attribute & Methoden haben sie und wie hängen sie zusammen. 
Die Verhaltensdiagramme zeigen, was zur Laufzeit passiert: in welcher Reihenfolge Schritte ablaufen, welche Objekte sich welche Nachrichten schicken und welche Zustände ein Objekt durchläuft. 
Man braucht beides, da eine Struktur allein nicht zeigt, ob die Abläufe funktionieren & Abläufe ohne Struktur keine Grundlage für den Code haben. 
Die Sequenzdiagramme sind dabei ein guter Test für das Klassendiagramm: Jede Nachricht muss als Methode existieren.

## 4. Herausforderungen & Lessons Learned

- **Lücken im Klassendiagramm:** `NutzerSpeicher` (Nutzer per E-Mail finden) & `mitgliedHinzufuegen()` fehlen in v1.0, ebenso Status/Melden beim `Dokument` → kommt in die nächste Version
- **Detailgrad:** schwierig abzuwägen; Fehlerfälle per `alt`-Fragment abgebildet, Technik wie Datenbank & HTTP weggelassen
- **Sicherheit:** unterschiedliche Fehlermeldungen beim Login würden verraten, ob eine E-Mail registriert ist → nur eine gemeinsame Fehlermeldung
- **Nächstes Mal:** Verhaltensdiagramme parallel zum Klassendiagramm skizzieren, dann fallen fehlende Methoden früher auf
