# 1DV508 Project Course in Computer Science - Cookbook by group COBOL

## Group members
* Iryna Koval
* Maxime Audrain 
    * Scrum Master
* Vanessa Jäger
* Andreas Kovacs
    * Program Owner
* Renaud Machecourt-Bourgeois
* Mariia Petrova
    * Note Taker
* Manon Ykema


## About the project

This project is about creating a cookbook as a computer application. This application would, mostly but not only allow a user to log in, browse all existing recipes and view details on them, as well as manage different lists of recipes unique to that user. A graphical interface will be implemented to make the user experience more comfortable, and a database containing all the data concerning reciped and users will be linked to the application. For all of that, Java, JavaFX and MySQL will be used as programming languages.

## Clone git 
Set authentication right for Git. 
```
git config --global user.name "Firstname Lastname"
```
```
git config --global user.email "youremail@student.lnu.se"
```

Clone project. 
```
git clone git@gitlab.lnu.se:1dv508/student/cobol/cookbook.git
```

Git clone creates new folder with repo as contents. Navigate to folder. 
```
cd cookbook
```

Git switch and (if doesn't exist) create branch with the "-c". If it already exists, remove `-c`. 
```
git switch -c dev
```

## Usage
**Build :**
```
./gradlew build
```

**Run :**
```
./gradlew run -q --console=plain
```

## User stories check list
- [X] 1. As a user I want to be able to start the application and get an animation
to welcome me
- [X] 2. As a user I want to be able to log in to the application so that it is personalised
for me
- [X] 3. As a user I want to see my favourite recipes for quick access
- [X] 4. As a user I want to be able to see the list of weekly dinner lists
- [ ] 5. As a user I should be able to select and display the dinner list for a week
- [X] 6. As a user I should be able to search for a recipe by name to see it
- [ ] 7. As a user I should be able to search for a recipe by one or more ingredients
to see it
- [ ] 8. As a user I should be able to search for a recipe by one or more tags to
see it
- [X] 9. As a user I want to be able to browse all recipes to select which I want
to see
- [X] 10. As a user I want see more the short description of a dish from a search
by hovering or clicking on it to know if I want to see the whole recipe
- [ ] 11. As a user I want to create a new recipe so that I can see it later.
- [ ] 12. As a user I want to be able to add a name, short description, ingredients
and detailed description to a recipe
- [X] 13. As a user I want to see the selected recipe with formatting (bold and
larger size for name, italics for short description, lists for ingredients
and detailed description) for easy viewing
- [X] 14. As a user I should be able to add comments to a recipe for everyone to
see to highlight something important with it
- [X] 15. As a user I must be able to edit and remove comments I make on recipes
if I make a mistake
- [ ] 16. As a user I should be able to adjust the number of persons a recipe is
for by even numbers so that I get the correct amount of ingredients
- [ ] 17. As a user I want to be able to add one or more tags to a recipe so that it
is easier to find later
- [ ] 18. As a user I want to be able to select from a number of predefined tags
(vegetarian, vegan, lactose free, gluten free, starter, main course, dessert
and sweets)
- [ ] 19. As a user I should be able to add my own tags to a dish for making it
easy to find later
- [X] 20. As a user I should be able to “star” a recipe as one of my favourites
- [X] 21. As a user I must be able to “unstar” a recipe to remove it from my list
of favourites
- [X] 22. As a user I want to be able to create weekly dinner lists for several weeks
so that I can display them later
- [X] 23. As a user I want to add dishes to created weekly dinner lists (one, two
or more dishes per day)
- [ ] 24. As a user I want to be able to generate a shopping list with what I need
to by for a week
- [ ] 25. As a user I want to be able to modify the shopping list so that it does
not contain things I already have at home
- [ ] 26. As a user I want to be able to send a recipe to another user so that he/she
can cook it
- [ ] 27. As a user I want to add a message to a recipe that I send to another user
to inform why I did it
- [ ] 28. As a user I want access to a help system with a tutorial of how the
program works so that I can understand how to use it
- [ ] 29. As a user I want to be able to search the help system for keywords to
better understand a feature
- [ ] 30. As an admin I want to be able to add a new user with user name, display
name and a password
- [ ] 31. As an admin I want to be able to modify an existing user if something
is wrong
- [ ] 32. As an admin I want to be able to delete an existing user it the user no
longer should be able to use the system


**Implenting loding content of page to the center of MainLayout :**
- [ ] 1. Begin from fxml file: remove part that displays header and footer.
Make sure that part that was left is wrapped in ScrollPane.
- [ ] 2. Move to View file of your page and change 'private Parent root;' to 'private ScrollPane root;'.
Then accordingly to that changes refactor the type of return value in getRoot() function(ScrollPane instead Parent)
- [ ] 3. Move to Controller.java file and refactor function that is responsible for displaying your page.
          Create a class instance of View and invoke LoadContent on this.mainView 
          with parameter that equals to root of the previous created instances of View:
         PageView pageView = new PageView();
         this.mainView.LoadContent(pageView.getRoot());