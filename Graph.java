import java.util.Queue;
import java.util.LinkedList;

public class Graph {
	// an array to store all vertexes
	Vertex[] myGraph;
	// array size
	public Graph(int size){
		myGraph = new Vertex[size];
	}
	
	public Vertex newAdjFriend(String friendHead)
	{
		// new adjacent vertex
		Vertex newAdjVertex = new Vertex(friendHead);
		// initialization
		newAdjVertex.next = null;
		return newAdjVertex;
	}
	
	public int findFriendIndex(String friend){
		//goes through myGraph until it finds the person
		for(int i = 0; i<myGraph.length; i++){
			if(friend.compareToIgnoreCase(myGraph[i].name)==0)
				return i;
		}
		return -1;
	}
	
	public String[] friendsOfFriends(Vertex lonely){
		//adds all of the friends of friends that arent the main person or people 
		//s/he is already friends with. It then adds them to a String array and returns it
		String[] fofList = new String[10000];
		String[] lonelyFriends = new String[100];
		int lfLength = 0;
		if(lonely.next == null){
			//checks if the person has no friends and thus no mutual friends
			System.out.println(lonely.name +" has no friends");
			return null;
		}
		Vertex loadFriends = lonely.next;
		for(int i = 0; (i < 10000)&&(loadFriends!=null); i++){
			if(loadFriends!= null){
				//loads in the current friends
				lonelyFriends[i]=loadFriends.name;
				loadFriends = loadFriends.next;
				lfLength = i;
			}
		}
		int count = 0;
		Vertex temp = lonely.next;
		while(temp != null){
			int index = findFriendIndex(temp.name);
			Vertex current = myGraph[index];
			while(current!= null){
				//goes through the while loop to check if the friend in the list is already 
				//friends with the given user or if it is the given user
				boolean notAlreadyFriends = true;
				for(int i = 0; i<= lfLength; i++){
					if(lonelyFriends[i]!= null){
						if(current.name.compareToIgnoreCase(lonelyFriends[i])==0)
							notAlreadyFriends = false;
					}
						
				}
				if(notAlreadyFriends){
					//if they arent already friends it adds the name to the list and increments 
					//the index
					fofList[count] = current.name;
					count++;
				}
				current = current.next;
			}
			temp = temp.next;
		}
		return fofList;
	}
	
	public int fofCount(String[] fofList, Vertex head){
		//goes through the String array from the previous method and counts up the number of 
		//unique names 
		String ignoreHead = head.name;
		int count = 0;
		for(int i = 0; (i<fofList.length)&&(fofList[i]!=null); i++){
			boolean incrementCount = true;
			if(fofList[i].compareToIgnoreCase(ignoreHead) ==0){
				incrementCount = false;
			}
			for(int j = 0; j<i; j++){
				if(fofList[i].compareToIgnoreCase(fofList[j])==0)
					incrementCount = false;
			}
			if(incrementCount){
				count++;
			}
		}
		return count;
	}
	
	public void topTenMutual(String[] mutualArray, int length, Vertex head){
		//gets passed the String array from the friendsOfFriends method and counts
		//the number each time  a name comes up
		String ignoreHead = head.name;
		String[] nameArray = new String[length];
		int[] countArray = new int[length];
		boolean newName = true;
		for(int i = 0; (i<mutualArray.length)&&(mutualArray[i]!=null); i++){
			newName = true;
			for(int j = 0; (j<i)&&(j<length); j++){
				if((nameArray[j]!= null)&&(mutualArray[i].compareToIgnoreCase(nameArray[j])==0)){
					newName = false;
					countArray[j]++;
				}
			}
			if(newName&&(mutualArray[i].compareToIgnoreCase(ignoreHead)!=0)){
				boolean continueCheck = true;
				for(int k = 0; (continueCheck)&&(k<length); k++){
					if(nameArray[k]==null){
						continueCheck = false;
						nameArray[k] = mutualArray[i];
						countArray[k]++;
					}
				}
			}
		}
		quicksort(countArray, nameArray, 0, length-1);
		//sort the code so that the countArray and nameArray are organized
		for(int i=1; i<11; i++ ){
			System.out.print(nameArray[length-i]+": ");
			System.out.println(countArray[length-i]);
		}
		
	}
	
	private void quicksort(int[] numbers, String[] names, int low, int high) {
		//modified discussion code
		int i = low, j = high;
		// Get the pivot element from the middle of the list
		int pivot = numbers[low + (high-low)/2];

		// Divide into two lists
		while (i <= j) {
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (numbers[i] < pivot) {
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (numbers[j] > pivot) {
				j--;
			}

			// If we have found a values in the left list which is larger than
			// the pivot element and if we have found a value in the right list
			// which is smaller than the pivot element then we exchange the
			// values.
			// As we are done we can increase i and j
			if (i <= j) {
				exchangeNum(numbers,i, j);
				exchangeNames(names,i,j);
				i++;
				j--;
			}
		}
		// Recursion
		if (low < j)
			quicksort(numbers,names,low, j);
		if (i < high)
			quicksort(numbers,names,i, high);
	}

	private void exchangeNum(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
	private void exchangeNames(String[] names, int i, int j) {
		String temp = names[i];
		names[i] = names[j];
		names[j] = temp;
	}
	
	public Vertex[] followPathToStart(Vertex start, String finish){
		//calls the BFT class and records the path taken from start to finish
		Vertex[] friendPath = new Vertex[20];
		int index = 0;
		friendPath[0] = myGraph[findFriendIndex(finish)];
		index++;
		while(start.name.compareToIgnoreCase(finish)!=0){
			Vertex v = breadthFirstTraversal(start,finish);
			friendPath[index] = v;
			finish = v.name;
			index++;
		}
		return friendPath;
		
	}
	
	public Vertex breadthFirstTraversal(Vertex start, String finish) {
		//checks if the friend is friends with the String given and if not
		//goes through BFT until the friend is found
		boolean friendFound = false;
		Vertex current= null;
		clearVisited();
		Queue<Vertex> vertexList = new LinkedList<Vertex>();
		vertexList.add(start);
		start.visited = true;
		while (!vertexList.isEmpty()&&!friendFound) {
			current = vertexList.remove();
			int num = findFriendIndex(current.name);
			Vertex temp = myGraph[num];
			while(temp!= null){
				if (!temp.visited) {
					if(temp.name.compareToIgnoreCase(finish)==0){
						friendFound = true;
					}	
				}
				temp.visited = true;
				int index =findFriendIndex(temp.name);
				vertexList.add(myGraph[index]);
				temp = temp.next;
			}
			
		}
		return current;

	}
	
	private void clearVisited() {
		//sets all of the visited variables to false
		for (int i=0; (i<myGraph.length)&&(myGraph[i]!=null); i++) {
			myGraph[i].visited=false;
			Vertex temp = myGraph[i];
			while(temp != null){
				temp.visited = false;
				temp = temp.next;
			}
		}
	}

}

