package org.example.utility;

import org.example.commands.ServerCommand;

public interface ServerCommandMethods {
    void registerServerCommands(String key, ServerCommand command);
    void registerServerCommandsContainsObject(String command);
    void registerServerCommandsContainsValue(String command);
}
