import java.util.*;
public class P4Main {
	public static void main(String[] args){
		
		//loads persons and connections into the network
		String dataFile = "facebook_network.txt";
		String findFile = "find_friends.txt";
		importer read_in = new importer();
		System.out.print("Importing network..");
		Graph network = read_in.ReadNetwork(dataFile, 170175);
		System.out.println("Network Ready");
		String[] findFriends = read_in.ReadFinder(findFile, 10);
		
		//finds top 10 suggested friends and prints the total number of "friends of fiends"
		for(int i=0; i<findFriends.length; i++){
			network.findNewFriends(findFriends[i]);
			System.out.println("------------------------------------------------------");
		}
		//finds a path of friends from one person to another
		for(int i=0; i<findFriends.length; i+=2){
			System.out.println("From " + findFriends[i]+" to " +findFriends[i+1]+ ":");
			network.connector(findFriends[i], findFriends[i+1]);
			System.out.println();
		}
		

		
		
	}
}// end main

