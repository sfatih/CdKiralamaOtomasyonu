package root;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@ManagedBean ( name = "BookBean" )
@RequestScoped
public class book_info {
    private String isbn,book_name,author,year_pub,publisher,pages,number;
    private String cd_isbn,cd_name,artist,cd_year_pub,cd_publisher,cd_number;
    private String searching_name,searching_author;
    CachedRowSet rowSet=null;
    
    DataSource dataSource;
    public book_info(){
        try{
            Context ctx = new InitialContext();
            dataSource = (DataSource)ctx.lookup("jdbc/cdkitap");
        }catch(NamingException e){
            e.printStackTrace();
        }
    }

    public void setSearching_name(String searching_name) {
        this.searching_name = searching_name;
    }

    public String getSearching_name() {
        return searching_name;
    }

    public void setSearching_author(String searching_author) {
        this.searching_author = searching_author;
    }

    public String getSearching_author() {
        return searching_author;
    }

    public void setCd_isbn(String cd_isbn) {
        this.cd_isbn = cd_isbn;
    }

    public void setCd_name(String cd_name) {
        this.cd_name = cd_name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setCd_year_pub(String cd_year_pub) {
        this.cd_year_pub = cd_year_pub;
    }

    public void setCd_publisher(String cd_publisher) {
        this.cd_publisher = cd_publisher;
    }

    public void setCd_number(String cd_number) {
        this.cd_number = cd_number;
    }

    public String getCd_isbn() {
        return cd_isbn;
    }

    public String getCd_name() {
        return cd_name;
    }

    public String getArtist() {
        return artist;
    }

    public String getCd_year_pub() {
        return cd_year_pub;
    }

    public String getCd_publisher() {
        return cd_publisher;
    }

    public String getCd_number() {
        return cd_number;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear_pub() {
        return year_pub;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPages() {
        return pages;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setYear_pub(String year_pub) {
        this.year_pub = year_pub;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
    public String insert_book() throws SQLException
    {

        if ( dataSource == null )
            throw new SQLException( "Unable to obtain DataSource" );

 
        Connection connection = dataSource.getConnection();

 
        if ( connection == null )
            throw new SQLException( "Unable to connect to DataSource" );

        try
        {
 
            PreparedStatement addEntry =
            connection.prepareStatement( "INSERT INTO BOOKS " +
            "(isbn,book_name,author,year_pub,publisher,pages)" +
            "VALUES ( ?, ?, ?, ?, ?, ?)" );

            addEntry.setInt( 1, Integer.parseInt(getIsbn()) );
            addEntry.setString( 2, getBook_name());
            addEntry.setString( 3, getAuthor());
            addEntry.setInt( 4, Integer.parseInt(getYear_pub()) );
            addEntry.setString( 5, getPublisher());
            addEntry.setInt(6,Integer.parseInt(getPages()));
 
            addEntry.executeUpdate(); 
            return "admin_page"; 
        }finally
            {
                connection.close(); 
            } 
    } 
    
    public String insert_cd() throws SQLException
    {

        if ( dataSource == null )
            throw new SQLException( "Unable to obtain DataSource" );

 
        Connection connection = dataSource.getConnection();

 
        if ( connection == null )
            throw new SQLException( "Unable to connect to DataSource" );

        try
        {
 
            PreparedStatement addEntry =
            connection.prepareStatement( "INSERT INTO CDS " +
            "(cd_isbn,cd_name,artist,cd_year_pub,cd_publisher)" +
            "VALUES ( ?, ?, ?, ?, ?)" );

            addEntry.setInt( 1, Integer.parseInt(getCd_isbn()) );
            addEntry.setString( 2, getCd_name());
            addEntry.setString( 3, getArtist());
            addEntry.setInt( 4, Integer.parseInt(getCd_year_pub()) );
            addEntry.setString( 5, getCd_publisher());
 
            addEntry.executeUpdate(); 
            return "admin_page"; 
        }finally
            {
                connection.close(); 
            } 
    } 
    
     public ResultSet search() throws SQLException
    {
    // check whether dataSource was injected by the server
        if ( dataSource == null )
            throw new SQLException( "Unable to obtain DataSource" );

    // obtain a connection from the connection pool
        Connection connection = dataSource.getConnection();

        // check whether connection was successful
        if ( connection == null )
            throw new SQLException( "Unable to connect to DataSource" );

        try
        {
        // create a PreparedStatement to insert a new address book entry
            PreparedStatement ps =
            connection.prepareStatement( "select ISBN,BOOK_NAME ,AUTHOR,YEAR_PUB, PUBLISHER from BOOKS " +
            "where BOOKS.BOOK_NAME=?" );
            ps.setString( 1, getSearching_name());
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate( ps.executeQuery() );
            return rowSet;
        } // end try
    finally
    {
        connection.close(); // return this connection to pool
    } // end finally
}
     public ResultSet search_author() throws SQLException
    {
    // check whether dataSource was injected by the server
        if ( dataSource == null )
            throw new SQLException( "Unable to obtain DataSource" );

    // obtain a connection from the connection pool
        Connection connection = dataSource.getConnection();

        // check whether connection was successful
        if ( connection == null )
            throw new SQLException( "Unable to connect to DataSource" );

        try
        {
        // create a PreparedStatement to insert a new address book entry
            PreparedStatement ps =
            connection.prepareStatement( "select AUTHOR from BOOKS " +
            "where BOOKS.AUTHOR=?" );
            ps.setString( 1, getSearching_author());
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate( ps.executeQuery() );
            return rowSet;
        } // end try
    finally
    {
        connection.close(); // return this connection to pool
    } // end finally
 } 
}

