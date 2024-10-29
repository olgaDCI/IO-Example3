import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSearcher {
  private static final Path ROOT_FOLDER = Paths.get("data");

  public static void main(String[] args) {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      System.out.print("Enter the file name to search for: ");
      String fileName = reader.readLine().trim();

      FileSearcherVisitor visitor = new FileSearcherVisitor(fileName, ROOT_FOLDER);
      Files.walkFileTree(ROOT_FOLDER, visitor);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
