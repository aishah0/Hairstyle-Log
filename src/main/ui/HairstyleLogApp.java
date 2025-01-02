package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.DoneList;
import model.WishList;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Hairstyle;

// Hairstyle log application
public class HairstyleLogApp {
    private static final String JSON_STORE1 = "./data/wishlist.json";
    private static final String JSON_STORE2 = "./data/donelist.json";
    private Scanner input;
    private WishList wishlist;
    private DoneList donelist;
    private JsonReader jsonReader1;
    private JsonReader jsonReader2;
    private JsonWriter jsonWriter1;
    private JsonWriter jsonWriter2;
    //private WishList wishList;

    // EFFECTS: runs the hairstylelog application, initializing it with an empty
    //          wishlist and an empty donelist
    public HairstyleLogApp() {
        //super("Hairstyle Log");
        //wishList = new WishList();
        input = new Scanner(System.in);
        wishlist = new WishList();
        donelist = new DoneList();
        jsonReader1 = new JsonReader(JSON_STORE1);
        jsonWriter1 = new JsonWriter(JSON_STORE1);
        jsonReader2 = new JsonReader(JSON_STORE2);
        jsonWriter2 = new JsonWriter(JSON_STORE2);
    }

    // EFFECTS: displays the main menu and handles user input to 
    // navigate through the application
    @SuppressWarnings("methodlength")
    public void runHairstyleLog() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMainMenu();
            command = input.nextLine();

            switch (command.toLowerCase()) {
                case "w":
                    handleWishListMenu();
                    break;
                case "c":
                    handleDoneListMenu();
                    break;
                case "q":
                    keepGoing = false;
                    break;
                default:
                    System.out.println("\nInvalid option. Please select w, c, or q.\n");
            }
        }

        System.out.println("Goodbye!");
    }

    // EFFECTS: displays the main menu options for choosing between the 
    // wishlist and completed list
    private void displayMainMenu() {
        System.out.println("\nChoose a Hairstyle List to view or edit:\n"); 
        System.out.println("w - Hairstyle Wishlist"); 
        System.out.println("c - List of Completed Hairstyles");
        System.out.println("q - quit\n"); 
        System.out.println("Enter your choice: "); 
    }

    // MODIFIES: this
    // EFFECTS: processes user input for navigating through 
    // wishlist options
    @SuppressWarnings("methodlength")
    private void handleWishListMenu() {
        boolean inWishlistMenu = true;
        String choice = null;
        while (inWishlistMenu) {
            System.out.println("\nWishlist Options:\n\nlw - load wishlist from file");
            System.out.println("sw - save wishlist to file");
            System.out.println("a -> add a hairstyle to wishlist");
            System.out.println("c -> edit the cost of a hairstyle on your wishlist");
            System.out.println("r -> remove a hairstyle from wishlist"); 
            System.out.println("v -> view wishlist"); 
            System.out.println("b -> go back to main menu\n\nEnter your choice: ");
            choice = input.nextLine();
            if (choice.toLowerCase().equals("a")) {
                addHairstyleToWishlist();
            } else if (choice.toLowerCase().equals("c")) {
                editStyleCost();
            } else if (choice.toLowerCase().equals("r")) {
                removeHairstyleFromWishlist();
            } else if (choice.toLowerCase().equals("v")) {
                viewWishlist();
            } else if (choice.toLowerCase().equals("lw")) {
                loadWishList();
            } else if (choice.toLowerCase().equals("sw")) {
                saveWishList();
            } else if (choice.toLowerCase().equals("b")) {
                inWishlistMenu = false; // Exits the wishlist menu
            } else {
                System.out.println("\nInvalid option. Please select a, c, r, v, or b.\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input for navigating through donelist options
    @SuppressWarnings("methodlength")
    private void handleDoneListMenu() {
        boolean inWishlistMenu = true;
        String choice = null;

        while (inWishlistMenu) {
            System.out.println("\nCompleted List Options:");
            System.out.println("\nlc - load completed list from file");
            System.out.println("sc - save completed list to file");
            System.out.println("a -> add a hairstyle to completed list");
            System.out.println("v -> view completed list");
            System.out.println("b -> go back to main menu");
            System.out.println("\nEnter your choice: ");
            choice = input.nextLine();

            switch (choice.toLowerCase()) {
                case "a":
                    addHairstyleToDonelist();
                    break;
                case "v":
                    viewDonelist();
                    break;
                case "b":
                    inWishlistMenu = false;
                    break;
                case "lc":
                    loadDoneList();
                    break;
                case "sc":
                    saveDoneList();
                    break;
                default: 
                    System.out.println("\nInvalid option. Please select a, v, or b.\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user for a hairstyle name and cost, 
    // and adds a new hairstyle to the wishlist
    private void addHairstyleToWishlist() {
        System.out.print("Enter hairstyle name: ");
        String name = input.nextLine();
        System.out.print("Enter estimated cost of your hairstyle: ");
        double cost = input.nextDouble();
        input.nextLine();

        Hairstyle newHairstyle = new Hairstyle(name,cost);
        wishlist.addHairstyle(newHairstyle);
        System.out.println("\nHairstyle added to wishlist! " + "(" + name + ", $" + cost + ")\n");
    }

    // MODIFIES: this
    // EFFECTS: prompts the user for a hairstyle name and new cost,
    // and replaces the cost of the named hairstyle with the new cost
    private void editStyleCost() {
        System.out.print("Enter hairstyle name to edit cost:");
        String name = input.nextLine();

        System.out.print("Enter new estimated hairstyle cost:");
        String newcost = input.nextLine();
        double dblnewcost = Double.parseDouble(newcost);

        wishlist.editHairstyleCost(name, dblnewcost);
        System.out.print("\nHairstyle cost updated\n\n");
    }

    // MODIFIES: this
    // EFFECTS: prompts the user for a hairstyle name and removes hairstyle from the wishlist
    private void removeHairstyleFromWishlist() {
        System.out.println("Enter the name of the hairstyle you want to remove: ");
        String name = input.nextLine();
        boolean removed = wishlist.removeHairstyle(name);
        if (removed) {
            System.out.println("\nHairstyle removed from wishlist!\n");
        } else {
            System.out.println("\nHairstyle not found in wishlist.\n");
        }
    }

    // EFFECTS: displays the name and cost of each hairstyle on the wishlist
    private void viewWishlist() {
        System.out.println("\nWishlist:");
        if (wishlist.getAllHairstyles().isEmpty()) {
            System.out.println("\nWishlist is empty.\n");
        } else {
            for (Hairstyle h : wishlist.getAllHairstyles()) {
                System.out.println(h.getName() + ", $" + h.getCost());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user for a hairstyle name and cost, 
    // and adds a new hairstyle to the donelist
    private void addHairstyleToDonelist() {
        System.out.print("Enter hairstyle name: ");
        String name = input.nextLine();
        System.out.print("Enter the cost of your completed hairstyle: ");
        double cost = input.nextDouble();
        input.nextLine();

        Hairstyle newHairstyle = new Hairstyle(name,cost);
        donelist.addDoneHairstyle(newHairstyle);
        System.out.println("\nHairstyle added to your list of completed styles!"
                + "(" + name + ", $" + cost + ")\n");
    }

    // EFFECTS: displays the name and cost of each hairstyle on donelist
    private void viewDonelist() {
        System.out.println("Completed List:");
        if (donelist.getAllDoneHairstyles().isEmpty()) {
            System.out.println("Completed List is empty.");
        } else {
            for (Hairstyle h : donelist.getAllDoneHairstyles()) {
                System.out.println(h.getName() + ", $" + h.getCost());
            }
        }
    }

    public JsonReader getJsonReader1() {
        return jsonReader1;
    }

    public JsonWriter getJsonWriter1() {
        return jsonWriter1;
    }

    public JsonReader getJsonReader2() {
        return jsonReader2;
    }

    public JsonWriter getJsonWriter2() {
        return jsonWriter2;
    }


    // MODIFIES: this
    // EFFECTS: loads wishlist from file
    public void loadWishList() {
        try {
            wishlist = jsonReader1.readWishList();
            System.out.println("Loaded wishlist from " + JSON_STORE1);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE1);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads donelist from file
    public void loadDoneList() {
        try {
            donelist = jsonReader2.readDoneList();
            System.out.println("Loaded list of completed hairstyles from " + JSON_STORE2);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE2);
        }
    }

    // EFFECTS: saves the wishlist to file
    public void saveWishList() {
        try {
            jsonWriter1.openWishList();
            jsonWriter1.writeWishList(wishlist);
            jsonWriter1.closeWishList();
            System.out.println("Saved wishlist to " + JSON_STORE1);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE1);
        }
    }
    

    // EFFECTS: saves the donelist to file
    public void saveDoneList() {
        try {
            jsonWriter2.openDoneList();
            jsonWriter2.writeDoneList(donelist);
            jsonWriter2.closeDoneList();
            System.out.println("Saved list of completed hairstyles to " + JSON_STORE2);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE2);
        }
    }
    // Citation: the last 4 methods was modeled using the sample application
    // "JsonSerializationDemo"

    //public static void main(String[] args) {
        //new HairstyleLogApp();
    //}

    public WishList getWishList() {
        return wishlist;
    }

    public DoneList getDoneList() {
        return donelist;
    }
}
