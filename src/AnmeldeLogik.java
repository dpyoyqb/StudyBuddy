/*
 * =====================================================================
 *  StudyBuddy – AnmeldeLogik.java
 * =====================================================================
 *  Das hier ist die eigentliche Anmelde-Logik.
 * Die Klasse prüft nur Daten (sie weiß nichts über Fenster, Knöpfe)
 *
 *  Folgende Anforderungen aus unserer Anforderungsliste werden umgesetzt:
 *    F01  Anmeldung nur mit E-Mail-Adresse der Uni-Domain
 *    NF01 Passwörter werden nur gehasht gespeichert, nie im Klartext
 * =====================================================================
 */

// "import" holt fertige Bausteine aus der Java-Bibliothek
import javax.crypto.SecretKeyFactory;   // berechnet den Passwort-Hash
import javax.crypto.spec.PBEKeySpec;    // "Bauplan" für den Hash (Passwort + Salt + Einstellungen)
import java.security.SecureRandom;      // erzeugt echte Zufallszahlen (für das Salt)
import java.util.Base64;                // wandelt Bytes in lesbaren Text um
import java.util.HashMap;               // eine Art Tabelle: Schlüssel -> Wert

public class AnmeldeLogik {

    // -----------------------------------------------------------------
    //  Einstellungen
    // -----------------------------------------------------------------

    // Nur E-Mail-Adressen mit "bht-berlin.de" Endung sind erlaubt (F01).
    // "final" = der Wert kann später nicht mehr geändert werden
    private final String ERLAUBTE_DOMAIN = "bht-berlin.de";

    // Wie oft der Hash-Vorgang wiederholt wird. Je höher, desto langsamer
    // wird das Durchprobieren von Passwörtern für Angreifer.
    private final int ITERATIONEN = 210000;

    // -----------------------------------------------------------------
    //  Gespeicherte Daten
    //  (Noch im Arbeitsspeicher – nach dem Beenden ist alles weg.
    //   Später ersetzen wir das durch eine Datenbank.)
    // -----------------------------------------------------------------

    // Tabelle: E-Mail -> gespeicherter Passwort-Hash
    private HashMap<String, String> passwortHashes = new HashMap<>();

    // Tabelle: E-Mail -> Anzeigename (für die Begrüßung)
    private HashMap<String, String> namen = new HashMap<>();


    //  1. Anforderung: E-Mail prüfen (F01)



    // Prüft, ob  eingegebene E-Mail-Adresse zur Hochschule gehört

    public boolean istUniEmail(String email) {

        // Leerzeichen am Rand entfernen & alles klein schreiben -> "Max@BHT-Berlin.de " genauso erkennen
        email = email.trim().toLowerCase();

        // Die Adresse am @ in zwei Teile zerlegen: "name" und "domain".
        String[] teile = email.split("@");

        // Prüfen: genau ein @ & vor dem @ muss irgendwas stehen
        if (teile.length != 2 || teile[0].isEmpty()) {
            return false;
        }

        String domain = teile[1];

        // Erlaubt ist "bht-berlin.de" oder etwas wie "student.bht-berlin.de".
        // Der Punkt vor der Domain ist wichtig: Sonst würde auch
        // "evilbht-berlin.de" durchrutschen, weil es ebenfalls so endet.
        return domain.equals(ERLAUBTE_DOMAIN) || domain.endsWith("." + ERLAUBTE_DOMAIN);
    }


    //  2. Nutzer registrieren

    //jetzt schon nötig, weil sonst gibt es keinen Nutzer und niemand kann sich anmelden
    //Später nutzen für richtige Registrierung (möglich)
    public void registrieren(String email, String name, String passwort) {
        //Leerzeiochen am Rand entfernen & alles klein
        email = email.trim().toLowerCase();

        //aus Passwort ein Hash machen -> nur Hash speichern (NF01)
        String hash = hashErzeugen(passwort);
        //in erste Tabelle eintragen: E-Mail -> Hash
        passwortHashes.put(email, hash);
        //in zweite Tabelle eintragen: E-Mail -> Name
        namen.put(email, name);
    }


    //  3. Anmelden


   //prüft: passen E-Mail udn passwort zusammen
    public String anmelden(String email, String passwort) {
        //wie bisher: Leerzeichen weg & alles klein
        email = email.trim().toLowerCase();

        // in Tabelle nachsehen, ob es E-Mail überhaupt gibt
        String gespeicherterHash = passwortHashes.get(email);
        //die E-Mail wurd nicht gefunden -> E-Mail ist nicht registriert
        if (gespeicherterHash == null) {
            return null;
        }

        // E-Mai okay -> Passwort prüfen
        // hashen der Eingabe mit demselben Salt & vergleichen beider Hashes -> beide gleich -> Passwort richtig
        if (passwortPruefen(passwort, gespeicherterHash)) {
            return namen.get(email);
        }

        return null;   // falsches Passwort
    }


    //  4) Hilfsmethoden fürs Hashen (NF01)
    // sorgt dafür, dass Passwörter NIE im Klartext gespeichert
    //  Verfahren: PBKDF2 (ist im JDK eingebaut, keine Zusatzbibliothek nötig)
    //  Gespeichert wird:  "Salt:Hash"  (beides als Text)

    // macht aus einem Passwort den Text "Salt:Hash" zum Speichern
    private String hashErzeugen(String passwort) {
        // 16 zufällige Bytes als Salt erzeugen
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);

        byte[] hash = pbkdf2(passwort, salt);

        // Bytes in Text umwandeln, damit speichern kann
        String saltText = Base64.getEncoder().encodeToString(salt);
        String hashText = Base64.getEncoder().encodeToString(hash);
        return saltText + ":" + hashText;
    }

   // prüft, ob ein eingegebenes Passwort zum gespeicherten "Salt:Hash" passt
    private boolean passwortPruefen(String passwort, String gespeichert) {
        // gespeicherten Text am ":" wieder in Salt & Hash zerlegen
        String[] teile = gespeichert.split(":");
        byte[] salt = Base64.getDecoder().decode(teile[0]);
        byte[] erwarteterHash = Base64.getDecoder().decode(teile[1]);

        // Eingabe mit DEMSELBEN Salt hashen
        byte[] neuerHash = pbkdf2(passwort, salt);

        // Beide Hashes vergleichen - Gleich -> Passwort richtig
        // MessageDigest.isEqual immer gleich lange –> Angreifer kann nicht an Zeit ablesen, wie viele Zeichen gestimmt
        return java.security.MessageDigest.isEqual(erwarteterHash, neuerHash);
    }

    //Eigentliche Hash-Berechnung
    //beide vorherigen Methoden nutzen das
    private byte[] pbkdf2(String passwort, byte[] salt) {
        //try: versuche das Folgende, wenn es nicht klappt, springe zu "catch"
        try {
            // Bauplan: Passwort + Salt + Anzahl Wiederholungen + Länge (256 Bit)
            PBEKeySpec bauplan = new PBEKeySpec(passwort.toCharArray(), salt, ITERATIONEN, 256);
            SecretKeyFactory rechner = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            //Hash ausrechnen & zurückgeben
            return rechner.generateSecret(bauplan).getEncoded();
        } catch (Exception e) {
            //wenn etws schiefgeht:: Programm mit Fehlermeldung abbrechen
            // Sollte nie passieren, weil PBKDF2 in jedem JDK enthalten ist
            throw new RuntimeException("Hashen nicht möglich", e);
        }
    }
}
