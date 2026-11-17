# Verhaltensdiagramme – Beschreibung (Übungsblatt 6, Aufgabe 1)

## Aktivitätsdiagramm: Dokument hochladen und teilen

Datei: `aktivitaetsdiagramm-dokument-hochladen.png`

**Modellierter Use Case:** „Nutzer lädt ein Dokument hoch & teilt es“ (LF20 / F03–F05). Das ist die Kernfunktion von StudyBuddy: 
  Ohne geteilte Unterlagen gibt es keine Fragen, keine Lernbereiche mit Inhalt & keine Aktivitätspunkte für den Avatar

**Wichtigste Entscheidungspunkte:**
- *Nutzer angemeldet?* – nur angemeldete Nutzer mit Hochschul-E-Mail dürfen hochladen, sonst Weiterleitung zur Anmeldung
- *Eingaben gültig?* – geprüft werden Dateityp, Dateigröße, Pflichtfelder (Titel, Modul, Dokumenttyp) & die Bestätigung der Rechte am Inhalt (NF04)
- *Lernbereich gewählt?* – entweder wird das Dokument einem geschützten Lernbereich zugeordnet (nur für Mitglieder sichtbar) oder für alle Hochschulangehörigen freigegeben

Nach dem Speichern laufen zwei Aktionen parallel (Fork/Join): (1) Aktivitätspunkte gutschreiben & (2) Mitglieder bzw. Interessierte benachrichtigen

**Fehler und Ausnahmen:**
- Nicht angemeldet → Weiterleitung zur Anmeldung → der Ablauf endet
- Ungültige Eingaben/fehlende Rechtebestätigung → Fehlermeldung, der Nutzer landet wieder beim Formular (Schleife) → es wird nichts gespeichert
