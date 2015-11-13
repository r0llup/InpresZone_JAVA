/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InpresZone.java
 *
 * Created on 10 oct. 2011, 15:53:11
 */

package inpreszone_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.HashSet;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import oracle.jdbc.OracleCallableStatement;
import org.ektorp.AttachmentInputStream;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import utils.PropertiesLauncher;

/**
 *
 * @author Sh1fT
 */

public final class InpresZone extends javax.swing.JFrame {
    protected PropertiesLauncher propertiesLauncher;
    protected HttpClient httpClient;
    protected CouchDbInstance couchDbInstance;
    protected CouchDbConnector couchDbConnector;
    protected Connection oracleConnection;
    protected Connection mysqlConnection;
    protected java.sql.Statement oracleStatement;
    protected java.sql.Statement mysqlStatement;
    protected OracleCallableStatement psBook;
    protected OracleCallableStatement psMovie;
    protected OracleCallableStatement psMusic;
    protected HashSet<Product> productList;
    protected DefaultListModel productJListModel;
    protected String selectedDB;

    /** Creates new form InpresZone */
    public InpresZone() {
        this.initComponents();
        this.setPropertiesLauncher(new PropertiesLauncher(
            System.getProperty("file.separator") + "properties" +
            System.getProperty("file.separator") + "InpresZone_JAVA.properties"));
        this.setProductJListModel(new DefaultListModel());
        this.setSelectedDB("DB_US");
        this.productJList.setModel(this.getProductJListModel());
        new ChooseDB(this, true).setVisible(true);
        this.filterInterface();
        /*if (this.selectedDB.compareTo("DB_UK") == 0)
        this.initMySqlDB();
        else
        this.initOracleDB();*/
        this.initCouchDB();
        this.initOracleDB();
    }

    public PropertiesLauncher getPropertiesLauncher() {
        return this.propertiesLauncher;
    }

    protected void setPropertiesLauncher(PropertiesLauncher propertiesLauncher) {
        this.propertiesLauncher = propertiesLauncher;
    }

    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    protected void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public CouchDbInstance getCouchDbInstance() {
        return this.couchDbInstance;
    }

    protected void setCouchDbInstance(CouchDbInstance couchDbInstance) {
        this.couchDbInstance = couchDbInstance;
    }

    public CouchDbConnector getCouchDbConnector() {
        return this.couchDbConnector;
    }

    protected void setCouchDbConnector(CouchDbConnector couchDbConnector) {
        this.couchDbConnector = couchDbConnector;
    }

    public Connection getOracleConnection() {
        return this.oracleConnection;
    }

    protected void setOracleConnection(Connection oracleConnection) {
        this.oracleConnection = oracleConnection;
    }

    public Connection getMysqlConnection() {
        return mysqlConnection;
    }

    protected void setMysqlConnection(Connection mysqlConnection) {
        this.mysqlConnection = mysqlConnection;
    }

    public Statement getOracleStatement() {
        return this.oracleStatement;
    }

    protected void setOracleStatement(Statement oracleStatement) {
        this.oracleStatement = oracleStatement;
    }

    public Statement getMysqlStatement() {
        return mysqlStatement;
    }

    protected void setMysqlStatement(Statement mysqlStatement) {
        this.mysqlStatement = mysqlStatement;
    }

    public OracleCallableStatement getPsBook() {
        return this.psBook;
    }

    protected void setPsBook(OracleCallableStatement psBook) {
        this.psBook = psBook;
    }

    public OracleCallableStatement getPsMovie() {
        return this.psMovie;
    }

    protected void setPsMovie(OracleCallableStatement psMovie) {
        this.psMovie = psMovie;
    }

    public OracleCallableStatement getPsMusic() {
        return this.psMusic;
    }

    protected void setPsMusic(OracleCallableStatement psMusic) {
        this.psMusic = psMusic;
    }

    public HashSet<Product> getProductList() {
        return this.productList;
    }

    protected void setProductList(HashSet<Product> productList) {
        this.productList = productList;
    }

    public DefaultListModel getProductJListModel() {
        return this.productJListModel;
    }

    protected void setProductJListModel(DefaultListModel productJListModel) {
        this.productJListModel = productJListModel;
    }

    public String getSelectedDB() {
        return this.selectedDB;
    }

    public void setSelectedDB(String selectedDB) {
        this.selectedDB = selectedDB;
    }

    public JProgressBar getProductProgressBar() {
        return this.productProgressBar;
    }

    public JRadioButton getCritereRadioButton() {
        return this.critereRadioButton;
    }

    public JCheckBox getArtisteCheckBox() {
        return this.artisteCheckBox;
    }

    public JTextField getArtisteTextField() {
        return this.artisteTextField;
    }

    public JCheckBox getPrixCheckBox() {
        return this.prixCheckBox;
    }

    public JSpinner getPrixMinSpinner() {
        return this.prixMinSpinner;
    }

    public JSpinner getPrixMaxSpinner() {
        return this.prixMaxSpinner;
    }

    public JCheckBox getCategorieCheckBox() {
        return this.categorieCheckBox;
    }

    public JComboBox getCategorieComboBox() {
        return this.categorieComboBox;
    }

    public JCheckBox getLangueCheckBox() {
        return this.langueCheckBox;
    }

    public JTextField getLangueTextField() {
        return this.langueTextField;
    }

    public JRadioButton getAsinRadioButton() {
        return this.asinRadioButton;
    }

    public JTextField getAsinTextField() {
        return this.asinTextField;
    }

    public JButton getOkButton() {
        return this.okButton;
    }

    public String getCouchDBHost() {
        return this.getPropertiesLauncher().getProperties().getProperty("couchDBHost");
    }

    public String getOracleHostDB_US() {
        return this.getPropertiesLauncher().getProperties().getProperty("oracleHostDB_US");
    }

    public String getOracleHostDB_UK() {
        return this.getPropertiesLauncher().getProperties().getProperty("oracleHostDB_UK");
    }

    public Integer getMysqlHost() {
        return Integer.parseInt(this.getPropertiesLauncher().getProperties().getProperty("mysqlHost"));
    }

    public String getOracleHostDB_BEL() {
        return this.getPropertiesLauncher().getProperties().getProperty("oracleHostDB_BEL");
    }

    public Integer getCouchDBPort() {
        return Integer.parseInt(this.getPropertiesLauncher().getProperties().getProperty("couchDBPort"));
    }

    public String getOraclePortDB_US() {
        return this.getPropertiesLauncher().getProperties().getProperty("oraclePortDB_US");
    }

    public String getOraclePortDB_UK() {
        return this.getPropertiesLauncher().getProperties().getProperty("oraclePortDB_UK");
    }

    public Integer getMysqlPort() {
        return Integer.parseInt(this.getPropertiesLauncher().getProperties().getProperty("mysqlPort"));
    }

    public String getOraclePortDB_BEL() {
        return this.getPropertiesLauncher().getProperties().getProperty("oraclePortDB_BEL");
    }

    public String getCouchDBTable() {
        return this.getPropertiesLauncher().getProperties().getProperty("couchDBTable");
    }

    public String getOracleSIDDB_US() {
        return this.getPropertiesLauncher().getProperties().getProperty("oracleSIDDB_US");
    }

    public String getOracleSIDDB_UK() {
        return this.getPropertiesLauncher().getProperties().getProperty("oracleSIDDB_UK");
    }

    public String getMysqlDbName() {
        return this.getPropertiesLauncher().getProperties().getProperty("mysqlDbName");
    }

    public String getOracleSIDDB_BEL() {
        return this.getPropertiesLauncher().getProperties().getProperty("oracleSIDDB_BEL");
    }

    public String getOracleUsernameDB_US() {
        return this.getPropertiesLauncher().getProperties().getProperty("oracleUsernameDB_US");
    }

    public String getOracleUsernameDB_UK() {
        return this.getPropertiesLauncher().getProperties().getProperty("oracleUsernameDB_UK");
    }

    public String getMysqlUsername() {
        return this.getPropertiesLauncher().getProperties().getProperty("mysqlUsername");
    }

    public String getOracleUsernameDB_BEL() {
        return this.getPropertiesLauncher().getProperties().getProperty("oracleUsernameDB_BEL");
    }

    public String getOraclePasswordDB_US() {
        return this.getPropertiesLauncher().getProperties().getProperty("oraclePasswordDB_US");
    }

    public String getOraclePasswordDB_UK() {
        return this.getPropertiesLauncher().getProperties().getProperty("oraclePasswordDB_UK");
    }

    public String getMysqlPassword() {
        return this.getPropertiesLauncher().getProperties().getProperty("mysqlPassword");
    }

    public String getOraclePasswordDB_BEL() {
        return this.getPropertiesLauncher().getProperties().getProperty("oraclePasswordDB_BEL");
    }

    public String getBookQuery() {
        return this.getPropertiesLauncher().getProperties().getProperty("bookQuery");
    }

    public String getMovieQuery() {
        return this.getPropertiesLauncher().getProperties().getProperty("movieQuery");
    }

    public String getMusicQuery() {
        return this.getPropertiesLauncher().getProperties().getProperty("musicQuery");
    }

    /**
     * Permet de filtrer l'ui selon la base de données sélectionnée.
     */
    private void filterInterface() {
        this.setTitle("InpresZone - " + this.getSelectedDB());
        if (this.getSelectedDB().compareTo("DB_US") == 0) {
            ((DefaultComboBoxModel) this.getCategorieComboBox().getModel()).removeElementAt(1);
            ((DefaultComboBoxModel) this.getCategorieComboBox().getModel()).removeElementAt(1);
            this.getCategorieCheckBox().setSelected(true);
            this.getCategorieCheckBox().setEnabled(false);
            this.getLangueTextField().setText("English");
        } else if (this.getSelectedDB().compareTo("DB_UK") == 0) {
            ((DefaultComboBoxModel) this.getCategorieComboBox().getModel()).removeElementAt(0);
            ((DefaultComboBoxModel) this.getCategorieComboBox().getModel()).removeElementAt(1);
            this.getCategorieCheckBox().setSelected(true);
            this.getCategorieCheckBox().setEnabled(false);
            this.getLangueTextField().setText("English");
        } else if (this.getSelectedDB().compareTo("DB_BEL") == 0)
            this.getLangueTextField().setText("Français");
        this.getLangueTextField().setEditable(false);
        this.getLangueCheckBox().setSelected(true);
        this.getLangueCheckBox().setEnabled(false);
        this.getCritereRadioButton().setSelected(true);
    }

    /**
     * Initialise une connexion avec la base de données CouchDB
     */
    protected void initCouchDB() {
        String host = this.getCouchDBHost();
        int port = this.getCouchDBPort();
        String table = this.getCouchDBTable();
        this.setHttpClient(new StdHttpClient.Builder().host(host).port(port).build());
        this.setCouchDbInstance(new StdCouchDbInstance(this.getHttpClient()));
        this.setCouchDbConnector(new StdCouchDbConnector(table, this.getCouchDbInstance()));
    }

    /**
     * Initialise une connexion avec la base de données Oracle
     */
    protected void initOracleDB() {
        String url = "jdbc:oracle:thin:@" +
            this.getPropertiesLauncher().getProperties().getProperty("oracleHost" + this.selectedDB) +
            ":" + this.getPropertiesLauncher().getProperties().getProperty("oraclePort" + this.selectedDB) +
            ":" + this.getPropertiesLauncher().getProperties().getProperty("oracleSID" + this.selectedDB);
        String username = this.getPropertiesLauncher().getProperties().getProperty("oracleUsername" + this.selectedDB);
        String password = this.getPropertiesLauncher().getProperties().getProperty("oraclePassword" + this.selectedDB);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.setOracleConnection(DriverManager.getConnection(url, username, password));
            this.getOracleConnection().setAutoCommit(false);
            this.setOracleStatement(this.getOracleConnection().createStatement());
            this.setPsBook((OracleCallableStatement) this.getOracleConnection().prepareCall(this.getBookQuery()));
            this.setPsMovie((OracleCallableStatement) this.getOracleConnection().prepareCall(this.getMovieQuery()));
            this.setPsMusic((OracleCallableStatement) this.getOracleConnection().prepareCall(this.getMusicQuery()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /** 
     * Initialise une connexion avec la base de données MySQL
     */
     protected void initMySqlDB() {
        String url = "jdbc:mysql://" + this.getMysqlHost() + ":" +
            this.getMysqlPort() + "/" + this.getMysqlDbName();
        String username = this.getMysqlUsername();
        String password = this.getMysqlPassword();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.setMysqlConnection(DriverManager.getConnection(url, username, password));
            this.getMysqlConnection().setAutoCommit(false);
            this.setMysqlStatement(this.getMysqlConnection().createStatement());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
     }

    /**
     * Ferme la connexion avec la base de données Oracle
     */
    protected void closeOracleDB() {
        try {
            this.getPsBook().close();
            this.getPsMovie().close();
            this.getPsMusic().close();
            this.getOracleStatement().close();
            this.getOracleConnection().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** 
     * Ferme la connexion avec la base de données MySQL
     */
    protected void closeMySqlDB() {
        try {
            this.getMysqlStatement().close();
            this.getMysqlConnection().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Effectue une requête vers CouchDB afin de récupérer tout les documents
     */
    protected void feedByAll() {
        /* "all": {
         *      "map": "function(doc) { if (doc.asin) emit(doc._id, doc); }"
         * },
         */
        ViewQuery query = new ViewQuery().designDocId("_design/main").viewName("all");
        this.setProductList(new HashSet<>(this.getCouchDbConnector().queryView(query, Product.class)));
    }

    /**
     * Effectue une requête vers CouchDB afin de récupérer les documents
     * relatif à un certain asin
     * @param asin
     */
    protected void feedByAsin(String asin) {
        /*
         * "asin_doc": {
         *      "map": "function (doc) { if (doc.asin) emit(doc.asin, doc); }"
         * },
         */
        ViewQuery query = new ViewQuery().designDocId("_design/main").viewName("asin_doc").key(asin);
        this.setProductList(new HashSet<>(this.getCouchDbConnector().queryView(query, Product.class)));
    }

    /**
     * Effectue une requête vers CouchDB afin de récupérer les documents
     * relatif à un certain auteur
     * @param author
     */
    protected void feedByAuthor(String author) {
        /* "authors_doc": {
         *      "map": "function (doc) { if (doc.category == 'book') { for (var i in doc.authors) emit(doc.authors[i], doc); } }"
         * },
         */
        ViewQuery query = new ViewQuery().designDocId("_design/main").viewName("authors_doc").key(author);
        HashSet<Product> productListAuthor = new HashSet<>(this.getCouchDbConnector().queryView(query, Product.class));
        if (this.getProductList() == null)
            this.setProductList(productListAuthor);
        else
            this.getProductList().retainAll(productListAuthor);
    }

    /**
     * Effectue une requête vers CouchDB afin de récupérer les documents
     * relatif à un certain acteur
     * @param actor
     */
    protected void feedByActor(String actor) {
        /* "actors_doc": {
         *      "map": "function (doc) { if (doc.category == 'movie') { for (var i in doc.actors) emit(doc.actors[i], doc); } }"
         * },
         */
        ViewQuery query = new ViewQuery().designDocId("_design/main").viewName("actors_doc").key(actor);
        HashSet<Product> productListActor = new HashSet<>(this.getCouchDbConnector().queryView(query, Product.class));
        if (this.getProductList() == null)
            this.setProductList(productListActor);
        else
            this.getProductList().retainAll(productListActor);
    }

    /**
     * Effectue une requête vers CouchDB afin de récupérer les documents
     * relatif à un certain artiste
     * @param artist
     */
    protected void feedByArtist(String artist) {
        /* "artist_doc": {
         *      "map": "function (doc) { if (doc.category == 'music') { for (var i in doc.artist) emit(doc.artist[i], doc); } }"
         * },
         */
        ViewQuery query = new ViewQuery().designDocId("_design/main").viewName("artist_doc").key(artist);
        HashSet<Product> productListArtist = new HashSet<>(this.getCouchDbConnector().queryView(query, Product.class));
        if (this.getProductList() == null)
            this.setProductList(productListArtist);
        else
            this.getProductList().retainAll(productListArtist);
    }

    /**
     * Effectue une requête vers CouchDB afin de récupérer les documents
     * relatif compris dans une fourchette de prix
     * @param minPrice
     * @param maxPrice
     */
    protected void feedByPrice(Double minPrice, Double maxPrice) {
        /*
         * "price_doc": {
         *      "map": "function (doc) { if (doc.price) emit(doc.price, doc); }"
         * },
         */
        ViewQuery query = new ViewQuery().designDocId("_design/main").viewName("price_doc").startKey(minPrice).endKey(maxPrice);
        HashSet<Product> productListPrice = new HashSet<>(this.getCouchDbConnector().queryView(query, Product.class));
        if (this.getProductList() == null)
            this.setProductList(productListPrice);
        else
            this.getProductList().retainAll(productListPrice);
    }

    /**
     * Effectue une requête vers CouchDB afin de récupérer les documents
     * relatif à une certaine catégorie
     * @param category
     */
    protected void feedByCategory(String category) {
        /* "category_doc": {
         *      "map": "function (doc) { if (doc.category) emit(doc.category, doc); }"
         * },
         */
        ViewQuery query = new ViewQuery().designDocId("_design/main").viewName("category_doc").key(category);
        HashSet<Product> productListCategory = new HashSet<>(this.getCouchDbConnector().queryView(query, Product.class));
        if (this.getProductList() == null)
            this.setProductList(productListCategory);
        else
            this.getProductList().retainAll(productListCategory);
    }

    /**
     * Effectue une requête vers CouchDB afin de récupérer les documents
     * relatif à une certaine langue
     * @param language
     */
    protected void feedByLanguage(String language) {
        /* "language_doc": {
         *      "map": "function (doc) { if (doc.language) emit(doc.language, doc); }"
         * },
         */
        ViewQuery query = new ViewQuery().designDocId("_design/main").viewName("language_doc").key(language);
        HashSet<Product> productListLanguage = new HashSet<>(this.getCouchDbConnector().queryView(query, Product.class));
        if (this.getProductList() == null)
            this.setProductList(productListLanguage);
        else
            this.getProductList().retainAll(productListLanguage);
    }

    /**
     * Effectue une requête vers CouchDB afin de récupérer les documents
     * relatif à une certaine langue
     * @param languages
     */
    protected void feedByLanguages(String languages) {
        /* "languages_doc": {
         *      "map": "function (doc) { if (doc.languages) { for (var i in doc.languages) emit(doc.languages[i], doc); } }"
         * },
         */
        ViewQuery query = new ViewQuery().designDocId("_design/main").viewName("languages_doc").key(languages);
        HashSet<Product> productListLanguages = new HashSet<>(this.getCouchDbConnector().queryView(query, Product.class));
        if (this.getProductList() == null)
            this.setProductList(productListLanguages);
        else
            this.getProductList().retainAll(productListLanguages);
    }

    /**
     * Effectue le formattage d'une date selon divers critères
     * @param date
     * @return Date
     */
    protected java.sql.Date checkForDate(String date) {
        // long french format
        if (Utils.stringToDate(date, DateFormat.LONG, Locale.FRENCH) != null)
            return Utils.stringToDate(date, DateFormat.LONG, Locale.FRENCH);
        // long english format
        if (Utils.stringToDate(date, DateFormat.LONG, Locale.ENGLISH) != null)
            return Utils.stringToDate(date, DateFormat.LONG, Locale.ENGLISH);
        // french custom format
        if (Utils.stringToDate(date, "MMMM yyyy", Locale.FRENCH) != null)
            return Utils.stringToDate(date, "MMMM yyyy", Locale.FRENCH);
        // english custom format
        if (Utils.stringToDate(date, "MMMM yyyy", Locale.ENGLISH) != null)
            return Utils.stringToDate(date, "MMMM yyyy", Locale.ENGLISH);
        // custom format
        if (Utils.stringToDate(date, "yyyy") != null)
            return Utils.stringToDate(date, "yyyy");
        return null;
    }

    /**
     * Insère les médias dans la requête
     * @param psMedia
     * @param p
     * @throws SQLException
     */
    protected void insertMedia(OracleCallableStatement psMedia, Product p) throws SQLException {
        if (p.getTitle() != null)
            psMedia.setString(1, p.getTitle());
        else
            psMedia.setNull(1, java.sql.Types.VARCHAR);
        psMedia.setDouble(2, p.getPrice());
        psMedia.setNull(3, java.sql.Types.VARCHAR);
        psMedia.setNull(4, java.sql.Types.VARCHAR);
        p.setFournisseur(p.getAsin());
        if (p.getFournisseur() != null)
            psMedia.setString(5, p.getFournisseur());
        else
            psMedia.setNull(5, java.sql.Types.VARCHAR);
        psMedia.setInt(6, 4000);
        AttachmentInputStream stream = Utils.getBlob(this.getCouchDbConnector(), p.get_id());
        if (stream != null)
            psMedia.setBlob(7, stream);
        else
            psMedia.setNull(7, java.sql.Types.BLOB);
        if (p.getRank() != null)
            psMedia.setDouble(8, p.getRank());
        else
            psMedia.setNull(8, java.sql.Types.DOUBLE);
        if (p.getReview() != null)
            psMedia.setDouble(9, p.getReview());
        else
            psMedia.setNull(9, java.sql.Types.DOUBLE);
        if (p.getWeight() != null)
            psMedia.setDouble(10, p.getWeight());
        else
            psMedia.setNull(10, java.sql.Types.DOUBLE);
        if (p.getDim_width() != null)
            psMedia.setDouble(11, p.getDim_width());
        else
            psMedia.setNull(11, java.sql.Types.DOUBLE);
        if (p.getDim_height() != null)
            psMedia.setDouble(12, p.getDim_height());
        else
            psMedia.setNull(12, java.sql.Types.DOUBLE);
        if (p.getDim_depth() != null)
            psMedia.setDouble(13, p.getDim_depth());
        else
            psMedia.setNull(13, java.sql.Types.DOUBLE);
        psMedia.setString(14, p.getCategory());
        psMedia.setString(15, p.getCategory_amazon());
    }

    /**
     * Insère les livres dans la requête SQL et l'exécute
     * @param p
     * @throws SQLException 
     */
    protected void insertBook(Product p) throws SQLException {
        this.insertMedia(this.getPsBook(), p);
        if (p.getLanguage() != null)
            this.getPsBook().setString(16, p.getLanguage());
        else
            this.getPsBook().setString(16, "Unknown");
        this.getPsBook().setString(17, p.getAsin());
        if (p.getPublisher() != null)
            this.getPsBook().setString(18, p.getPublisher());
        else
            this.getPsBook().setNull(18, java.sql.Types.VARCHAR);
        if (p.getPages() != null)
            this.getPsBook().setInt(19, p.getPages());
        else
            this.getPsBook().setNull(19, java.sql.Types.NUMERIC);
        if (p.getDate() != null)
            this.getPsBook().setDate(20, this.checkForDate(p.getDate()));
        else
            this.getPsBook().setNull(20, java.sql.Types.DATE);
        if (p.getEdition() != null)
            this.getPsBook().setString(21, p.getEdition());
        else
            this.getPsBook().setNull(21, java.sql.Types.VARCHAR);
        if (p.getFormat() != null)
            this.getPsBook().setString(22, p.getFormat());
        else
            this.getPsBook().setNull(22, java.sql.Types.VARCHAR);
        if (p.getAuthors() != null)
            this.getPsBook().setString(23, Utils.arrayToString(Utils.fixDupValues(p.getAuthors())));
        else
            this.getPsBook().setString(23, "Unknown,");
        this.getPsBook().executeUpdate();
    }

    /**
     * Insère les films dans la requête SQL et l'exécute
     * @param p
     * @throws SQLException 
     */
    protected void insertMovie(Product p) throws SQLException {
        this.insertMedia(this.getPsMovie(), p);
        String langs = Utils.arrayToString(Utils.fixDupValues(p.getLanguages()));
        if (langs != null)
            this.getPsMovie().setString(16, langs);
        else
            this.getPsMovie().setString(16, "Unknown,");
        if (p.getStudio() != null)
            this.getPsMovie().setString(17, p.getStudio());
        else
            this.getPsMovie().setNull(17, java.sql.Types.VARCHAR);
        if (p.getDiscs() != null)
            this.getPsMovie().setInt(18, p.getDiscs());
        else
            this.getPsMovie().setNull(18, java.sql.Types.NUMERIC);
        if (p.getRating() != null)
            this.getPsMovie().setString(19, p.getRating());
        else
            this.getPsMovie().setNull(19, java.sql.Types.VARCHAR);
        if (p.getRuntime() != null)
            this.getPsMovie().setString(20, p.getRuntime());
        else
            this.getPsMovie().setNull(20, java.sql.Types.VARCHAR);
        if (p.getRegion() != null)
            this.getPsMovie().setInt(21, p.getRegion());
        else
            this.getPsMovie().setNull(21, java.sql.Types.NUMERIC);
        if (p.getFormat() != null)
            this.getPsMovie().setString(22, p.getFormat());
        else
            this.getPsMovie().setNull(22, java.sql.Types.VARCHAR);
        if (p.getRelease_date() != null)
            this.getPsMovie().setDate(23, this.checkForDate(p.getRelease_date()));
        else
            this.getPsMovie().setNull(23, java.sql.Types.DATE);
        String subs = Utils.arrayToString(Utils.fixDupValues(p.getSubtitles()));
        if (subs != null)
            this.getPsMovie().setString(24, subs);
        else
            this.getPsMovie().setString(24, "Unknown,");
        String actors = Utils.arrayToString(Utils.fixDupValues(p.getActors()));
        if (actors != null)
            this.getPsMovie().setString(25, actors);
        else
            this.getPsMovie().setString(25, "Unknown,");
        String directors = Utils.arrayToString(Utils.fixDupValues(p.getDirectors()));
        if (directors != null)
            this.getPsMovie().setString(26, directors);
        else
            this.getPsMovie().setString(26, "Unknown,");
        this.getPsMovie().executeUpdate();
    }

    /**
     * Insère les musiques dans la requête et l'exécute
     * @param p
     * @throws SQLException 
     */
    protected void insertMusic(Product p) throws SQLException {
        this.insertMedia(this.getPsMusic(), p);
        this.getPsMusic().setString(16, "Unknown");
        this.getPsMusic().setString(17, p.getLabel());
        if (p.getDiscs() != null)
            this.getPsMusic().setInt(18, p.getDiscs());
        else
            this.getPsMusic().setNull(18, java.sql.Types.NUMERIC);
        if (p.getRelease_date() != null)
            this.getPsMusic().setDate(19, this.checkForDate(p.getRelease_date()));
        else
            this.getPsMusic().setNull(19, java.sql.Types.DATE);
        String artists = Utils.arrayToString(Utils.fixDupValues(p.getArtist()));
        if (artists != null)
            this.getPsMusic().setString(20, artists);
        else
            this.getPsMusic().setString(20, "Unknown,");
        this.getPsMusic().executeUpdate();
    }

    /**
     * Permet l'insertion des livres dans la base de données
     */
    protected void insertForBook() {
        try {
            for (Product p : this.getProductList()) {
                Utils.fixSpeechComa(p);
                this.insertBook(p);
                this.getProductJListModel().addElement(p.getAsin() + " - " +
                    p.getTitle() + " - " + p.getCategory());
            }
            this.getOracleConnection().commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Permet l'insertion des films dans la base de données
     */
    protected void insertForMovie() {
        try {
            for (Product p : this.getProductList()) {
                Utils.fixSpeechComa(p);
                Utils.fixSubtitleComa(p);
                this.insertMovie(p);
                this.getProductJListModel().addElement(p.getAsin() + " - " +
                    p.getTitle() + " - " + p.getCategory());
            }
            this.getOracleConnection().commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Permet l'insertion des films dans la base de données
     */
    protected void insertForMusic() {
        try {
            for (Product p : this.getProductList()) {
                this.insertMusic(p);
                this.getProductJListModel().addElement(p.getAsin()+ " - " +
                    p.getTitle() + " - " + p.getCategory());
            }
            this.getOracleConnection().commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Permet l'insertion des médias dans la base de données
     */
    protected void insertForAll() {
        try {
            for (Product p : this.getProductList()) {
                if (p.getCategory().compareTo("music") == 0)
                    this.insertMusic(p);
                else {
                    Utils.fixSpeechComa(p);
                    if (p.getCategory().compareTo("book") == 0)
                        this.insertBook(p);
                    else if (p.getCategory().compareTo("movie") == 0) {
                        Utils.fixSubtitleComa(p);
                        this.insertMovie(p);
                    }
                }
                this.getProductJListModel().addElement(p.getAsin() + " - " +
                    p.getTitle() + " - " + p.getCategory());
            }
            this.getOracleConnection().commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Permet l'insertion des produits français dans la base de données
     */
    protected void insertForDbBel() {
        if (this.getCategorieCheckBox().isSelected()) {
            if (this.getCategorieComboBox().getSelectedItem().toString().compareTo("book") == 0)
                this.insertForBook();
            else if (this.getCategorieComboBox().getSelectedItem().toString().compareTo("movie") == 0)
                this.insertForMovie();
            else if (this.getCategorieComboBox().getSelectedItem().toString().compareTo("music") == 0)
                this.insertForMusic();
        } else
            this.insertForAll();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        artisteCheckBox = new javax.swing.JCheckBox();
        artisteTextField = new javax.swing.JTextField();
        prixCheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        categorieCheckBox = new javax.swing.JCheckBox();
        categorieComboBox = new javax.swing.JComboBox();
        langueCheckBox = new javax.swing.JCheckBox();
        langueTextField = new javax.swing.JTextField();
        critereRadioButton = new javax.swing.JRadioButton();
        prixMinSpinner = new javax.swing.JSpinner();
        prixMaxSpinner = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        asinRadioButton = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        asinTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productJList = new javax.swing.JList();
        productProgressBar = new javax.swing.JProgressBar();
        jPanel5 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();

        buttonGroup1.add(this.critereRadioButton);
        buttonGroup1.add(this.asinRadioButton);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("InpresZone");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        artisteCheckBox.setText("Artiste :");
        artisteCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                artisteCheckBoxActionPerformed(evt);
            }
        });

        prixCheckBox.setText("Prix :");
        prixCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prixCheckBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Entre");

        jLabel2.setText("et");

        categorieCheckBox.setText("Catégorie :");
        categorieCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorieCheckBoxActionPerformed(evt);
            }
        });

        categorieComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "book", "movie", "music" }));
        categorieComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorieComboBoxActionPerformed(evt);
            }
        });

        langueCheckBox.setText("Langue :");
        langueCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                langueCheckBoxActionPerformed(evt);
            }
        });

        critereRadioButton.setText("Recherche par critère :");

        prixMinSpinner.setModel(new SpinnerNumberModel(0, 0, 999, 0.5f));

        prixMaxSpinner.setModel(new SpinnerNumberModel(0, 0, 999, 0.5f));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(artisteCheckBox)
                            .addComponent(prixCheckBox)
                            .addComponent(categorieCheckBox)
                            .addComponent(langueCheckBox))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(langueTextField)
                            .addComponent(categorieComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(artisteTextField)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(prixMinSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(prixMaxSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(critereRadioButton))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(critereRadioButton)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(artisteCheckBox)
                    .addComponent(artisteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prixCheckBox)
                    .addComponent(jLabel1)
                    .addComponent(prixMinSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(prixMaxSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categorieCheckBox)
                    .addComponent(categorieComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(langueCheckBox)
                    .addComponent(langueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        asinRadioButton.setText("Recherche par ASIN :");

        jLabel3.setText("ASIN :");

        asinTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                asinTextFieldMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(asinRadioButton)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(asinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(asinRadioButton)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(asinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        productJList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(productJList);

        productProgressBar.setMinimum(0);
        productProgressBar.setMaximum(100);
        productProgressBar.setValue(0);
        productProgressBar.setVisible(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(productProgressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        jPanel5.add(okButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        new ActionThread(this).start();
    }//GEN-LAST:event_okButtonActionPerformed

    private void artisteCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_artisteCheckBoxActionPerformed
        if (!this.getCritereRadioButton().isSelected())
            this.getCritereRadioButton().setSelected(true);
    }//GEN-LAST:event_artisteCheckBoxActionPerformed

    private void prixCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prixCheckBoxActionPerformed
        this.artisteCheckBoxActionPerformed(evt);
    }//GEN-LAST:event_prixCheckBoxActionPerformed

    private void categorieCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorieCheckBoxActionPerformed
        this.artisteCheckBoxActionPerformed(evt);
    }//GEN-LAST:event_categorieCheckBoxActionPerformed

    private void langueCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_langueCheckBoxActionPerformed
        this.artisteCheckBoxActionPerformed(evt);
    }//GEN-LAST:event_langueCheckBoxActionPerformed

    private void asinTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_asinTextFieldMouseClicked
        if (!this.getAsinRadioButton().isSelected())
            this.getAsinRadioButton().setSelected(true);
    }//GEN-LAST:event_asinTextFieldMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.closeOracleDB();
    }//GEN-LAST:event_formWindowClosing

    private void categorieComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorieComboBoxActionPerformed
        if (this.getCategorieComboBox().getSelectedItem().toString().compareTo("music") == 0)
            this.getLangueCheckBox().setSelected(false);
        else
            this.getLangueCheckBox().setSelected(true);
    }//GEN-LAST:event_categorieComboBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InpresZone.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new InpresZone().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox artisteCheckBox;
    private javax.swing.JTextField artisteTextField;
    private javax.swing.JRadioButton asinRadioButton;
    private javax.swing.JTextField asinTextField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox categorieCheckBox;
    private javax.swing.JComboBox categorieComboBox;
    private javax.swing.JRadioButton critereRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox langueCheckBox;
    private javax.swing.JTextField langueTextField;
    private javax.swing.JButton okButton;
    private javax.swing.JCheckBox prixCheckBox;
    private javax.swing.JSpinner prixMaxSpinner;
    private javax.swing.JSpinner prixMinSpinner;
    private javax.swing.JList productJList;
    private javax.swing.JProgressBar productProgressBar;
    // End of variables declaration//GEN-END:variables
}