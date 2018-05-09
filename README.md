# coding exercise

## Building

Building from source can be done with the Gradle wrapper from the top-level directory--"Locations"

```bash
 ./gradlew clean build
```

## Running

To run the program and input possible paths, use the following format

```bash
 java -jar build/libs/Locations.jar '010' '000' '110'
```

The program will print out two important numbers-- the first one is the number of locations that can reach all other locations, and the second one is the number of locations that are reachable by all other locations

If you would prefer to use the Gradle wrapper, paths can be input using the following format.
```bash
 ./gradlew run -PpathArgs="['010', '000', '110']"
```

## Testing

To run just the exercise's Constraints and Examples in test form, use the Gradle wrapper in the top-level directory

```bash
 ./gradlew test
```

## Problem Statement

Model a game where there are certain locations people can stand, and from each location there are one-way paths that lead to other locations. People can move along paths from one location to another, but may not be able to go back retracing their steps along that same path. To play the game, you need to calculate two important numbers. The first number is the count of locations from which every other location can be reached. The second number is the count of locations that can be reached from every other location.

Create a class TeamBuilder with a method specialLocations that takes a String[] paths that models the way the locations are connected, and returns the two specific numbers described above.


Each element of paths will be a String containing as many characters as there are elements in paths. The i-th element of paths (beginning with the 0-th element) will contain a '1' in position j if there is a path that leads directly from i to j, and a '0' if there is not a path that leads directly from i to j.



Class:  TeamBuilder
Method: specialLocations
Parameters: String[]
Returns:  int[]
Method signature: int[] specialLocations(String[] paths)
(be sure your method is public)


Constraints
- paths will contain between 2 and 50 elements, inclusive.
- Each element of paths will contain N characters, where N is the number of elements of paths.
- Each element of paths will contain only the characters '0' and '1'.


## Examples
0)        

{"010","000","110"}

Returns: { 1,  1 }


1)        

{"0010","1000","1100","1000"}

Returns: { 1,  3 }


2)        

{"01000","00100","00010","00001","10000"}

Returns: { 5,  5 }



3)        

{"0110000","1000100","0000001","0010000","0110000","1000010","0001000"}

Returns: { 1,  3 }
