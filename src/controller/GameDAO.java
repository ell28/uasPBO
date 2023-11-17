package controller;

import model.Games;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {
    private Connection connection;

    public GameDAO() {
        this.connection = Connector.getInstance().getConnection();
    }

    public List<Games> getAllGames() {
        List<Games> games = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM games");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                int price = resultSet.getInt("price");
                games.add(new Games(id, name, genre, price));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return games;
    }
}