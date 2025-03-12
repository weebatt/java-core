package utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServerInvoker implements Serializable {
    public static List<String> list = new ArrayList<>();
    ServerHandler serverHandler = new ServerHandler();

    public String invoke(String request, Object object) {
        String entryCommand = (String) serverHandler.requestHanding(request).get(0);

        if (entryCommand.equalsIgnoreCase("quit")) {
            ServerCommandManager.serverCommands.get("save").executionForResponse(null);
            return "Server reply - " + entryCommand + " - OK \nChanges from this session were successfully saved!";
        }
        else if (entryCommand.equalsIgnoreCase("save")) {
            return "Client does not have access rights to use the save collection command";
        }
        else if (!ServerCommandManager.serverCommands.containsKey(entryCommand)) {
            return "Command doesn't exist - " + entryCommand + " - OK\n";
        }
        else if (ServerCommandManager.serverCommandsContainsValue.contains(entryCommand)) {
            String entryValue = (String) serverHandler.requestHanding(request).get(1);
            return "The server responds to the command: \n" + ServerCommandManager.serverCommands.get(entryCommand).executionForResponse(entryValue) + "\nServer Wrote message to client";
        }
        else if (ServerCommandManager.serverCommandsContainsObject.contains(entryCommand)) {
            return "The server responds to the command: \n" + ServerCommandManager.serverCommands.get(entryCommand).executionForResponse(object) + "\nServer Wrote message to client";
        }
        else if (ServerCommandManager.serverCommandsContainsValueAndObject.contains(entryCommand)){
            return "The server responds to the command: \n" + ServerCommandManager.serverCommands.get(entryCommand).executionForResponse(object) + "\nServer Wrote message to client";

        } else {
            return "The server responds to the command: \n" + ServerCommandManager.serverCommands.get(entryCommand).executionForResponse(null) + "\nServer Wrote message to client";
        }
    }
}