# Klassendiagramm – Review mit GenAI

**Werkzeug:** ChatGPT (OpenAI), November 2026

**Prompt:** 
- ChatGPT das Projekt, die wichtigsten Anforderungen und eine Kurzfassung des Klassendiagramms (Klassen und Beziehungen mit Multiplizitäten) gegeben & gefragt:
  1. Fehlen wichtige Klassen?
  2. Sind die Beziehungen sinnvoll modelliert?
  3. Ein konkreter Verbesserungsvorschlag.

## Wichtigste Erkenntnisse

- **Fehlende Klassen:**
    - Für das Melden unangemessener Inhalte (LF70) fehlt eine Klasse `Meldung` mit meldendem Nutzer, gemeldetem Inhalt, Grund und Bearbeitungsstatus.
    - Außerdem fehlt eine `Rolle`, um Studierende und Lehrende mit unterschiedlichen Rechten zu unterscheiden.
- **Beziehungen:**
    - Die Kompositionen (Nutzer–Avatar, Dokument–Frage–Antwort, Lernbereich–Einladungslink) wurden als sinnvoll bestätigt.
    - Beim Avatar fehlt noch die Unterscheidung, ob ein Accessoire durch Aktivität freigeschaltet/ gekauft wurde.
- **Kritische Prüfung der KI-Antwort:**
    - Drei Hinweise waren falsch, weil sie bereits umgesetzt sind:
        - Ablauf und Widerruf von Einladungen (`Einladungslink`)
        - die Viele-zu-Viele-Beziehung zwischen Lernbereich und Nutzer (`0..*` – `1..*`)
        - Transkripte als Unterlagenart (`UnterlagenArt.TRANSKRIPT`)\
      -> KI-Feedback muss also immer mit dem Diagramm abgeglichen werden.

## Was wir übernehmen

- Klasse `Meldung` & Attribut `rolle` am Nutzer in der nächsten Version des Diagramms
- Unterscheidung „freigeschaltet“ und „gekauft“ bei Accessoires (z. B. über eine Klasse `Kauf`)
