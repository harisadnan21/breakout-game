# Breakout Plan
### Haris Adnan


#### Examples

You need to put blank lines to write some text

in separate paragraphs.


Emphasis, aka italics, with *asterisks* or _underscores_.

Strong emphasis, aka bold, with **asterisks** or __underscores__.

Combined emphasis with **asterisks and _underscores_**.


You can also make lists:
* Bullets are made with asterisks
1. You can order things with numbers.


You can put links in like this: [Duke CompSci](https://www.cs.duke.edu)



## Interesting Breakout Variants

 * Idea #1

I found the Brick Breaker Hero game very interesting because it's different from the other games in the sense that there is an enemy that you have to defeat that can move around and is defended by different obstacles. The boss also can defend himself.
 * Idea #2

Fairy Treasure stuck out to me because in this game, you can clearly see how the ball reflects depending on where it hits the paddle. Also, the sound that the game makes when the ball hits the blocks is very satisfiying and I believe the way that the ball can hit multiple blocks by reflecting off of different blocks is also an aspect I'd like to have in my version as well.


## Paddle Ideas

 * Idea #1

The ball will bounce depending on which part of the paddle it hits, the middle third causes the ball to bounce perpendicularly to the paddle, the left third reflects the ball to the left side(20 degrees) and the right third makes the ball reflect to the right(20 degrees).


 * Idea #2

The paddle will be able to move forward, backwards -to a certain extent- and left and right- from the left side of the screen to the right. 

## Block Ideas

 * Idea #1

The blocks will take multiple hits before getting destroyed. This number will depend on the size of the blocks.

 * Idea #2

Some special blocks will give the player a big score addition.

 * Idea #3

Unless a block is on its last hit, the ball will reflect off of the blocks, and there is a possibilty of hitting multiple blocks in one go.


## Power-up Ideas

 * Idea #1

This power up slows down the ball.

 * Idea #2

This power up increases the length of the paddle. 
 * Idea #3

This power up slows down the paddle

## Cheat Key Ideas

 * Idea #1

This cheat allows the player to shoot a ball that will destroy every block in its path.
 * Idea #2

This cheat code gives the player one extra life

 * Idea #3

This cheat code will pass the player onto the next round.
 * Idea #4

This cheat code adds a certain number of points to the player's score
## Level Descriptions

 * Idea #1
 
The numbers indicate the sizes of the blocks.
(Random ordering)

1 1 0 3 1 1

1 0 5 0 1 1 

1 0 3 0 0 1

4 0 0 4 0 1

0 1 0 3 1 0

3 0 4 0 1 0

0 0 0 0 0 0

0 0 0 0 0 0

 * Idea #2


Smaller size of paddle, and the speed of the ball is faster. The bigger blocks are surrounded by smaller blocks.

1 1 5 5 1 1

0 0 1 1 0 0

0 1 2 2 1 0

1 3 4 4 3 1

0 1 2 2 1 0

0 0 1 1 0 0

0 0 0 0 0 0

0 0 0 0 0 0


 * Idea #3
5 0 0 0 0 5

1 1 3 3 1 1

0 1 5 4 2 0

2 3 2 3 2 2

0 1 0 1 0 1

1 0 1 0 1 0

0 0 0 0 0 0

0 0 0 0 0 0


This level has more paddle slow down power ups. The level will have multiple "rocks", which are blocks that are similar to the normal ones, except they can't be broken and won't allow the ball to pass through.



## Class Ideas

 * Idea #1

 powerups class.

Method: paddleLength()


 * Idea #2

runClass

Method: setupGame()


 * Idea #3

paddleClass

Method: paddleMove()

Method: paddleHit()



 * Idea #4

mapClass

Method: level1()

Method: level2()

Method: level3()
