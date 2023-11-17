package controller;

import model.Transactions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransactionsDAO {
    private Connection connection;

    public TransactionsDAO() {
        this.connection = Connector.getInstance().getConnection();
    }

    public List<Transactions> getTransactionsByUser(int userId) {
        List<Transactions> transactions = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM transactions WHERE user_id = " + userId);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int gameId = resultSet.getInt("game_id");
                transactions.add(new Transactions(id, userId, gameId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
    public boolean createTransaction(int userId, int gameId) {
        try {
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate("INSERT INTO transactions (user_id, game_id) VALUES (" + userId + ", " + gameId + ")");
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
