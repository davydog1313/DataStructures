import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FBReader {
	//Taken directly from my Project 2 reader, changed to work with Vertexes
	public Vertex[] loadFriendsToGraph(String fileName, int size){
		
		Vertex[] friendDB = new Vertex[size];

		int count = 0;
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufReader = new BufferedReader(fileReader);

			String line;

			while(bufReader.ready() && count < size) {
				
				line = bufReader.readLine();
				String[] dataTokens = line.split("\\s+");//split on spaces in the text
				if(dataTokens.length >= 2) {
					boolean makeNew = true;
					int friendIndex = 0;

					String head = dataTokens[0].replaceAll("﻿", "");//deletes the characters that appear before the first apartment ID
					String friend = dataTokens[1];
					for(int i = 0; i<count; i++){
						//checks if the person has already been added to the array
						if(friendDB[i].name.compareToIgnoreCase(head)==0){
							makeNew = false;
							friendIndex=i;
						}
					}
					if(makeNew){
						//if it's a new person, they are created and assigned friend as their first friend
						friendDB[count] = new Vertex(head);
						friendDB[count].next = friendDB[count].newAdjFriend(friend);
						count++;
					}
					else{
						//finds the end of the list of friends then adds the new friend to the end of the list
						Vertex temp = friendDB[friendIndex];
						while(temp.next != null){
							temp = temp.next;
						}
						temp.next = friendDB[friendIndex].newAdjFriend(friend);
					}

				}
			}
			bufReader.close();
			}
		catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Load friend number: " + count);
		return friendDB;
	}

}

