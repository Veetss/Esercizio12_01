import java.sql.*;

public class Tabelle {

    private final String url = "jdbc:mysql://localhost:3306/newdb";
    private final String user = "developer";
    private final String pw = "Password!";

    public void createTableFornitori() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pw);
        Statement statement = con.createStatement();

        String fornitori = ""
                + "CREATE TABLE IF NOT EXISTS newdb.fornitori ( "
                + "	nome varchar(100) NULL, "
                + "	indirizzo varchar(100) NULL, "
                + "	città varchar(100) NULL, "
                + "	codice_fornitore INT auto_increment NOT NULL, "
                + "	CONSTRAINT fornitori_pk PRIMARY KEY (codice_fornitore) "
                + ")";

        statement.executeUpdate(fornitori);

        con.close();
    }

    public void createTableProdotti() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pw);
        Statement statement = con.createStatement();

        String prodotti = ""
                + "CREATE TABLE IF NOT EXISTS newdb.prodotti ( "
                + "  `codice_prodotto` int NOT NULL AUTO_INCREMENT, "
                + "  `tipo` varchar(30) DEFAULT NULL, "
                + "  `marca` varchar(50) DEFAULT NULL, "
                + "  `modello` varchar(50) DEFAULT NULL, "
                + "	CONSTRAINT prodotti_pk PRIMARY KEY (codice_prodotto) "
                + ")";

        statement.executeUpdate(prodotti);

        con.close();
    }

    public void createTableCatalogo() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pw);
        Statement statement = con.createStatement();

        String catalogo = ""
                + "CREATE TABLE newdb.catalogo ( "
                + "	codice_fornitore INT NOT NULL, "
                + "	codice_prodotto INT NOT NULL, "
                + "	costo DOUBLE NULL, "
                + "	CONSTRAINT catalogo_pk PRIMARY KEY (codice_prodotto,codice_fornitore), "
                + "	CONSTRAINT catalogo_FK FOREIGN KEY (codice_fornitore) REFERENCES newdb.fornitori(codice_fornitore), "
                + "	CONSTRAINT catalogo_FK_1 FOREIGN KEY (codice_prodotto) REFERENCES newdb.prodotti(codice_prodotto) "
                + ")";

        statement.executeUpdate(catalogo);

        con.close();
    }

   public void insertFornitori(String nome, String indirizzo, String città) throws SQLException {
       Connection con = DriverManager.getConnection(url, user, pw);
       Statement statement = con.createStatement();

       statement.executeUpdate("insert into newdb.fornitori (nome, indirizzo, città) VALUES ('" + nome + "', '" + indirizzo + "', '" + città + "');");

       con.close();
   }

    public void insertProdotto(String marca, String tipo, String modello) throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pw);
        Statement statement = con.createStatement();

        statement.executeUpdate("insert into newdb.prodotti (marca, tipo, modello) VALUES ('" + marca + "', '" + tipo + "', '" + modello + "');");

        con.close();
    }

    public void insertCatalogo() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pw);
        Statement statement = con.createStatement();

        Integer fornitore_id = null;
        Integer prodotto_id = null;
        double costo = Math.random() * 100;

        statement.executeQuery("SELECT codice_fornitore, codice_prodotto from newdb.fornitori, newdb.prodotti LIMIT 1");

        ResultSet rs = statement.executeQuery("SELECT codice_fornitore, codice_prodotto from newdb.fornitori, newdb.prodotti LIMIT 1");

        while(rs.next()){
            fornitore_id = rs.getInt("codice_fornitore");
            prodotto_id = rs.getInt("codice_prodotto");
        }

        statement.executeUpdate("insert into newdb.catalogo (codice_fornitore, codice_prodotto, costo) VALUES ('" + fornitore_id + "', '" + prodotto_id + "', '" + costo + "');");

        con.close();
    }
}
