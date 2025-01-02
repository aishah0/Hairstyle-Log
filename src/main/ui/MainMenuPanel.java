package ui;

import javax.swing.*;

import model.WishList;
import model.DoneList;
import model.EventLog;
import model.Event;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * The panel in which the Main Menu is rendered.
 */
public class MainMenuPanel extends JFrame implements ActionListener, LogPrinter {
    private JButton wishListButton;
    private JButton completedListButton;
    private HairstyleLogApp hairstyleLogApp;
    private WishList wishlist;
    private DoneList donelist;

    // MODIFIES: this
    // EFFECTS: initializes and displays the main menu panel with buttons for
    // navigation.
    @SuppressWarnings("methodlength")
    public MainMenuPanel(HairstyleLogApp app) {
        super("Hairstyle Log - Main Menu");
        this.hairstyleLogApp = app;
        this.wishlist = app.getWishList();
        this.donelist = app.getDoneList();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 120));
        setLayout(new FlowLayout());

        JLabel welcomeLabel = new JLabel("Welcome to your personal Hairstyle Log!"
                + "Choose a Hairstyle List to view or edit:");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(welcomeLabel);

        wishListButton = new JButton("Hairstyle WishList");
        completedListButton = new JButton("List of Completed Hairstyles");

        wishListButton.setActionCommand("wishList");
        completedListButton.setActionCommand("completedList");

        wishListButton.addActionListener(this);
        completedListButton.addActionListener(this);

        add(wishListButton);
        add(completedListButton);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleApplicationExit();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: EventLog
    // EFFECTS: Prints all logged events to the console.
    private void handleApplicationExit() {
        EventLog log = EventLog.getInstance();
        log.logEvent(new Event("Application closed."));

        System.out.println("Event Log:");
        printLog(log); 

        System.exit(0);
    }

    // EFFECTS: Prints each event in the provided EventLog to the console.
    @Override
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next);
        }
    }

    // EFFECTS: handles button clicks and navigates to the appropriate panel
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("wishList".equals(command)) {
            new WishListPanel(wishlist, hairstyleLogApp);
        } else if ("completedList".equals(command)) {
            new CompletedListPanel(donelist, hairstyleLogApp);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes and displays the main menu panel
    public static void main(String[] args) {
        HairstyleLogApp app = new HairstyleLogApp();
        new MainMenuPanel(app);
    }
}
