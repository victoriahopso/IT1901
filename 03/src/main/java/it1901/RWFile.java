package it1901;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RWFile {
    
    //Read objects from file
	public Object fileReader(String streng, Object objekt) throws IOException {
		try {
			FileInputStream f = new FileInputStream(streng);
			ObjectInputStream o = new ObjectInputStream(f);
			Object object = (Object) o.readObject();
			o.close();
			f.close();
			return object;
			
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
			
		} catch (IOException e) {
			System.out.println("Error initializing stream");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}
		return objekt;
	}
    
    // Write objects to file
	public void fileWriter(String streng, Object objekt) throws FileNotFoundException{
		try {
			FileOutputStream f = new FileOutputStream(streng);
			ObjectOutputStream o = new ObjectOutputStream(f);

			o.writeObject(objekt);
			o.close();
			f.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}
}