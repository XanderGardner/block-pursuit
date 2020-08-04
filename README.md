# Block-Pursuit
An intense JavaFX game. Try and avoid the AI; the longer you survive, the higher your score and the faster sprites move. Different modes and maps. Fun music.

AI uses a new pathfinding method, which I call the "lilypad method". The user can toggle ON/OFF if the "lilypads" are visible. Basically, the pathfinding works by
continuously checking if a path straight to the destination is safe and reassessing to a new destination. The con of this method is that the "lilpads", where the AI can reset 
its destination, must be set before running. The pro is that the lilypad method run VERY quickly, even compared to A*!


NOTE: High scores were originally stored in files. All code which does this is commented out because it won't work on any computer running the jar/exe file. Thus, until
I learn SQL or more about files, the high scores will be kept in memory and not storage.
