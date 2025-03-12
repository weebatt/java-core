package utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Класс отвечающий за выполнение команд и выполнение скрипта.
 * @author butareyka
 */
public class Invoker {
    public static List<String> list = new ArrayList<>();

    public void invoke(){
        Scanner scanner = new Scanner(System.in);
        String commandAndValue;
        while (true) {
            commandAndValue = scanner.nextLine().trim();
            String saveCommandAndValue = commandAndValue;
            String command = commandAndValue.replaceAll(" .*", "");
            String value = saveCommandAndValue.replaceAll("\\w* ", "");
            if (!CommandManager.commands.containsKey(command)) {
                System.out.println("Такой команды нет");
            }
            else {
                CommandManager.commands.get(command).executionResponse(value);
            }
        }
    }

    public void invokeScript(String fileName){
        try {
            String filePath = System.getenv(fileName);
            if (filePath == null) {
                throw new IllegalArgumentException("Переменная окружения не установлена: " + fileName);
            }
            Scanner scannerFile = new Scanner(new File(filePath));
            while (scannerFile.hasNext()){
                String commandAndValue = scannerFile.nextLine();
                String command = commandAndValue.replaceAll(" .*", "");
                String value = commandAndValue.replaceAll("\\w* ", "");
                if (CommandManager.commands.containsKey(command)) {
                    if (Objects.equals(command, "execute_script")) {
                        list.add(value);
                        if (list.contains(value)){
                            int counter = 0;
                            for (String item: list){
                                if (Objects.equals(item, value)){
                                    counter++;
                                }
                            }
                            if (counter > 4){
                                CommandManager.commands.get("newCommand").executionResponse("");
                            }
                        }
                        scannerFile.close();
                    }
                    System.out.println(commandAndValue);
                    CommandManager.commands.get(command).executionResponse(value);
                }
                else {
                    System.out.println("Такой команды нет");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
