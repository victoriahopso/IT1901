package mymovies.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class RW {

  public InputStreamReader createReader(String file) {
    try {
      InputStream inputStream = new FileInputStream(file);
      InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
      return reader;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public OutputStreamWriter createWriter(String file) {
    try {
      FileOutputStream fileStream = new FileOutputStream(file);
      OutputStreamWriter writer = new OutputStreamWriter(fileStream, "UTF-8");
      return writer;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    return null;
  }
}
