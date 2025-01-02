package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.DoneList;
import model.Hairstyle;

/*
 * The panel in which the CompletedList is rendered.
 */
public class CompletedListPanel extends JFrame implements ActionListener {
    private DoneList donelist;
    private HairstyleLogApp hairstyleLogApp;

    // MODIFIES: this
    // EFFECTS: initializes the panel with given donelist and hairstyleLogApp,
    //          sets up the frame, and adds necessary labels and buttons
    public CompletedListPanel(DoneList donelist, HairstyleLogApp hairstyleLogApp)  {
        super("Completed List Menu");
        this.donelist = donelist;
        this.hairstyleLogApp = hairstyleLogApp;

        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(890, 390));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());

        // Adding visual component (image)
        JLabel imageLabel = createImageLabel("data/Donelist Welcome.png");
        add(imageLabel, BorderLayout.NORTH);

        createAddButton();
        createSaveButton(); 
        createLoadButton(); 
        createViewButton(); 
        createBackButton(); 

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds "Add hairstyle to list" button
    public void createAddButton() {
        JButton addButton = new JButton("<html>Add hairstyle<br>to list<html>");
        addButton.setActionCommand("Add");
        addButton.addActionListener(this);
        addButton.setBackground(new Color(61, 150, 153));
        addButton.setForeground(Color.WHITE);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.setPreferredSize(new Dimension(165, 40));

        add(addButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds "Save list of completed hairstyles" button
    public void createSaveButton() {
        JButton saveButton = new JButton("<html>Save list of<br>completed styles<html>");
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(this);
        saveButton.setBackground(new Color(61, 150, 153));
        saveButton.setForeground(Color.WHITE);
        saveButton.setOpaque(true);
        saveButton.setBorderPainted(false);
        saveButton.setPreferredSize(new Dimension(165, 40));

        add(saveButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds "Load list of completed hairstyles" button
    public void createLoadButton() {
        JButton loadButton = new JButton("<html>Load list of<br>completed styles<html>");
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(this);
        loadButton.setBackground(new Color(61, 150, 153));
        loadButton.setForeground(Color.WHITE);
        loadButton.setOpaque(true);
        loadButton.setBorderPainted(false);
        loadButton.setPreferredSize(new Dimension(165, 40));

        add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds "View list of completed hairstyles" button
    public void createViewButton() {
        JButton viewButton = new JButton("<html>View list of<br>completed styles<html>");
        viewButton.setActionCommand("View");
        viewButton.addActionListener(this);
        viewButton.setBackground(new Color(61, 150, 153));
        viewButton.setForeground(Color.WHITE);
        viewButton.setOpaque(true);
        viewButton.setBorderPainted(false);
        viewButton.setPreferredSize(new Dimension(165, 40));

        add(viewButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds "Back to Main Menu" button
    public void createBackButton() {
        JButton backButton = new JButton("<html>Back to<br>Main Menu<html>");
        backButton.setActionCommand("Back");
        backButton.addActionListener(this);
        backButton.setBackground(new Color(61, 150, 153));
        backButton.setForeground(Color.WHITE);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(165, 40));

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
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Add":
                handleAdd();
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
    // and adds a new hairstyle to the completed list
    private void handleAdd() {
        String name = JOptionPane.showInputDialog(this, "Enter hairstyle name:");
        if (name != null) {
            String costStr = JOptionPane.showInputDialog(this, "Enter hairstyle cost:");
            try {
                double cost = Double.parseDouble(costStr);
                donelist.addDoneHairstyle(new Hairstyle(name, cost));
                JOptionPane.showMessageDialog(this, "Hairstyle added: " + name + ", $" + cost);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid cost. Please enter a valid number.");
            }
        }
    }

    // EFFECTS: saves the donelist to file
    private void handleSave() {
        try {
            hairstyleLogApp.getJsonWriter1().openDoneList();
            hairstyleLogApp.getJsonWriter1().writeDoneList(donelist);
            hairstyleLogApp.getJsonWriter1().closeDoneList();
            JOptionPane.showMessageDialog(this, "Completed list saved successfully to ./data/donelist.json!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to save completed list: " 
                    + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    } 


    // MODIFIES: this, donelist
    // EFFECTS: loads the wishlist from file
    private void handleLoad() {
        try {
            donelist = hairstyleLogApp.getJsonReader1().readDoneList();
            JOptionPane.showMessageDialog(this, "Completed list loaded successfully from ./data/donelist.json!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load wishlist: " 
                    + e.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    } 


    // EFFECTS: displays the name and cost of each hairstyle on donelist
    private void handleView() {
        StringBuilder message = new StringBuilder("Hairstyles in your completed list:\n");

        if (donelist.getAllDoneHairstyles().isEmpty()) {
            message.append("No hairstyles in the list of completed hairstyles.");
        } else {
            for (Hairstyle h : donelist.getAllDoneHairstyles()) {
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

    // EFFECTS: Creates a sample donelist
    public static void main(String[] args) {
        DoneList sampleDoneList = new DoneList();
        HairstyleLogApp sampleApp = new HairstyleLogApp(); 
        new CompletedListPanel(sampleDoneList, sampleApp);
    }


}


