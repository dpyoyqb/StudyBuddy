# Klassendiagramm – Notizen

## 1. Kernklassen (1b)

- Klassen haben wir aus unserer Anforderungsliste & dem Lastenheft abgeleitet

| Klassenname | Verantwortlichkeit | Beispiel-Attribute | Anforderungen |
|-------------|--------------------|--------------------|---------------|
| **Nutzer** | eine registrierte Person mit Hochschul-E-Mail-Adresse\ sie meldet sich an & lädt Dokumente hoch | nutzerId, name, email, passwortHash | F01, NF01 |
| **Dokument** | Oberklasse für alle hochgeladenen Lernunterlagen\ speichert die Datei, das Modul & ob die Rechte am Inhalt bestätigt wurden | titel, modul, dateipfad, rechteBestaetigt | F03–F05, NF04 |
| **Frage** | eine Frage, die an einer bestimmten Stelle in einem Dokument hinterlegt ist | text, seite, stelle, geloest | F06 |
| **Antwort** | Antwort eines anderen Nutzers auf eine Frage | text, erstelltAm | F07 |
| **Lernbereich** | ein geschützter Bereich für eine Lerngruppe\ nur Mitglieder sehen Inhalte | name, beschreibung, erstelltAm | F08, F09 |
| **Einladungslink** | Zugang zu einem Lernbereich\ er läuft nach einer Frist ab & kann zurückgezogen werden (gespeichert wird nur ein Hash) | tokenHash, gueltigBis, zurueckgezogen | F15, NF01 |
| **Avatar** | Teddybär eines Nutzers, der Accessoires tragen kann | motiv | F11, F12 |
| **Accessoire** | ein Extra für den Avatar, das man freischalten/ kaufen kann | name, benoetigtePunkte, preisInCent | F12, F13 |

## 2. Vererbung (2c)

- Notizen, Altklausuren & Vorlesungsunterlagen:
  - alle gleich hochgeladen, geteilt, heruntergeladen
  - zu allen kann man Fragen stellen \
    → gemeinsame Attribute & Methoden nur einmal in der Oberklasse `Dokument`
- Unterklassen ergänzen nur, was sie besonders macht:
  - Altklausur: Semester, Prüfer, mit Lösung
  - Vorlesungsunterlage: Art, aus Moodle importiert
  - Notiz: Stichworte
- „ist-ein“-Beziehung: 
  - eine Altklausur **ist ein** Dokument
- `Dokument` ist abstrakt:
  - es gibt kein „Dokument an sich“, nur bestimmte Arten
- nur zwei Ebenen -> übersichtlich 
- Transkript ist keine eigene Unterklasse:
  - unterscheidet sich nur in der Art von Skript & Folien \
    → geregelt über die Aufzählung `UnterlagenArt`

## 3. Validierung (3a)

| Prüfpunkt | Ergebnis | evtl. kurze Erklärung |
|-----------|----------|----------|
| Sind alle Klassennamen aussagekräftig und groß geschrieben? | ✔ |
| Haben alle Attribute sinnvolle Sichtbarkeiten (+, -, #)? | ✔ | Attribute sind privat (`-`), Methoden öffentlich (`+`), `dateitypPruefen()` ist geschützt (`#`) & nur für Unterklassen gedacht |
| Sind alle Attributtypen angegeben? (z.B. String, int, Date) | ✔ |
| Haben Methoden Parameter und Rückgabetypen? | ✔ |
| Sind alle Assoziationen mit Multiplizitäten versehen?  | ✔ |
| Sind Navigationsrichtungen korrekt (wo sinnvoll unidirektional)?  | ✔ | ein Dokument kennt seinen Autor, aber der Nutzer muss nicht alle Dokumente kennen\ Mitgliedschaft im Lernbereich gilt in beide Richtungen |
| Sind Aggregation/Komposition korrekt verwendet (oder einfache Assoziation)? | ✔ | Komposition nur dort, wo das Teil ohne das Ganze keinen Sinn hat (z. B. Frage ohne Dokument) |
| Ist Vererbung sinnvoll eingesetzt (nicht übertrieben)? | ✔ | nur eine Ebene unter `Dokument` |
| Ist das Diagramm übersichtlich und lesbar? | ✔ | nach Bereichen gruppiert |
| Bildet das Diagramm die zentralen Aspekte Ihres Systems ab? | ✔ | alle Muss-Anforderungen sind enthalten\ Melden von Inhalten & Rollen fehlen noch (siehe Review) |
