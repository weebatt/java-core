package utility;

import commands.ClientCommand;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClientCommandManager {
    public static Map<String, ClientCommand> clientCommands = new LinkedHashMap<>();
    public static HashSet<String> clientCommandsContainsObject = new HashSet<>();
    public static HashSet<String> clientCommandsContainsValueAndObject = new HashSet<>();

    public void register(String key, ClientCommand command){
        clientCommands.put(key, command);
    }
    public void registerClientCommandsContainsObject(String commandName) {
        clientCommandsContainsObject.add(commandName);
    }
    public void registerClientCommandsContainsValueAndObject(String commandName) {
        clientCommandsContainsValueAndObject.add(commandName);
    }
}
