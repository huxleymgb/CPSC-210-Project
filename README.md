# My Personal Project

## A D&D Character Tracker

This application will track someone's characters for the tabletop game D&D - containing their **name**, the **6 main stats**, their **class**,
their **life status**, and a **brief description** of the character. 
It will be used by people who have many characters in this game and want a *summary* of all their characters at a glance.

This project is of interest to me as I used to play this game with my friends, and a common complaint was that it was difficult to keep track of characters through many ongoing games.

## User Stories:
- As a user, I want to be able to add characters to my tracker.
- As a user, I want to be able to remove characters from my tracker.
- As a user, I want to be able to view a list of all my characters in the tracker.
- As a user, I want to be able to select a player and view their individual stats.
- As a user, I want to be able to update the life status of a character, if they die in game.
- As a user, I want to be able to save my character tracker to a file.
- As a user, I want to be able to load my character tracker from a file.

## Instructions For Grader:
You can generate the first required event related to adding multiple Xs to a Y by pressing the "add character" button on the far left. This can add multiple characters to your tracker.
It will prompt you to input a lot of fields. Try the following:
  - Any name
  - 10
  - 10
  - 10
  - 10
  - 10
  - 10
  - "Class"
  - "Description"

This will create a basic character and pressing "update character viewer" will show you the character you have added. You can create as many characters as you like.

You can generate the second required event relating to Xs and Ys by pressing the "remove character" button. You must have already added a character to do so.
This will bring up a menu where you can select the name of your character and remove it with the new "Submit character for removal" button.
Once done, press the "update character viewer" button to see your change.

My visual component is the icon for the tracker itself and the icons for the buttons. These were all designed and made by me in Powerpoint.

You can save and load the character tracker by pressing the save / load buttons. By default, there are no characters saved.

## Phase 4 Task 2:
Two events are logged: when you add / remove a character. It will print to console (upon exit)
- "Added a new character to list."
- "Removed a character from list."

for each character added / removed (along with timestamps).

## Phase 4 Task 3:
Looking back, I like how the model turned out. The relationship between Character and CharacterList didn't cause any issues.
But I wish I had implemented the UI a little better, perhaps with more class cohesion by making multiple classes. Having everything in one class
made it difficult / repetitive, especially when similar code had to be repeated for all the functions of the program. If I were
to do this again I would probably make more classes in the UI to keep everything cohesive. With all the graphics, button functionality, 
persistence, etc. it would be nicer to separate these into different classes and call methods from each other with one static Character App created.