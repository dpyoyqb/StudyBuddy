/*
 * =====================================================================
 *  StudyBuddy – Main.java
 * =====================================================================
 * zum Ausführen auf den grünen Pfeil neben "main" klicken
 *
 *  Das Programm besteht aus drei Dateien:
 *    Main.java           -> startet alles (diese Datei)
 *    AnmeldeLogik.java   -> prüft E-Mail und Passwort (macht die "Denkarbeit")
 *    AnmeldeFenster.java -> zeigt das Fenster an (das, was man sieht)
 *
 * durch das Trennen von Logik & Ansicht (Anmeldefenster) können die Bereiche unabhängig voneinander verbessert/korrigiert/verändert werden.
 * =====================================================================
 */
public class Main {

    public static void main(String[] args) {

        // 1. Die Logik erzeugen. Sie merkt sich alle Nutzer.
        AnmeldeLogik logik = new AnmeldeLogik();

        // 2. Ein Testkonto anlegen.
        //Da es noch keine Registrierungen (Datenbank) gibt, wird ein Testaccount angelegt.
        //    Mit dem Testaccount kann man sich anmelden zum Testen der Funktion
        //      E-Mail:   test@bht-berlin.de
        //      Passwort: StudyBuddy2026!
        logik.registrieren("test@bht-berlin.de", "Test", "StudyBuddy2026!");

        // 3. Das Fenster erzeugen und anzeigen.
        //Dem Fenster muss die Logik mitgegeben werden, damit bei einem Klick auf "Anmelden" die Prüfung aufgerufen werden kann.
        AnmeldeFenster fenster = new AnmeldeFenster(logik);
        fenster.setVisible(true);
    }
}
