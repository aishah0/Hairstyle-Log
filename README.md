# My Personal Project


This Java application will help users keep track of their past hairstyles, plan future hairstyles, and manage the costs involved. As a black woman, I know that planning is crucial to both natural and protective hairstyles, and alternating between styles is important to avoid hair damage to my hair type. Users will be able to log the details of each completed hairstyle, with its name and cost. Additionally, the app will provide a way to add styles to a wishlist as inspiration for future hairstyles. 

This project is of interest to me because I have observed that black hair services are not always readily available in Vancouver, and are often overpriced due to the small demographic. The primary users of this application will be individuals such as myself who frequently change hairstyles, particularly those who want to manage the costs involved in their hair care routines. This application will streamline the process of selecting, preparing for, and tracking the costs of each hairstyle, helping users manage both their time and budget more effectively.

#### User Stories:

- As a user, I want to be able to create a hairstyle, specify its cost, and add it to my personal log of past hairstyles.
- As a user, I want to be able to view my list of past hairstyles with the cost of each hairstyle.
- As a user, I want to be able to create a new hairstyle, specify its estimated cost, and add it to my wishlist for future styling.
- As a user, I want to be able to view the wishlist of my potential hairstyles with the estimated cost for each hairstyle.
- As a user, I want to be able to edit the cost of any hairstyle in my wishlist.
- As a user, I want to be able to remove a hairstyle from my wishlist.
- As a user, I want to have the option to save my wishlist and completed list to file before closing the application.
- As a user, when I start the application, I want to be given the option to reload my wishlist and my completed list from file.

#### Instructions for End User:
- You can generate the action "add a hairstyle to the wishlist" clicking the button labelled "Hairstyle WishList", and then clicking the button labelled "Add hairstyle".
- You can generate the first required action related to the user story "remove a hairstyle from the wishlist" by clicking the button labelled "Hairstyle WishList", and then clicking the button labelled "Remove hairstyle".
- You can generate the second required action related to the user story "edit the cost of any hairstyle in the wishlist" by clicking the button labelled "Hairstyle WishList", and then clicking the button labelled "Edit cost of hairstyle".
- You can locate my visual component by running the application and clicking the button labelled "Hairstyle WishList". A background image should appear above a few buttons.
- You can save the state of my application by clicking the button labelled "Hairstyle WishList", and then clicking the button labelled "Save wishlist".
- You can reload the state of my application by clicking the button labelled "Hairstyle WishList", and then clicking the button labelled "Load wishlist".

#### Phase 4: Task 2
Event Log:  
Thu Nov 28 14:29:10 PST 2024  
Hairstyle:locs added to completed list.  
Thu Nov 28 14:29:11 PST 2024  
All hairstyles in completed list displayed.  
Thu Nov 28 14:29:21 PST 2024  
Hairstyle:cornrows added to wishlist.  
Thu Nov 28 14:29:29 PST 2024  
Cost of Hairstyle:cornrows changed.  
Thu Nov 28 14:29:39 PST 2024  
Hairstyle:twists added to wishlist.  
Thu Nov 28 14:29:44 PST 2024  
Hairstyle:cornrows removed from wishlist.  
Thu Nov 28 14:29:46 PST 2024  
All hairstyles in wishlist displayed.  
Thu Nov 28 14:29:51 PST 2024  
Application closed.


#### Phase 4: Task 3
I could make my project adhere better to the Single Responsibility Principle by reducing the responsibilities of my MainMenuPanel class. Currently, this class is responsible for both being the app's entry point and managing a key part of the GUI. A possible refactoring solution would be to separate the application initialization and event-handling from the GUI components. I could extract a dedicated controller class that would handle events, while the other GUI class would be responsible for rendering UI elements. Another possible refactoring change to improve my design would be using the Observer pattern to manage event logs. I could create a dedicated observer class to "watch" the application state and log events when they occur. Separating the logging functionality from my UI classes would make my code design more flexible and easier to extend.