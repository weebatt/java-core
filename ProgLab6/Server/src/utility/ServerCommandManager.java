package utility;

import commands.ServerCommand;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class ServerCommandManager implements ServerCommandMethods, Serializable {
    public static Map<String, ServerCommand> serverCommands = new LinkedHashMap<>();
    public static HashSet<String> serverCommandsContainsObject = new HashSet<>();
    public static HashSet<String> serverCommandsContainsValue = new HashSet<>();
    public static HashSet<String> serverCommandsContainsValueAndObject = new HashSet<>();

    public void registerServerCommands(String key, ServerCommand command){
        serverCommands.put(key, command);
    }
    public void registerServerCommandsContainsObject(String command){
        serverCommandsContainsObject.add(command);
    }
    public void registerServerCommandsContainsValue(String command){
        serverCommandsContainsValue.add(command);
    }
    public void registerServerCommandsContainsValueAndObject(String command){
        serverCommandsContainsValueAndObject.add(command);
    }
}

