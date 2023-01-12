import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Tabelle ogg = new Tabelle();

        ogg.createTableFornitori();
        ogg.createTableProdotti();
        ogg.createTableCatalogo();

        System.out.println("The table \"fornitori\" has been created!\n");
        System.out.println("The table \"prodotti\" has been created!\n");
        System.out.println("The table \"catalogo\" has been created!\n\n");

        ogg.insertFornitori("Ladroni", "Via Ostense", "Roma");
        ogg.insertFornitori("Risparmietti", "Viale Marconi", "Roma");
        ogg.insertFornitori("Teleporto", "Via Roma", "Milano");

        System.out.println("You have added some fornitori in your table \"fornitori\"!\n");

        ogg.insertProdotto("logitech", "tastiera", "AF13");
        ogg.insertProdotto("logitech", "mouse", "YS64");
        ogg.insertProdotto("logitech", "cuffie wireless", "OR67-3");

        System.out.println("You have added some prodotti in your table \"prodotti\"!\n\n");

        ogg.insertCatalogo();

        System.out.println("You have populated your table \"catalogo\" with your first product!");
    }
}