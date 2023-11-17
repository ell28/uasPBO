package view;
import model.Users;
import model.Transactions;
import javax.swing.*;
import java.util.List;
import controller.TransactionsDAO; 

public class TransactionsView extends JFrame {
    private Users currentUser;
    private JTable transactionsTable;

    public TransactionsView(Users currentUser) {
        this.currentUser = currentUser;

        setTitle("Transactions");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TransactionsDAO transactionsDAO = new TransactionsDAO();
        List<Transactions> transactions = transactionsDAO.getTransactionsByUser(currentUser.getId());

        // Convert the list of transactions to a 2D array for the JTable.
        Object[][] tableData = new Object[transactions.size()][];
        for (int i = 0; i < transactions.size(); i++) {
            Transactions transaction = transactions.get(i);
            tableData[i] = new Object[] { transaction.getId(), transaction.getUserId(), currentUser.getName(), transaction.getGameId()};
        }

        // Create the JTable and add it to the frame.
        transactionsTable = new JTable(tableData, new Object[] { "ID", "User ID", "Game ID" });
        add(new JScrollPane(transactionsTable));

        setVisible(true);
    }
}