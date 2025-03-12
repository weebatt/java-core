package utility;

import commands.ServerCommand;

public interface ServerCommandMethods {
    void registerServerCommands(String key, ServerCommand command);
    void registerServerCommandsContainsObject(String command);
    void registerServerCommandsContainsValue(String command);
}
