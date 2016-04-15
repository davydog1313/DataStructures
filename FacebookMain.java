
public class FacebookMain {

	public static void main(String[] args) {
		FBReader reader = new FBReader();
		Graph graph = new Graph(3959);
		
		graph.myGraph = reader.loadFriendsToGraph("C:/Users/Eric/workspace/Project4/src/facebook_network.txt", 3959);

		System.out.println("\n-------------------------------");
		System.out.println("Recommended numbers of friends: ");
		System.out.println("-------------------------------");

		int ryanIndex = graph.findFriendIndex("Ryan");
		String[] ryanMutualFriends = graph.friendsOfFriends(graph.myGraph[ryanIndex]);
		int ryanNum = graph.fofCount(ryanMutualFriends,graph.myGraph[ryanIndex]);
		System.out.println("\nRyan recommended friends: "+ryanNum);

		int spencerIndex = graph.findFriendIndex("Spencer");
		String[] spencerMutualFriends = graph.friendsOfFriends(graph.myGraph[spencerIndex]);
		int spencerNum = graph.fofCount(spencerMutualFriends,graph.myGraph[spencerIndex]);
		System.out.println("Spencer recommended friends: "+spencerNum);
		
		int jacobsIndex = graph.findFriendIndex("Jacobs");
		String[] jacobsMutualFriends = graph.friendsOfFriends(graph.myGraph[jacobsIndex]);
		int jacobsNum = graph.fofCount(jacobsMutualFriends,graph.myGraph[jacobsIndex]);
		System.out.println("Jacobs recommended friends: "+jacobsNum);
		
		int henryIndex = graph.findFriendIndex("Henry");
		String[] henryMutualFriends = graph.friendsOfFriends(graph.myGraph[henryIndex]);
		int henryNum = graph.fofCount(henryMutualFriends,graph.myGraph[henryIndex]);
		System.out.println("Henry recommended friends: "+henryNum);
		
		int pierceIndex = graph.findFriendIndex("Pierce");
		String[] pierceMutualFriends = graph.friendsOfFriends(graph.myGraph[pierceIndex]);
		int pierceNum = graph.fofCount(pierceMutualFriends,graph.myGraph[pierceIndex]);
		System.out.println("Pierce recommended friends: "+pierceNum);
		
		int oliverIndex = graph.findFriendIndex("Oliver");
		String[] oliverMutualFriends = graph.friendsOfFriends(graph.myGraph[oliverIndex]);
		int oliverNum = graph.fofCount(oliverMutualFriends,graph.myGraph[oliverIndex]);
		System.out.println("Oliver recommended friends: "+oliverNum);
		
		int griffinIndex = graph.findFriendIndex("Griffin");
		String[] griffinMutualFriends = graph.friendsOfFriends(graph.myGraph[griffinIndex]);
		int griffinNum = graph.fofCount(griffinMutualFriends,graph.myGraph[griffinIndex]);
		System.out.println("Griffin recommended friendss: "+griffinNum);
		
		int hillIndex = graph.findFriendIndex("Hill");
		String[] hillMutualFriends = graph.friendsOfFriends(graph.myGraph[hillIndex]);
		int hillNum = graph.fofCount(hillMutualFriends,graph.myGraph[hillIndex]);
		System.out.println("Hill recommended friends: "+hillNum);
		
		int archerIndex = graph.findFriendIndex("Archer");
		String[] archerMutualFriends = graph.friendsOfFriends(graph.myGraph[archerIndex]);
		int archerNum = graph.fofCount(archerMutualFriends,graph.myGraph[archerIndex]);
		System.out.println("Archer recommended friends: "+archerNum);
		
		int colvinIndex = graph.findFriendIndex("Colvin");
		String[] colvinMutualFriends = graph.friendsOfFriends(graph.myGraph[colvinIndex]);
		int colvinNum = graph.fofCount(colvinMutualFriends,graph.myGraph[colvinIndex]);
		System.out.println("Colvin recommended friends: "+colvinNum);
		
		System.out.println("\n----------------------------");
		System.out.println("TOP MUTUAL FRIENDS FOR RYAN:");
		System.out.println("----------------------------");
		graph.topTenMutual(ryanMutualFriends, ryanNum, graph.myGraph[ryanIndex]);
		
		System.out.println("\n-------------------------------");
		System.out.println("TOP MUTUAL FRIENDS FOR SPENCER:");
		System.out.println("-------------------------------");
		graph.topTenMutual(spencerMutualFriends, spencerNum, graph.myGraph[spencerIndex]);
		
		System.out.println("\n------------------------------");
		System.out.println("TOP MUTUAL FRIENDS FOR JACOBS:");
		System.out.println("------------------------------");
		graph.topTenMutual(jacobsMutualFriends, jacobsNum, graph.myGraph[jacobsIndex]);
		
		System.out.println("\n-----------------------------");
		System.out.println("TOP MUTUAL FRIENDS FOR HENRY:");
		System.out.println("-----------------------------");
		graph.topTenMutual(henryMutualFriends, henryNum, graph.myGraph[henryIndex]);
		
		System.out.println("\n------------------------------");
		System.out.println("TOP MUTUAL FRIENDS FOR PIERCE:");
		System.out.println("------------------------------");
		graph.topTenMutual(pierceMutualFriends, pierceNum, graph.myGraph[pierceIndex]);
		
		System.out.println("\n------------------------------");
		System.out.println("TOP MUTUAL FRIENDS FOR OLIVER:");
		System.out.println("------------------------------");
		graph.topTenMutual(oliverMutualFriends, oliverNum, graph.myGraph[oliverIndex]);
		
		System.out.println("\n-------------------------------");
		System.out.println("TOP MUTUAL FRIENDS FOR GRIFFIN:");
		System.out.println("-------------------------------");
		graph.topTenMutual(griffinMutualFriends, griffinNum, graph.myGraph[griffinIndex]);
		
		System.out.println("\n----------------------------");
		System.out.println("TOP MUTUAL FRIENDS FOR HILL:");
		System.out.println("----------------------------");
		graph.topTenMutual(hillMutualFriends, hillNum, graph.myGraph[hillIndex]);
		
		System.out.println("\n------------------------------");
		System.out.println("TOP MUTUAL FRIENDS FOR ARCHER:");
		System.out.println("------------------------------");
		graph.topTenMutual(archerMutualFriends, archerNum, graph.myGraph[archerIndex]);
		
		System.out.println("\n------------------------------");
		System.out.println("TOP MUTUAL FRIENDS FOR COLVIN:");
		System.out.println("------------------------------");
		graph.topTenMutual(colvinMutualFriends, colvinNum, graph.myGraph[colvinIndex]);
		
		System.out.println("\n----------------");
		System.out.println("CHAINS OF USERS:");
		System.out.println("----------------");
		
		Vertex[] path = graph.followPathToStart(graph.myGraph[ryanIndex], "Spencer");
		System.out.println("");
		for(int i = path.length-1; i>0; i--){
			if(path[i]!=null){
			System.out.print(path[i].name+" -- ");
			}
		}
		System.out.print(graph.myGraph[spencerIndex].name);	
		
		path = graph.followPathToStart(graph.myGraph[jacobsIndex], "Henry");
		System.out.println("");
		for(int i = path.length-1; i>0; i--){
			if(path[i]!=null){
			System.out.print(path[i].name+" -- ");
			}
		}
		System.out.print(graph.myGraph[henryIndex].name);	
		
		path = graph.followPathToStart(graph.myGraph[pierceIndex], "Oliver");
		System.out.println("");
		for(int i = path.length-1; i>0; i--){
			if(path[i]!=null){
			System.out.print(path[i].name+" -- ");
			}
		}
		System.out.print(graph.myGraph[oliverIndex].name);	
		
		path = graph.followPathToStart(graph.myGraph[griffinIndex], "Hill");
		System.out.println("");
		for(int i = path.length-1; i>0; i--){
			if(path[i]!=null){
			System.out.print(path[i].name+" -- ");
			}
		}
		System.out.print(graph.myGraph[hillIndex].name);	
		
		path = graph.followPathToStart(graph.myGraph[archerIndex], "Colvin");
		System.out.println("");
		for(int i = path.length-1; i>0; i--){
			if(path[i]!=null){
			System.out.print(path[i].name+" -- ");
			}
		}
		System.out.print(graph.myGraph[colvinIndex].name);
	}
}
