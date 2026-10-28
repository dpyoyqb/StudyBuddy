/*
 * =====================================================================
 *  StudyBuddy – AnmeldeFenster.java
 * =====================================================================
 *  Das Fenster, das der Nutzer sieht. Gebaut mit "Swing", der
 *  Oberflächen-Bibliothek, die in Java schon eingebaut ist.
 *
 *  Das Fenster hat zwei "Seiten", zwischen denen wir umschalten:
 *    Seite "login"       -> E-Mail, Passwort, Knopf "Anmelden"
 *    Seite "begruessung" -> Teddybär winkt und sagt Hallo (F02)
 *
 *  Wichtige Swing-Begriffe:
 *    JFrame     = das Fenster selbst
 *    JPanel     = eine Fläche, auf die man Elemente legt
 *    JLabel     = ein Text oder Bild
 *    JTextField = ein Eingabefeld
 *    JPasswordField = Eingabefeld, das Punkte statt Buchstaben zeigt
 *    JButton    = ein Knopf
 * =====================================================================
 */

import javax.swing.*;   // alle Swing-Bausteine (JFrame, JButton, ...)
import java.awt.*;      // Farben, Schriften, Layouts

// "extends JFrame": Klasse IST ein Fenster & erbt alles, was ein normales Fenster kann (Titel, Größe, sichtbar machen, ...)
public class AnmeldeFenster extends JFrame {

    // -----------------------------------------------------------------
    //  Farben (aus unserem Logo)
    // -----------------------------------------------------------------
    private final Color BRAUN = new Color(158, 82, 40);
    private final Color BRAUN_DUNKEL = new Color(74, 44, 24);
    private final Color ROT = new Color(179, 38, 30);

    // -----------------------------------------------------------------
    //  Bestandteile, auf die wir später noch zugreifen müssen (deshalb hier oben als "Attribute" und nicht nur lokal)
    // -----------------------------------------------------------------
    private AnmeldeLogik logik;                          // die Prüf-Logik aus der anderen Datei
    private JTextField emailFeld = new JTextField();
    private JPasswordField passwortFeld = new JPasswordField();
    private JLabel fehlerText = new JLabel(" ");          // " " statt "", damit die Zeile Platz behält
    private JLabel begruessungText = new JLabel("Hallo!");

    // Zum Umschalten zwischen den zwei Seiten
    private CardLayout seitenWechsler = new CardLayout();
    private JPanel seiten = new JPanel(seitenWechsler);


    // =================================================================
    //  Konstruktor: wird einmal aufgerufen, wenn das Fenster erzeugt wird
    // =================================================================
    public AnmeldeFenster(AnmeldeLogik logik) {
        this.logik = logik;

        // Grundeinstellungen des Fensters
        setTitle("StudyBuddy – Anmelden");
        setSize(420, 600);                         // Breite, Höhe in Pixeln
        setLocationRelativeTo(null);               // in der Bildschirmmitte öffnen
        setDefaultCloseOperation(EXIT_ON_CLOSE);   // Programm beenden, wenn man das Fenster schließt

        // Beide Seiten bauen und dem Seitenwechsler geben
        seiten.add(loginSeiteBauen(), "login");
        seiten.add(begruessungsSeiteBauen(), "begruessung");
        add(seiten);

        // Enter-Taste soll wie ein Klick auf "Anmelden" wirken
        getRootPane().setDefaultButton(anmeldenKnopf);
    }


    // =================================================================
    //  1. Seite: Anmeldeformular
    // =================================================================

    // Knopf auch im Konstruktor (für die Enter-Taste)
    private JButton anmeldenKnopf = new JButton("Anmelden");

    private JPanel loginSeiteBauen() {
        JPanel seite = neueSeite();

        // Logo oben (Bild aus unserem Ordner)
        seite.add(bildLaden("StudyBuddy_Bilder/Logo.png", 220));

        // Überschrift
        JLabel titel = new JLabel("Willkommen zurück!");
        titel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titel.setForeground(BRAUN_DUNKEL);
        seite.add(titel);
        seite.add(Box.createVerticalStrut(20));    // 20 Pixel Abstand

        // E-Mail: Beschriftung + Feld
        seite.add(new JLabel("Hochschul-E-Mail (@bht-berlin.de)"));
        seite.add(emailFeld);
        seite.add(Box.createVerticalStrut(12));

        // Passwort: Beschriftung + Feld
        seite.add(new JLabel("Passwort"));
        seite.add(passwortFeld);
        seite.add(Box.createVerticalStrut(20));

        // Knopf "Anmelden"
        knopfGestalten(anmeldenKnopf);
        // Klick -> Methode anmeldenGeklickt() aufrufen.
        // "e -> ..." ist eine Kurzschreibweise (Lambda) für "wenn geklickt, dann ..."
        anmeldenKnopf.addActionListener(e -> anmeldenGeklickt());
        seite.add(anmeldenKnopf);
        seite.add(Box.createVerticalStrut(10));

        // Platz für Fehlermeldungen (anfangs leer)
        fehlerText.setForeground(ROT);
        seite.add(fehlerText);

        // Alle Felder gleich breit machen und linksbündig ausrichten - sieht besser aus
        felderAusrichten(seite);
        return seite;
    }


    // =================================================================
    // 2. Seite: Begrüßung durch den Teddybären (F02)
    // Die Seite sieht man nach einer erfolgreichen Anmeldung.
    // =================================================================
    private JPanel begruessungsSeiteBauen() {
        JPanel seite = neueSeite();

        //etwas Platz nach oben lassen -> sieht besser aus
        seite.add(Box.createVerticalStrut(30));
        //Bild vom winkenden Teddy (aus Ordner)
        seite.add(bildLaden("StudyBuddy_Bilder/Hello.png", 180));
        //Platz zwischen Bild & Text -> sieht besser aus
        seite.add(Box.createVerticalStrut(16));

        //Text groß und fett in dunkelbraun
        //der Name wird erst bei der Anmeldung eingesetzt
        begruessungText.setFont(new Font("SansSerif", Font.BOLD, 24));
        begruessungText.setForeground(BRAUN_DUNKEL);
        seite.add(begruessungText);

        //weiterer Willkommenssatz
        seite.add(new JLabel("Schön, dass du da bist. Lass uns gemeinsam lernen!"));

        //Platz lassen vor Abmelden Knopf
        seite.add(Box.createVerticalStrut(24));

        // Knopf "Abmelden" (in der passenden Farbe) erstellen
        JButton abmeldenKnopf = new JButton("Abmelden");
        knopfGestalten(abmeldenKnopf);

        //Aktionen, nachdem "Abmelden" geklickt wurde
        abmeldenKnopf.addActionListener(e -> {
            emailFeld.setText(""); // E-Mail-Feld leeren
            passwortFeld.setText(""); //Passwort-Feld leeren
            fehlerText.setText(" "); //mögliche alte Fehlermeldung entfernen
            seitenWechsler.show(seiten, "login"); // zur Anmeldeseite zurück wechseln
        });
        //Knopf auf Seite ablegen
        seite.add(abmeldenKnopf);

        //gut ausrichten alles (soll ja auch gut aussehen)
        felderAusrichten(seite);

        //fertige Seite
        return seite;
    }


    // =================================================================
    // Klick auf "Anmelden"
    // Methode läuft immer, wenn "Anmelden" geklickt wird/Enter gedrückt wird
    // =================================================================
    private void anmeldenGeklickt() {

        // 1. Eingaben aus den Feldern holen
        String email = emailFeld.getText();
        String passwort = new String(passwortFeld.getPassword());

        // 2. Ist ein Feld leer?
        if (email.isBlank() || passwort.isEmpty()) {
            fehlerText.setText("Bitte E-Mail und Passwort eingeben.");
            return;   // beendet die Methode –> der Rest wird übersprungen
        }

        // 3. Ist es eine Hochschul-Adresse? (F01)
        // (hier: wenn es keine ist, dann...)
        if (!logik.istUniEmail(email)) {
            fehlerText.setText("Nur Hochschul-Adressen (@bht-berlin.de) erlaubt.");
            return;
        }

        // 4. Passen E-Mail und Passwort?
        String name = logik.anmelden(email, passwort);
        passwortFeld.setText("");   // Passwort-Feld wird immer geleert, egal ob richtig o. falsch

        //Wenn kein Name zurückkommt, dann war etwas falsch
        if (name == null) {
            // Absichtlich allgemein: nicht verraten, ob E-Mail existiert o. nur  Passwort falsch -> verhindert herausfinden, welche Adressen registriert
            fehlerText.setText("E-Mail-Adresse oder Passwort ist falsch.");
            return;
        }

        // 5. Alles ist richtig -> Begrüßung anzeigen (F02)
        begruessungText.setText("Hallo " + name + "!");
        seitenWechsler.show(seiten, "begruessung");
    }


    // =================================================================
    //  Hilfsmethoden:für Aufgaben, die mehrmals vorkommen
    // =================================================================

    //leere Seite erstellen
    //wird für beide Seiten benötigt
    private JPanel neueSeite() {
        //neue leere Fläche
        JPanel seite = new JPanel();
        // BoxLayout Y_AXIS -> alles, was man hinzufügt -> untereinander
        seite.setLayout(new BoxLayout(seite, BoxLayout.Y_AXIS));
        //Hintergrund: weiß
        seite.setBackground(Color.WHITE);
        // Innenabstand: oben, links, unten, rechts
        seite.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        //fertige Seite zurück
        return seite;
    }

    //Knopf in den gewünschten Farben gestalten
    //genutzt für "Anmelden" & "Abmelden"
    private void knopfGestalten(JButton knopf) {
        knopf.setBackground(BRAUN); //Hintergrund: braun
        knopf.setForeground(Color.WHITE); //Schrift: weiß
        knopf.setFont(new Font("SansSerif", Font.BOLD, 15)); //Schrift
        knopf.setOpaque(true);
        knopf.setBorderPainted(false);
        knopf.setFocusPainted(false);
    }

  // Elemente ausrichten (linksbündig)
    private void felderAusrichten(JPanel seite) {
        //Jedes Element durchgehen
        for (Component teil : seite.getComponents()) {
            //linksbündig ausrichten
            ((JComponent) teil).setAlignmentX(Component.LEFT_ALIGNMENT);
            //Eingabefeld o. Knopf? -> so breit wie möglich (aber nur 40 Pixel hoch)
            if (teil instanceof JTextField || teil instanceof JButton) {
                // volle Breite, aber feste Höhe von 40 Pixeln
                teil.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            }
        }
    }

  // Bild laden
    // holt Bild aus Ordner & macht es kleiner
    private JLabel bildLaden(String pfad, int breite) {
        // Bild (aus Datei) laden
        ImageIcon original = new ImageIcon(pfad);

        // Bild nicht gefunden - Breite 0 o. kleiner
        if (original.getIconWidth() <= 0) {
            System.out.println("Bild nicht gefunden: " + pfad); //Hinweis
            return new JLabel("StudyBuddy"); //stattdessen Text
        }

        // verkleinern (Höhe passend zur Breite, damit es nicht verzerrt wird)
        int hoehe = breite * original.getIconHeight() / original.getIconWidth();
        Image klein = original.getImage().getScaledInstance(breite, hoehe, Image.SCALE_SMOOTH);
        //verkleinertes Bild zurück
        return new JLabel(new ImageIcon(klein));
    }
}
