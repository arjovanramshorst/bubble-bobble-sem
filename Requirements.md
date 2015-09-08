#Requirements Bubble Bobble 

##Daan Vermunt, Matthijs Rijlaarsdam, Arjo van Ramshorst, Eric Dammeijer & Wouter Zirkzee

##Group 15, TA : Aaron Ang, Supervisoer : Alberto Bacchelli






###Functional requirements

**Must haves**
-The game's playing field must consist of platforms to stand on.
-The game's playing field must be surrounded with walls, except for holes in the bottom and top.
-The game must have a 2d perspective.
-The player must be able to move around the playing field in two directions (left and right).
-The player must be able to jump.
-The player must be able to jump through the bottom of a platform.
-There must be gravity in the playing area for all objects including the player.
-When the player falls through the bottom of the playing area, he must come out at the top.
-When the player collides with an enemy, the player will die.
-The player must be able to fire bubbles.
-If a bubble contains an object, it must collide with the roof of the level. ( can't escape the level )
-If a bubble hits an enemy, the enemy is consumed by that bubble for a set amount of time.
-If the player collides with an enemy that is consumed by a bubble, the enemy must die and thus the bubble dissapears.
-Enemies should be able to move.
-The player must receive points after an enemy dies.
-The game must end if a set amount of lives is used.
-The game must have a grid with the dimensions 50x50.
-Bubbles must move up and be able to go through platforms.


**Should haves**
-The score should be displayed on the current screen.
-The game should have basic sound effects (shooting bubbles and defeating enemies).
-The game should have different levels/stages.
-The game should have multiple types of enemies with different kinds of movement and behavior, and looks.
-When the player has killed all the enemies in a stage, a new level should be reached.
-A bubble that is fired should go in the direction that it is shot, and should then move up, until it leaves the screen.

**Could haves**
-The game could have a main menu that shows access to the game and high-scores.
-The game could have a pause and resume option.
-The game could have power ups that appear during gameplay which boost the velocity or speed in which bubbles are fired.
-The game could have tokens that appear if a level is done, which give extra points.
-Killed enemies could drop power-ups or objects that give extra points.
-The enemies could change their movement based on the player's position.

**Would/Won't haves**
-The game shall save the scores of all of its players using a high-score list with the top ten of names and scores of players with the highest scores.

####Non-functional requirements
-The game shall be playable on Windows (7 or higher), Mac OS X (10.8 and higher), and Linux.
-The game shall be implemented in Java
-A first fully working version of the game shall be delivered at September 11, 2015
-For the iterations after the delivery of the first fully working version, the Scrum methodology shall be applied
-The implementation of the game shall have at least 75% of meaningful line test coverage (where meaningful means that the tests actually test the functionality of the game and for example do not just execute the methods involved)
-Pull requests shall be used to update and make changes to the game.
-Every pull request shall receive at least one code review.
    \item{The enemies could change their movement based on the player's position.}