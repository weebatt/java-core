package utility;

import models.Command;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс хранящий регистрирующий элементы коллекции команд, добавляет команды.
 * @author butareyka
 */
public class CommandManager implements CommandMethods{
    public static Map<String, Command> commands = new LinkedHashMap<>();

    public void register(String key, Command command){
        commands.put(key, command);
    }
}
