import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class importer {
	//reads in information from external file and returns it as a connected network
	public Graph ReadNetwork(String filename, int datanum){
		Graph network = new Graph();
		try{
			FileReader file_reader = new FileReader(new File(filename));
			BufferedReader buf_reader = new BufferedReader(file_reader);
			String line = "";
			int count = 0;
			while((line = buf_reader.readLine()) != null && count<datanum){
				String[] data = line.split("\t"); // in the input file, attributes are separated by "\t"
				
				String name = data[0];
				String connection = data[1];
				//if we havent seen these names before we make new vertexes
				if(network.isNew(name)){
					network.newVertex(name);
				}
				if(network.isNew(connection)){
					network.newVertex(connection);
				}
				//we then connect these vertexes
				network.addAdjVertex(name, connection);
				network.addAdjVertex(connection, name);
				count++;
				//provides a progress bar for larger input sizes
				if(count%(datanum/10) == 0){
					int percent = 10*(count/(datanum/10));
					System.out.print("..." + percent +"%");
				}
			}
			buf_reader.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		System.out.println();
		return network;
	}//end ReadNetwork
	
	//reads in the file for the find_friends.txt, returns it as an array of names
	public String[] ReadFinder(String filename, int datanum){
		String[] friends = new String[10];

		try{
			FileReader file_reader = new FileReader(new File(filename));
			BufferedReader buf_reader = new BufferedReader(file_reader);
			String line = "";
			int count = 0;
			int position=0;
			while((line = buf_reader.readLine()) != null && count<datanum){
				String[] data = line.split("\t"); // in the input file, attributes are separated by ","

				String name = data[0];
				String connection = data[1];
				friends[position] = name;
				position++;
				friends[position] = connection;
				position++;
				count++;
			}
			buf_reader.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return friends;
	}//end ReadFinder
}//end importer








