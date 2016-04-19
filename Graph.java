import java.util.*;
//construction of graph and vertex class is mostly from discussion files
public class Graph {
	// an array to store all vertexes
	Vertex[] myGraph = new Vertex[5000];
	// array size
	int graphSize = 0;
	String[] potentialFriends;
	int[] mutualNum;
	Deque<String> pathStack = new ArrayDeque<String>();
	// the adjacent vertex of a vertex
	public class AdjVertex
	{
		// adjacent vertex name
		String name;
		// the next adjacent vertex in the linked list
		AdjVertex next;
		boolean visited;
	}
	
	// the vertex
	public class Vertex
	{
		// the head of the linked list to all adjacent vertexes
		AdjVertex adjVertexHead;
		String name;
		boolean visited;
	}
	
//=========================================================================================================
	
	// initialize a new vertex
	public void newVertex(String name)
	{
		// new vertex initialized here
		myGraph[graphSize] = new Vertex();
		myGraph[graphSize].name = name;
		myGraph[graphSize].adjVertexHead = null;
		// maintain the size counter
		graphSize++;
	}
	
	// initialize a new adjacent vertex
	public AdjVertex newAdjVertex(String name)
	{
		// new adjacent vertex
		AdjVertex newAdjVertex = new AdjVertex();
		// initialization
		//newAdjVertex.vertexNum = vertexNum;
		newAdjVertex.name = name;
		newAdjVertex.next = null;
		//newAdjVertex.id = some;
		return newAdjVertex;
	}

	// add a new adjacent vertex to a vertex
	public void addAdjVertex(String currentVertex, String connection)
	{
		AdjVertex newAdjVertex = newAdjVertex(connection);
		// insert this new node to the linked list
		newAdjVertex.next = myGraph[getId(currentVertex)].adjVertexHead;
		myGraph[getId(currentVertex)].adjVertexHead = newAdjVertex;
	}
//============================================================================================================
	//returns true if a given name is not in the network
	public boolean isNew(String name){
		boolean isNew = true;
		for(int i=0; i<graphSize;i++){
			if((myGraph[i] != null) && (myGraph[i].name.compareToIgnoreCase(name) == 0)){
				isNew = false;
			}
		}
		return isNew;
	}//end isNew
	
	//returns the position a name has in the graph array
	public int getId(String name){
		for(int i=0; i<graphSize; i++){
			if(myGraph[i].name.compareToIgnoreCase(name) == 0){
				return i;
			}
		}
		return -1;
	}//end getId
	
	private boolean inList(String stranger){
		for(int i=0; i<potentialFriends.length; i++){
			if(potentialFriends[i] != null && (stranger.compareToIgnoreCase(potentialFriends[i])==0)){
				return true;
			}
		}
		return false;
	}//end inList
	
	//clears String and int arrays for findNewFriends
	private void clearLists(){
		for(int i=0; i<potentialFriends.length; i++){
			potentialFriends[i] = null;
		}
		for(int i=0; i<mutualNum.length; i++){
			mutualNum[i] = -1;
		}
	}//end clearLists
	
	//finds friends of friends, puts the name and number of mutual friends in arrays
	//then it calls a program to find the top ten
	public void findNewFriends(String source){
		potentialFriends = new String[graphSize];
		mutualNum = new int[graphSize];
		clearLists();
		int index = 0;
		AdjVertex myFriend = myGraph[getId(source)].adjVertexHead;
		while(myFriend != null){//each of my friends
			AdjVertex hisFriend = myGraph[getId(myFriend.name)].adjVertexHead;
			while(hisFriend != null) {//each of the friends of my friend
				//if they arent friends with the first person, arent the first person, and havent been added already
				if(!areFriends(hisFriend.name, source) && (hisFriend.name.compareToIgnoreCase(source) != 0) && !inList(hisFriend.name)){
					potentialFriends[index] = hisFriend.name;
					mutualNum[index] = findMutual(source, hisFriend.name);
					index++;
				}
				// next adjacent vertex
				hisFriend = hisFriend.next;
			}
			// next adjacent vertex
			myFriend = myFriend.next;
		}
		//sort both arrays in descending order based on number of mutual friends
		quicksort(0, mutualNum.length-1);
		//print top 10 recommended friends
		System.out.println("Suggested friends for " + source + " are: ");
		System.out.println("NAME | NUMBER OF MUTUAL FRIENDS");
		for(int i=0; i<10; i++){
			System.out.println(potentialFriends[i] + "| " + mutualNum[i]);
		}
		System.out.println("TOTAL NUMBER OF POTENTIAL FRIENDS: " + friendLength(potentialFriends));
	}//end findNewfriends
	
	//quicksort method from discussion
	private void quicksort(int low, int high) {
		int i = low, j = high;
		// Get the pivot element from the middle of the list
		int pivot = mutualNum[low + (high-low)/2];
		// Divide into two lists
		while (i <= j) {
			while (mutualNum[i] > pivot) {
				i++;
			}
			while (mutualNum[j] < pivot) {
				j--;
			}
			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
			}
		}
		// Recursion
		if (low < j)
			quicksort(low, j);
		if (i < high)
			quicksort(i, high);
	}//end quicksort
	
	//swaps values in array
	private void exchange(int i, int j) {
		//swap values
		int temp = mutualNum[i];
		mutualNum[i] = mutualNum[j];
		mutualNum[j] = temp;
		//swap the same friends
		String Stemp = potentialFriends[i];
		potentialFriends[i] = potentialFriends[j];
		potentialFriends[j] = Stemp;
	}//end exchange
	
	private int friendLength(String[] array){
		int count =0;
		for(int i=0; i<array.length; i++){
			if(array[i] != null){
				count++;
			}
		}
		return count;
	}
	
	//finds the mutual friends between two people
	private int findMutual(String me, String stranger){
		int num = 0;
		AdjVertex myfriend = myGraph[getId(me)].adjVertexHead;
		while(myfriend != null){
			 if(areFriends(myfriend.name, stranger)){
				 num++;
			 }
			 myfriend = myfriend.next;
		}
		return num;
	} //findMutual
	
	//returns boolean for if two people are friends or not
	public boolean areFriends(String one, String two) {
		AdjVertex friend = myGraph[getId(one)].adjVertexHead;
		while(friend != null) // search in the adjacent list
		{
			if(friend.name.compareToIgnoreCase(two)==0) // friends?
				return true;
			// next adjacent vertex
			friend = friend.next;
		}
		return false;
	}//end areFriends
	
	//clears the visited attribute to false for all vertexes
	private void clearVisited(){
		for(int i=0; i<graphSize; i++){
			myGraph[i].visited = false;
			AdjVertex vert = myGraph[i].adjVertexHead;
			while(vert != null){
				vert.visited = false;
				vert= vert.next;
			}
		}
	}//end clear visited
	

	//finds the path from one person to another person through friendships
	//modified from Breadth-first transversal
	public void connector(String start, String finish){
	 	clearVisited();
	 	boolean found = false;
	 	Queue<Vertex> vertexList = new LinkedList<Vertex>();
	 	vertexList.add(myGraph[getId(start)]);
	 	myGraph[getId(start)].visited = true;
	 	while (!vertexList.isEmpty() && !found) {	
			Vertex current = vertexList.remove();
			AdjVertex friend = current.adjVertexHead;
			while(friend.next != null && !found){	
					if (!friend.visited) {	 //if we have not visted this, make it visited and add it to the queue
						friend.visited = true;	
						vertexList.add(myGraph[getId(friend.name)]);
					}
					if(friend.name.compareToIgnoreCase(finish) == 0){ //if we found what we want, backtrack the path
						found = true;
						backTrack(start, friend, current);
						break;
					}
					friend = friend.next;
			}	
	 	}
	 	if(!found){
	 		System.out.println("There is no connection between " +start+ " and " +finish);
	 		System.out.println();
	 	}
	}// end connector
	
	//tracks the path from the second person back to the first person
	//utilizes a stack by pushing the names from the second person to the first person
	//these names are then popped off the stack as a path
	private void backTrack(String start, AdjVertex end, Vertex parent){
		if(parent.name.compareToIgnoreCase(start) == 0){ 
			//if we have found our way back to the start, then we print the result
			if(pathStack.isEmpty()){
				System.out.println(start + " -- " + end.name);
			}
			else{
				System.out.print(start + " -- " + end.name);
				while(!pathStack.isEmpty()){
					System.out.print(" -- " + pathStack.pop());
				}
				System.out.println();
			}
		}
		//if we still have to find our way back to the first person,
		//we push the end name into the stack, 
		else{ 
			pathStack.push(end.name);
			connector(start, parent.name);
		}
	}//end backTrack
}// end class


