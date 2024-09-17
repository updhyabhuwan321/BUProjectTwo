BUProjectTwo.java
This Java program simulates a hiker's journey through a forest, which is represented as a 2D array where different terrains such as clear paths, rocks, water, and volcanoes are coded as integers. The program reads the map from a file, initializes the hiker's journey from a random starting point, and directs the hiker southward, ensuring the path is clear.

Key Features:

File Input: Prompts the user for a file that contains the 2D array representing the forest. Each value in the array corresponds to a specific type of terrain:
0: Clear Path
1: Rocks
2: Water
3: Volcano
Terrain Navigation: The hiker starts at a random position and attempts to move southward through clear paths, adjusting direction if blocked.
Random Starting Point: The hiker’s journey begins at a randomly chosen position on the map.
Pathfinding Algorithm: Guides the hiker through the forest while avoiding obstacles and tracks the number of moves made during the journey.
Map Visualization: Displays the map with labeled rows and columns, along with the hiker’s movement as they navigate through the forest.
