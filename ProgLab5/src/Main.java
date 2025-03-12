import commands.*;

import utility.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
     public static void main(String[] args) throws FileNotFoundException {
          System.out.println("Вы пользуетесь моим первым консольным приложением");

          System.out.println("Имеющиеся переменные окружения: \n" +
                  "MYFILE - csv файл \n" +
                  "FILE1 - первый файл скрипта \n" +
                  "FILE2 - второй файл скрипта \n" +
                  "FILE3 - третий файл скрипта");
          FileManager fileManager = new FileManager();
          Scanner scanner = new Scanner(new File(System.getenv("MYFILE")));

          if (scanner.hasNext()){
               fileManager.parseCSVToCollection();
          }
          CommandManager commandManager = new CommandManager() {{
               register("help", new Help());
               register("info", new Info());
               register("show", new Show());
               register("insert", new Insert());
               register("update", new Update());
               register("remove_key", new RemoveKey());
               register("clear", new Clear());
               register("save", new Save());
               register("execute_script", new ExecuteScript());
               register("exit", new Exit());
               register("remove_greater", new RemoveGreater());
               register("remove_lower", new RemoveLower());
               register("remove_greater_key", new RemoveGreaterKey());
               register("max_by_coordinates", new MaxByCoordinates());
               register("filter_contains_name", new FilterContainsName());
               register("print_field_descending_expelled_students", new PrintFieldDescendingExpelledStudents());
               register("newCommand", new NewCommand());
          }};
          new Invoker().invoke();
     }
}
