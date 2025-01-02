package ui;

// Main file that is run to launch the Hairstyle Log application
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("\nWelcome to your personal Hairstyle Log!");

        HairstyleLogApp hairstyleLogApp = new HairstyleLogApp();

        // Start from the MainMenuPanel instead of directly opening WishListPanel
        new MainMenuPanel(hairstyleLogApp);
    }
}


