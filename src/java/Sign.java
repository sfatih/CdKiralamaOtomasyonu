
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ManagedBean ( name = "Sign" )
@RequestScoped
public class Sign {
    private String girilen_ad,girilen_sifre,admin,user,bilinmeyen,username,name,surname,password,sex,email;
    Connection con;
    DataSource dataSource;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public String getEmail() {
        return email;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setBilinmeyen(String bilinmeyen) {
        this.bilinmeyen = bilinmeyen;
    }

    public String getAdmin() {
        return admin;
    }

    public String getUser() {
        return user;
    }

    public String getBilinmeyen() {
        return bilinmeyen;
    }

    public String getGirilen_ad() {
        return girilen_ad;
    }

    public String getGirilen_sifre() {
        return girilen_sifre;
    }

    public void setGirilen_ad(String girilen_ad) {
        this.girilen_ad = girilen_ad;
    }

    public void setGirilen_sifre(String girilen_sifre) {
        this.girilen_sifre = girilen_sifre;
    }

    public Sign() throws SQLException, NamingException{
        String url="jdbc:derby://localhost/cdkitap";
        String user = "admin";
        String password = "password";
        try{
            con = DriverManager.getConnection(url,user,password);
            Context ctx = new InitialContext();
            dataSource = (DataSource)ctx.lookup("jdbc/cdkitap");
        } catch (NamingException e )
        {
            e.printStackTrace();
        }
    }
        
    public void Sign() throws SQLException
    {
        if ( dataSource == null )
            throw new SQLException ( "Unable to obtain DataSource" );
        
        Connection connection = dataSource.getConnection();
        
        if ( connection == null )
            throw new SQLException ( "Unable to connect to DataSource" );
        
//        try
//        {
//            PreparedStatement addEntry =
//                    connection.prepareStatement(" INSERT INTO USERS " + "(USER_NAME,USER_PASS)" );
//            addEntry.setString( 1,getUser);
//        }
    }
    
    public String sign_up() throws SQLException
    {
        
        if ( dataSource == null )
            throw new SQLException( "Unable to obtain DataSource" );

 
        Connection connection = dataSource.getConnection();

 
        if ( con == null )
            throw new SQLException( "Unable to connect to DataSource" );

        try
        {
 
            PreparedStatement addEntry =
            connection.prepareStatement( "INSERT INTO USERS " +
            "(USER_NAME,USER_PASS,NAME,SURNAME,EMAIL,SEX)" +
            "VALUES ( ?, ?, ?, ?, ?, ?)" );

            addEntry.setString( 1, getUsername());
            addEntry.setString( 2, getPassword());
            addEntry.setString( 3, getName());
            addEntry.setString( 4, getSurname());
            addEntry.setString( 5, getEmail());
            addEntry.setString( 6, getSex());
            addEntry.executeUpdate(); 
        return "index"; 
        } 
        finally
        {
            connection.close(); 
        } 
    }
    
    public String login() throws SQLException
    {
        Statement sta = con.createStatement();
        ResultSet res = sta.executeQuery("select ADMIN_NAME,ADMIN_PASS,USER_NAME,USER_PASS from ADMIN,USERS");
        while ( res.next())
        {
            if(girilen_ad.equals(res.getString("ADMIN_NAME")) && girilen_sifre.equals(res.getString("ADMIN_PASS")) && admin.equals("Admin"))
            {
                return "add.xhtml";
            }
            else if(girilen_ad.equals(res.getString("USER_NAME")) && girilen_sifre.equals(res.getString("USER_PASS")) && admin.equals("User"))
            {
                return "search.xhtml";
            }
        }
        return "index.xhtml";
    }
    
}
