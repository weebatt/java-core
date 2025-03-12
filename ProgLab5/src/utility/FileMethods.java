package utility;

/**
 * Интерфейс хранящий методы FileManager.
 * @author butareyka
 */
public interface FileMethods {
    String readFile();
    void convertStringToCollection();
    void parseCSVToCollection();
    void parseCollectionToCSV(String data);
}
