package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.WishList;
import model.Hairstyle;

/*
 * The panel in which the WishList is rendered.
 */
public class WishListPanel extends JFrame implements ActionListener {
    private WishList wishlist;
    private HairstyleLogApp hairstyleLogApp;

    // MODIFIES: this
    // EFFECTS: initializes the panel with given wishlist and hairstyleLogApp,
    //          sets up the frame, and adds necessary labels and buttons
    public WishListPanel(WishList wishlist, HairstyleLogApp hairstyleLogApp)  {
        super("Wishlist Menu");
        this.wishlist = wishlist;
        this.hairstyleLogApp = hairstyleLogApp;

        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(890, 390));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());

        // Adding visual component (image)
        JLabel imageLabel = createImageLabel("data/WishlistWelcome.png");
        add(imageLabel, BorderLayout.NORTH);

        createAddButton();
        createRemoveButton();  
        createEditCostButton(); 
        createSaveButton(); 
        createLoadButton(); 
        createViewButton();
        createBackButton(); 

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setFocusable(true);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds "Add hairstyle" button
    public void createAddButton() {
        JButton addButton = new JButton("<html>Add<br>hairstyle<html>");
        addButton.setActionCommand("Add");
        addButton.addActionListener(this);
        addButton.setBackground(new Color(183, 138, 97));
        addButton.setForeground(Color.WHITE);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.setPreferredSize(new Dimension(117, 40));

        add(addButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds "Remove hairstyle" button
    public void createRemoveButton() {
        JButton removeButton = new JButton("<html>Remove<br>hairstyle<html>");
        removeButton.setActionCommand("Remove");
        removeButton.addActionListener(this);
        removeButton.setBackground(new Color(183, 138, 97));
        removeButton.setForeground(Color.WHITE);
        removeButton.setOpaque(true);
        removeButton.setBorderPainted(false);
        removeButton.setPreferredSize(new Dimension(116, 40));

        add(removeButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds "Edit hairstyle cost" button
    public void createEditCostButton() {
        JButton editCostButton = new JButton("<html>Edit cost<br>of hairstyle<html>");
        editCostButton.setActionCommand("EditCost");
        editCostButton.addActionListener(this);
        editCostButton.setBackground(new Color(183, 138, 97));
        editCostButton.setForeground(Color.WHITE);
        editCostButton.setOpaque(true);
        editCostButton.setBorderPainted(false);
        editCostButton.setPreferredSize(new Dimension(116, 40));

        add(editCostButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds "Save wishlist" button
    public void createSaveButton() {
        JButton saveButton = new JButton("<html>Save<br>wishlist<html>");
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(this);
        saveButton.setBackground(new Color(183, 138, 97));
        saveButton.setForeground(Color.WHITE);
        saveButton.setOpaque(true);
        saveButton.setBorderPainted(false);
        saveButton.setPreferredSize(new Dimension(116, 40));

        add(saveButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds "Load wishlist" button
    public void createLoadButton() {
        JButton loadButton = new JButton("<html>Load<br>wishlist<html>");
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(this);
        loadButton.setBackground(new Color(183, 138, 97));
        loadButton.setForeground(Color.WHITE);
        loadButton.setOpaque(true);
        loadButton.setBorderPainted(false);
        loadButton.setPreferredSize(new Dimension(116, 40));

        add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds "View WishList" button
    public void createViewButton() {
        JButton viewButton = new JButton("<html>View<br>wishlist<html>");
        viewButton.setActionCommand("View");
        viewButton.addActionListener(this);
        viewButton.setBackground(new Color(183, 138, 97));
        viewButton.setForeground(Color.WHITE);
        viewButton.setOpaque(true);
        viewButton.setBorderPainted(false);
        viewButton.setPreferredSize(new Dimension(116, 40));

        add(viewButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds "Back to Main Menu" button
    public void createBackButton() {
        JButton backButton = new JButton("<html>Back to<br>Main Menu<html>");
        backButton.setActionCommand("Back");
        backButton.addActionListener(this);
        backButton.setBackground(new Color(183, 138, 97));
        backButton.setForeground(Color.WHITE);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(117, 40));

        add(backButton);
    }


    // REQUIRES: imagePath is the path to an existing image file
    // MODIFIES: this
    // EFFECTS: creates and returns a JLabel displaying specified image
    private JLabel createImageLabel(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return imageLabel;
    }

    // MODIFIES: this
    // EFFECTS: determines action to be performed when each button is clicked
    @SuppressWarnings("methodlength")
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Add":
                handleAdd();
                break;
            case "Remove":
                handleRemove();
                break;
            case "EditCost":
                handleEditCost();
                break;
            case "Save":
                handleSave();
                break;
            case "Load":
                handleLoad();
                break;
            case "View":
                handleView();
                break;
            case "Back":
                handleBack();
                break;
            default:
                System.out.println("Unknown action command: " + command);
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user for a hairstyle name and cost, 
    // and adds a new hairstyle to the wishlist
    private void handleAdd() {
        String name = JOptionPane.showInputDialog(this, "Enter hairstyle name:");
        if (name != null) {
            String costStr = JOptionPane.showInputDialog(this, "Enter hairstyle cost:");
            try {
                double cost = Double.parseDouble(costStr);
                wishlist.addHairstyle(new Hairstyle(name, cost));
                JOptionPane.showMessageDialog(this, "Hairstyle added: " + name + ", $" + cost);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid cost. Please enter a valid number.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user for a hairstyle name and removes hairstyle from the wishlist
    private void handleRemove() {
        String name = JOptionPane.showInputDialog(this, "Enter hairstyle name to remove:");
        if (name != null) {
            boolean removed = wishlist.removeHairstyle(name);
            if (removed) {
                JOptionPane.showMessageDialog(this, "Hairstyle removed: " + name);
            } else {
                JOptionPane.showMessageDialog(this, "Hairstyle not found in wishlist.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user for a hairstyle name and new cost,
    // and replaces the cost of the named hairstyle with the new cost
    private void handleEditCost() {
        String name = JOptionPane.showInputDialog(this, "Enter hairstyle name to edit:");
        if (name != null) {
            String costStr = JOptionPane.showInputDialog(this, "Enter new hairstyle cost:");
            try {
                double newCost = Double.parseDouble(costStr);
                wishlist.editHairstyleCost(name, newCost);
                JOptionPane.showMessageDialog(this, "Cost updated for " + name + ": $" + newCost);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid cost. Please enter a valid number.");
            }
        }
    }

    // EFFECTS: saves the wishlist to file
    private void handleSave() {
        try {
            hairstyleLogApp.getJsonWriter1().openWishList();
            hairstyleLogApp.getJsonWriter1().writeWishList(wishlist);
            hairstyleLogApp.getJsonWriter1().closeWishList();
            JOptionPane.showMessageDialog(this, "Wishlist saved successfully to ./data/wishlist.json!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to save wishlist: " 
                    + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    } 

    // MODIFIES: this, wishlist
    // EFFECTS: loads the wishlist from file
    private void handleLoad() {
        try {
            wishlist = hairstyleLogApp.getJsonReader1().readWishList();
            JOptionPane.showMessageDialog(this, "Wishlist loaded successfully from ./data/wishlist.json!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load wishlist: " 
                    + e.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    } 

    // EFFECTS: displays the name and cost of each hairstyle on wishlist
    private void handleView() {
        StringBuilder message = new StringBuilder("Hairstyles in your wishlist:\n");

        if (wishlist.getAllHairstyles().isEmpty()) {
            message.append("No hairstyles in the wishlist.");
        } else {
            for (Hairstyle h : wishlist.getAllHairstyles()) {
                message.append(h.getName()).append(" - $").append(h.getCost()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(this, message.toString());
    }

    // MODIFIES: this
    // EFFECTS: goes back to main menu
    private void handleBack() {
        dispose();
    }

    // EFFECTS: Creates a sample wishlist
    public static void main(String[] args) {
        WishList sampleWishList = new WishList();
        HairstyleLogApp sampleApp = new HairstyleLogApp(); 
        new WishListPanel(sampleWishList, sampleApp);
    }
}