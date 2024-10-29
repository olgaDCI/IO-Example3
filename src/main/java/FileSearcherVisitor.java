import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;


public class FileSearcherVisitor extends SimpleFileVisitor<Path> {
    private final String fileName;
    private final Path startDir;
    private boolean fileFound = false;

    public FileSearcherVisitor(String fileName, Path startDir) {
        this.fileName = fileName;
        this.startDir = startDir;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, java.nio.file.attribute.BasicFileAttributes attrs) {
        // Visit all directories
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, java.nio.file.attribute.BasicFileAttributes attrs) throws IOException {
        String currentFileName = file.getFileName().toString();
        if (currentFileName.equals(fileName)) {
            System.out.println("File found: " + file.toAbsolutePath());
            fileFound = true;
            return FileVisitResult.TERMINATE; // Stop searching
        }
        return FileVisitResult.CONTINUE; // Continue searching
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Failed to access file: " + file.toAbsolutePath());
        return FileVisitResult.CONTINUE; // Continue searching
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        if (Files.isSameFile(dir, startDir) && !fileFound) {
            System.out.println("Search complete. File '" + fileName + "' not found.");
        }
        return FileVisitResult.CONTINUE; // Continue searching
    }
}
