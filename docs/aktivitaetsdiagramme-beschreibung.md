# Verhaltensdiagramme – Beschreibung (Übungsblatt 6)

## Aufgabe 1
## Aktivitätsdiagramm: Dokument hochladen und teilen

Datei: [aktivitaetsdiagramm-dokument-hochladen.png](./aktivitaetsdiagramm-dokument-hochladen.png)

**Modellierter Use Case:** „Nutzer lädt ein Dokument hoch & teilt es“ (LF20 / F03–F05). Das ist die Kernfunktion von StudyBuddy: 
  Ohne geteilte Unterlagen gibt es keine Fragen, keine Lernbereiche mit Inhalt & keine Aktivitätspunkte für den Avatar

**Wichtigste Entscheidungspunkte:**
- *Nutzer angemeldet?* – nur angemeldete Nutzer mit Hochschul-E-Mail dürfen hochladen, sonst Anmelde Fester anzeigen und danach nochmal prüfen
- *Eingaben gültig?* – geprüft werden Dateityp, Dateigröße, Pflichtfelder (Titel, Modul, Dokumenttyp) & die Bestätigung der Rechte am Inhalt (NF04)
- *Lernbereich gewählt?* – entweder wird das Dokument einem geschützten Lernbereich zugeordnet (nur für Mitglieder sichtbar) oder für alle Hochschulangehörigen freigegeben

Nach dem Speichern laufen zwei Aktionen parallel (Fork/Join): (1) Aktivitätspunkte gutschreiben & (2) Mitglieder bzw. Interessierte benachrichtigen

**Fehler und Ausnahmen:**
- Nicht angemeldet → Weiterleitung zur Anmeldung → der Ablauf endet
- Ungültige Eingaben/fehlende Rechtebestätigung → Fehlermeldung, der Nutzer landet wieder beim Formular (Schleife) → es wird nichts gespeichert

## Aufgabe 2
## Sequenzdiagramm 1: Anmeldung mit Hochschul-E-Mail

Datei: [sequenzdiagramm-1.png](./sequenzdiagramm-1.png)

- **Interaktion:** Ein Studierender meldet sich mit E-Mail & Passwort an (LF10 / F01)
- **Beteiligte Klassen:** `AnmeldeFenster` (Oberfläche), `AnmeldeLogik`, `NutzerSpeicher`, `Nutzer`
- **Zentrale Nachricht:** `anmelden(email, passwort)`; Die `AnmeldeLogik` prüft zuerst mit `istUniEmail()` die Domain, holt dann den Nutzer & lässt ihn mit `anmelden(passwort)` den Passwort-Hash vergleichen (NF01). Bei unbekannter E-Mail wird gegen einen Dummy-Nutzer geprüft & dieselbe Fehlermeldung ausgegeben, damit Angreifer nicht herausfinden können, welche E-Mail-Adressen registriert sind.

## Sequenzdiagramm 2: Lernbereich per Einladungslink beitreten

Datei: [sequenzdiagramm-2.png](./sequenzdiagramm-2.png)

- **Interaktion:** Ein Studierender öffnet einen Einladungslink & tritt einem geschützten Lernbereich bei (LF40 / F08–F09)
- **Beteiligte Klassen:** `LernbereichSeite` (Oberfläche), `Lernbereich`, `Einladungslink`, `Nutzer`
- **Zentrale Nachricht:** `beitreten(nutzer, token)`; Der `Lernbereich` prüft, ob der Nutzer schon Mitglied ist & fragt sonst den `Einladungslink` mit `passtZu(token)` & `istGueltig()` ab. Nur wenn beides stimmt, wird der Nutzer Mitglied & bekommt Aktivitätspunkte; abgelaufene/zurückgezogene Links werden abgewiesen.
