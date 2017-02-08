package control;


import entity.articles.Electronics;
import entity.users.PrivateUser;
import entity.users.RegisteredUser;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PrivateDBcontroller extends DatabaseController
{
    private static PrivateDBcontroller ourInstance = new PrivateDBcontroller();
    public static PrivateDBcontroller getOurInstance(){return ourInstance;}
    private PrivateDBcontroller(){
        super();
    }

    public void addUser(PrivateUser newUser) throws Exception
    {

        super.addRegisteredUser(newUser);

        Connection connection2 = null;

        PreparedStatement statement2 = null;

        final String insert2 = "INSERT INTO USERS.Privato(NOME, COGNOME, EMAIL) values (?,?,?)";

        try
        {
            connection2 = provider.getConnection();

            statement2 = connection2.prepareStatement(insert2);
            statement2.setString(1, newUser.getName());
            statement2.setString(2, newUser.getSurname());
            statement2.setString(3, newUser.getEmail());

            statement2.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(statement2 != null)
            {
                statement2.close();
            }

            if(connection2  != null)
            {
                connection2.close();
            }
        }
    }


    public PrivateUser findUser(String email) throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        PrivateUser user = null;
        ResultSet result = null;
        final String query = "select * from USERS.Privato where EMAIL=?";
        //final String query = "select * from USERS.UtenteRegistrato";

        try{
            connection = provider.getConnection();

            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            result = statement.executeQuery();

            if (result.next()) {
/*                if (user == null) {*/
                user = UserFactory.getInstance().createUser();
                user.setEmail(result.getString("EMAIL"));
                user.setName(result.getString("NOME"));
                user.setType(1);
                System.out.println("nome ritrovato: " + user.getName());
/*                }*/
            } else {
                return null;
            }
        }finally{
            // release resources
            if(result != null){
                result.close();
            }
            // release resources
            if(statement != null){
                statement.close();
            }
            if(connection  != null){
                connection.close();
            }

        }
        System.out.println("nome utente: " + user.getName());
        return user;
    }



    public boolean removeMoney(String user, float newbalance)
    {
        String sql = "UPDATE USERS.UtenteRegistrato SET SALDO='"+ newbalance +"' WHERE EMAIL = '"+ user+"'";
        System.out.println(sql);
        try{
            Statement stmt = provider.getConnection().createStatement();
            System.out.println("successo");
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("fallimento");
            return false;
        }
        return true;
    }

    public boolean addAcquisto(String nome, String proprietario, String articolo) {
        String sql = "INSERT INTO ARTICLES.acquisti (UTENTE, ARTICOLO, PROPRIETARIO) VALUES ('" +
                nome + "', '" + articolo + "', '" +
                proprietario + "')";

        System.out.println(sql);
        try {
            Statement stmt = provider.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("errore nell'inserimento dell'acquisto");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
