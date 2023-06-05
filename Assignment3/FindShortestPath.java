// Name: Eunsung Kim 
// Program Description: the program finds the shortest path of a specified dungeon 
// from its initial chamber to its exit chamber. The path is displayed graphically and 
// length of the shortest path is displayed.

import java.io.FileNotFoundException;
import java.io.IOException;

public class FindShortestPath {

	public static void main(String[] args) {
		// exception handling
		try {
			// if there is no argument for input file
			if (args.length < 1)
				// throw an exception
				throw new Exception("No input file specified");
			// create Dungeon object with specified input file
			String dungeonFileName = args[0];
			Dungeon dungeon = new Dungeon(dungeonFileName);
			// create empty priority queue
			DLPriorityQueue<Hexagon> pq = new DLPriorityQueue<Hexagon>();
			// set start chamber to the initial chamber of the dungeon
			Hexagon start = dungeon.getStart();
			pq.add(start, 0);
			start.markEnqueued();
			// start the current chamber at the initial chamber
			Hexagon currCh = start;
			boolean pathFound = false;

			// repeat until priority queue is empty or reached exit chamber
			while (!pq.isEmpty() && !currCh.isExit()) {
				// remove the chamber with the smallest priority and dequeue it
				currCh = pq.removeMin();
				currCh.markDequeued();
				// if reached the exit chamber
				if (currCh.isExit()) {
					pathFound = true;
					break;
				}
				// boolean value to tell whether reached the dragon chamber or its neighbours
				boolean isDragon = false;
				if (!currCh.isDragon()) {
					for (int i = 0; i < 6; i++) {
						if (currCh.getNeighbour(i) != null && currCh.getNeighbour(i).isDragon()) {
							isDragon = true;
							break; // exit the loop
						}
					}
				} else
					isDragon = true;
				if (isDragon)
					continue; // return back to the loop

				// repeat for each neighbouring chamber to the current chamber
				for (int i = 0; i < 6; i++) {
					// if the chamber is not null, is empty, and is not marked dequeued
					if (currCh.getNeighbour(i) != null && !currCh.getNeighbour(i).isWall()
							&& !currCh.getNeighbour(i).isMarkedDequeued()) {
						// it is a neighbour of the current chamber
						Hexagon neighbour = currCh.getNeighbour(i);
						// get the euclidean distance from the current chamber to the initial chamber
						int D = 1 + currCh.getDistanceToStart();
						boolean modified = false;
						// if the distance from neighbour to the initial chamber is larger than it is
						// supposed to be
						if (neighbour.getDistanceToStart() > D) {
							// modify the distance to D
							neighbour.setDistanceToStart(D);
							modified = true;
							// set the predecessor chamber of the neighbour to current chamber
							neighbour.setPredecessor(currCh);
						}
						// if it is enqueued and modified
						if (neighbour.isMarkedEnqueued() && modified) {
							// update the priority is set as the sum of neighbour's distance from the start
							// and its distance from the exit
							pq.updatePriority(neighbour,
									neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon));
							// if not enqueued
						} else if (!neighbour.isMarkedEnqueued()) {
							// add the neighbour to the priority queue with the same priority as the
							// previous
							pq.add(neighbour, neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon));
							neighbour.markEnqueued();
						}
					}
				}
			}
			// length of the shortest path
			int shortest = 0;

			if (!pathFound)
				System.out.println("No path found");
			else {
				// back track from the last chamber to get the shortest length
				while (currCh != null) {
					shortest++;
					currCh = currCh.getPredecessor();
				}
				System.out.println("Path of legnth " + shortest + " found");
			}

			// catch all possible exceptions and print out the error message
		} catch (InvalidDungeonCharacterException e) {
			e.printStackTrace();
		} catch (InvalidNeighbourIndexException e) {
			e.printStackTrace();
		} catch (InvalidElementException e) {
			e.printStackTrace();
		} catch (EmptyPriorityQueueException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
