package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Games;
import model.Users;
import java.awt.event.ActionListener;
import controller.GameDAO;
import controller.TransactionsDAO;


public class GameListView extends JFrame {
    private Users currentUser;
    private JList<Games> gameList;
    private JFrame frame;
    private JButton transactionsButton;
    private JPanel gamePanel;
    private GameDAO gameDAO;

    public GameListView() {
        frame = new JFrame("Game List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        transactionsButton = new JButton("Transactions");
        transactionsButton.addActionListener(e -> {
            TransactionsView transactionsView = new TransactionsView(currentUser);
            transactionsView.setVisible(true);
        });
        gamePanel = new JPanel(new GridLayout(0, 2)); // Grid layout for games

        frame.add(transactionsButton, BorderLayout.NORTH);
        frame.add(gamePanel, BorderLayout.CENTER);

        gameDAO = new GameDAO();
        setGames(gameDAO.getAllGames());

    }

    public void setGames(List<Games> games) {
        for (Games game : games) {
            JPanel panel = new JPanel();
            panel.add(new JTextField(game.getName()));
            panel.add(new JTextField(game.getGenre()));
            panel.add(new JTextField(String.valueOf(game.getPrice())));
            // Add image here
            JButton buyButton = new JButton("Buy Game");
            buyButton.addActionListener(e -> {
                // Handle buy game action
                Games selectedGame = gameList.getSelectedValue();

                if (selectedGame != null) {
                    // Create a new transaction
                    TransactionsDAO transactionsDAO = new TransactionsDAO();
                    if (transactionsDAO != null) {
                        boolean success = transactionsDAO.createTransaction(currentUser.getId(), selectedGame.getId());
    
                        if (success) {
                            JOptionPane.showMessageDialog(null, "Purchase successful.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Purchase failed.");
                        }
                    }
                }
            });
            panel.add(buyButton);
            gamePanel.add(panel);
        }
        frame.revalidate();
    }

    public void setTransactionsButtonListener(ActionListener listener) {
        transactionsButton.addActionListener(listener);
    }

    public void show() {
        frame.setVisible(true);
    }
}