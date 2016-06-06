package goeuro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class Solution {
	static String URL = "http://api.goeuro.com/api/v2/position/suggest/en/";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URL += args[0];
		System.out.println("URL=" + URL);
		ConnectionHandler conHandler = new ConnectionHandler();
		try {

			Gson g = new Gson();
			LocationObj[] obj = g.fromJson(conHandler.sendGet(URL), LocationObj[].class);
			StringBuilder sb = new StringBuilder();
			for (LocationObj s : obj) {
				sb.append(s._id + "," + s.name + "," + s.type + "," + s.geo_position.latitude + ","
						+ s.geo_position.longitude + "\n");
			}
			writeToFile(sb.toString(), args[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeToFile(String content, String Filename) {

		try {
			File file = new File(Filename + ".csv");

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("File written succesfully");

		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
}
