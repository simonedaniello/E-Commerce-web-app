package control;
import entity.articles.Review;
import entity.articles.Article;
import entity.users.RegisteredUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ArticlesController extends DatabaseController{

    private static ArticlesController instance = new ArticlesController();


    private ArticlesController(){
    }

    public static ArticlesController getInstance(){
        return instance;
    }

    public void validate(String email_proprietario, String nome_articolo){
        Connection connection = null;
        PreparedStatement statement = null;

        final String query = "update ARTICLES.articolo set isvalid=TRUE where proprietario=? and nome=?";
        try {

            connection = provider.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email_proprietario);
            statement.setString(2, nome_articolo);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            // release resources
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection  != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Article> listAllArticles() throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        ArrayList<Article> articles = new ArrayList<Article>();
        final String query = "select * " +
                "from ARTICLES.articolo";
        try {
            connection = provider.getConnection();

            statement = connection.prepareStatement(query);
            result = statement.executeQuery();

            while (result.next()) {
                String nome = result.getString(1);
                //System.out.println(nome + "\n");
                String proprietario = result.getString(2);
                RegisteredUser ru = new RegisteredUser(proprietario);
                Float prezzo = Float.parseFloat(result.getString(3));
                //System.out.print(prezzo  + "\n");
                Integer quantità = Integer.parseInt(result.getString(4));
                //System.out.print(quantità + "\n");
                Boolean isvalid = result.getBoolean(5);
                //System.out.print(isvalid  + "\n");
                String immagine = result.getString(6);
                //System.out.println(immagine + "\n");

                Article article = new Article(nome, ru, prezzo, quantità, isvalid, immagine);
                articles.add(article);
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
        return articles;
    }

    public int sendReview(String text, String articlename, String username, int rating, String owner) throws ClassNotFoundException {
    /*invia la query al database e torna 1 in caso di successo, 0 o 2 altrimenti*/

        if(text.length() > 300)
            return 2;
        else {
            Review review;
            if (rating > 0)
                review = ArticleFactory.getInstance().getReview(rating, text.replace("\'", "\""), username, articlename, owner, false);
            else
                review = ArticleFactory.getInstance().getReview(0, text.replace("\'", "\""), username, articlename, owner, false);
            if (DatabaseController.getInstance().setReview(review)) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public int sendWarning(String text, String vendor, String username,String articlename) throws ClassNotFoundException {

    /*come sendReview ma invia una segnalazione e non una recensione*/
        System.out.println("sono qui ------------");
        Review review = ArticleFactory.getInstance().getReview(0, text.replace("\'", "\""), username, articlename, vendor, true);
        if(DatabaseController.getInstance().setReview(review))
            return 1;
        else
            return 0;
    }

}
