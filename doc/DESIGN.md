# Breakout Design
## NAME

Haris Adnan

## Design Goals

The project was designed such that all code that related to the running of the program be kept in the runClass. Other classes would be Cheat, Powerups, Ball(this class would contain functions that determine the functionality of the ball ), the blocks class(this class would contain all methods that define different block types and their functionality), and the collision class (which would hol functions that define how the ball collides with different objects and what these collisions result in). the new features that I intended to be easily extendable were differnt cheats, differnt blocks, and different power-ups. I think the way the ball collides can also be modified by extension. 



## High-Level Design

The project is made up of 5 classes: Cheats, Main, runClass, Score, and Rectangleobj. Main runs the program. runClass sets up the scene and the program, further it also has the step function that determines what will happen in each frame. This class cointains the bulk of code that relates to the different types of blocks, collisions, levels made, different power-ups and functionality of the paddle. The Score Class updates the lives, level, and user score with each frame. The Cheats class is designed to implement functionality of all the cheats that the user can use by clicking certain keys. The Rectangleobj was a class I made that would define a new object - this object would be a rectangle and an int. I intended to replace the Rectsangles in my code with these Rectangleobj's so that each block could keep a coumnt of how many times the block had been hit. Unfortunately, I was not able to implement this without resolving errors in the code.


## Assumptions or Simplifications

I decided to not make the paddle reflect the ball such that if the ball hits the left or right third of the paddle, the ball reflects in the direction it came from. I tried to implement the code but that led to collision problems with the paddle. Further, I decided to not add a powerup that would allow the user to fire lasers into the blocks. I also decided not to include sprites in the shapes and instead differentiated the blocks and their functionality based off their color.

## Changes from the Plan

For my original I intended to implement most of my functions in the runClass and then make classes afterwards in which I will be able to sort code accordingly. But, by the deadline, I didnt have enough time to make these changes. Further, I also didnt add a cheat that would allow the ball to wreck every block in its path without the ball reflecting off. I also didn't implement the classes in the way that I wanted to.


## How to Add New Levels

Adding the different reflection functionality for the paddle can be added by using if functios to check where the ball hits the paddle adn then change the x and y directions based off that information. I could have segregated code into different classes by creating the classes as I think about new objects to introduce into the code and not create them afterwards. I could implement the festure of wrecking every ball by creating a new collision funtion for the ball that would allow the ball to do this.