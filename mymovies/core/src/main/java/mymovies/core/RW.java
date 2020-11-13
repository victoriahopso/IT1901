package mymovies.core;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

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

  public Writer createWriter(String userPath) {
    Path path = Paths.get(System.getProperty("user.home"), userPath);
    try {
      Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8);
      return writer;
    } catch (IOException e) {
      System.err.println("Klarte ikke å skrive til hjemmeområde ved " + userPath);
    }
    return null;
  }
}
