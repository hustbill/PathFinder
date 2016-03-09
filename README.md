# PathFinder
DFS

## PathFinder

Perform basic string parsing in Java

Use basic algorithms / data structures in Java

Path findingPerform string parsing in Java

Manipulate dates in Java

Use multiple Java utilties

Input_1.txt

A E

A : B C D

B : C

C : E

D : B

Output:

A-> B -> C-> E

A-> C->E

A-> D ->B->C->E

List<String> return all paths from node A to node E.
    

## Log Parser (for Java)
Log Parser (for Java)

Objective:

Write a program that will read a log file and calculate the percent of time a player had network connectivity.

Details:

We have several games that involve connecting to our servers via the network. Mobile network conditions can be very different between devices, providers, and so on. For diagnostic reasons it's important to know what portion of the time a given player spends actually connected to the network.

We keep console logs of various game subsystems for each play session. Each log message has the following format:

(MM/dd/yyyy-hh:mm:ss) :: [message logged]

Sample log lines:


(11/12/2015-02:34:56) :: START
(01/02/1990-13:10:00) :: DISCONNECTED
(03/13/2018-21:01:01) :: ERROR - File "close.png" not found.





Log messages that pertain to network connectivity are as follows:

- START : Logged when the game starts up.
- CONNECTED : Logged when a network connection is established.
- DISCONNECTED : Logged when network connection is lost.
- SHUTDOWN : Logged when the player is quitting the game.

A player's session length is the amount of time between the START and SHUTDOWN messages.

A player's connected time is the amount of time they spend in a connected state. A player starts the game disconnected, becomes connected when we log a CONNECTED message, and becomes disconnected when we log a DISCONNECTED message.

You can make the following assumptions:

- All logs will be properly formatted
- All dates will be valid
- No log message will stretch across multiple lines
- All log files will be ordered chronologically
- There will always be exactly one START and exactly one SHUTDOWN event logged

Input

A file containing lines of log messages.

Output

The connectivity percentage as a string, rounded down to an integer.

Examples