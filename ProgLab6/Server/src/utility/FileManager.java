package utility;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import models.*;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class FileManager implements FileMethods, Serializable{
    public String readFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try{
            FileInputStream inputStream = new FileInputStream(System.getenv("MYFILE"));
            InputStreamReader reader = new InputStreamReader(inputStream);

            int character;
            while ((character = reader.read()) != -1) {
                if (character != '\n' && character != '\r') {
                    stringBuilder.append((char) character);
                }
            }
        }
        catch (AccessDeniedException e){
            System.err.println("Ошибка доступа к файлу: " + e.getMessage());
        }
        catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        }
        return StringEscapeUtils.escapeJava(stringBuilder.toString());
    }

    public void parseCSVToCollection() {
        String[] lines;
        lines = readFile().split(",\\\\");
        lines = Arrays.toString(lines).split(", ");

        int i = 0;
        for (String str: lines){
            lines[i] = str.replace("\"", "");
            lines[i] = Arrays.toString(lines[i].split(": "));
            i++;
        }

        int j = 0;
        for(String str: lines){
            lines[j] = str.replaceAll("\\[ ", "");
            lines[j] = lines[j].replaceAll("\\[", "");
            lines[j] = lines[j].replaceAll("]", "");
            lines[j] = lines[j].replaceAll("\\\\", "");
            lines[j] = lines[j].replaceAll("\\w+, ", "");
            j += 1;
        }

        j = 0;
        while(j < lines.length){
            ServerCollectionManager.group.put(Long.parseLong(lines[j]), new StudyGroup(Long.parseLong(lines[j + 1]), lines[j + 2], LocalDateTime.parse(lines[j + 3]), new Coordinates().convertStringToCoordinates(lines[j + 4]), Integer.parseInt(lines[j + 5]), Long.parseLong(lines[j + 6]), Integer.parseInt(lines[j + 7]), FormOfEducation.valueOf(lines[j + 8]), new Person(lines[j + 9], Long.parseLong(lines[j + 10]), EyesColor.valueOf(lines[j + 11]), HairColor.valueOf(lines[j + 12]), Country.valueOf(lines[j + 13]), new Location(lines[j + 14], new Location().convertStringToCoordinatesX(lines[j + 15]), new Location().convertStringToCoordinatesY(lines[j + 15])))));
            j += 16;
        }
    }

    public void parseCollectionToCSV(String data) {
        try{
            FileOutputStream outputStream = new FileOutputStream(System.getenv("MYFILE"));

            StringWriter writer = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(writer);
            CSVReader csvReader = new CSVReader(new StringReader(data));

            List<String[]> csvData = csvReader.readAll();
            csvWriter.writeAll(csvData);
            csvWriter.close();

            String csvResult = writer.toString();
            byte[] bytes = csvResult.getBytes();
            outputStream.write(bytes);
        }
        catch (AccessDeniedException e){
            System.err.println("Ошибка доступа к файлу: " + e.getMessage());
        }
        catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
}