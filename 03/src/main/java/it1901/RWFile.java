package it1901;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class RWFile {

    public void save(String streng) {
        File saveFile = new File("movies.txt");
        try {
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
            FileWriter writer = new FileWriter(saveFile, true);
            BufferedWriter buffWrite = new BufferedWriter(writer);
            buffWrite.write(streng + "\n");
            buffWrite.close();
        } catch (IOException e) {
            System.out.println("kunne ikke lagre");
        }
    }

    public String load(){
        File fil = new File ("movies.txt");
		String total = "";
		try {
			Scanner loadFile = new Scanner (fil);
			while (loadFile.hasNextLine()) {
                String line = loadFile.nextLine();
                total+=line+"\n";
            }
        loadFile.close();
        }
        catch  (Exception e){
            //give some feedback. 
        }
        return total;
    }
}
