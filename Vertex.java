
public class Vertex {
	String name;
	Vertex next;
	boolean visited = false;

	//AdjFriend adjFriendHead = new AdjFriend();

	public Vertex(String head) {
		// set the head of the linked list
		name = head;
		
	}
	
	// initialize a new adjacent vertex
	public Vertex newAdjFriend(String friendHead)
	{
		// new adjacent vertex
		Vertex newAdjVertex = new Vertex(friendHead);
		// initialization
		newAdjVertex.next = null;
		return newAdjVertex;
	}

}
