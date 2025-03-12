package org.example.utility;

import org.example.models.User;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ServerInvoker implements Serializable {
    public static List<String> list = new ArrayList<>();
    ServerHandler serverHandler = new ServerHandler();
    private ReentrantLock locker;

    public ServerInvoker(ReentrantLock reentrantLock) {
        this.locker = reentrantLock;
    }
    public Object invoke(String request, Object object, User user) throws IOException {
        try {
            locker.lock();
            String entryCommand = (String) serverHandler.requestHanding(request).get(0);
            if (!ServerCommandManager.serverCommands.containsKey(entryCommand)) {
                return "no " + entryCommand;
            }
            else if (ServerCommandManager.serverCommandsContainsValue.contains(entryCommand)) {
                String entryValue = (String) serverHandler.requestHanding(request).get(1);
                return ServerCommandManager.serverCommands.get(entryCommand).executionForResponse(entryValue, user);
            }
            else if (ServerCommandManager.serverCommandsContainsObject.contains(entryCommand)) {
                return ServerCommandManager.serverCommands.get(entryCommand).executionForResponse(object, user);
            }
            else if (ServerCommandManager.serverCommandsContainsValueAndObject.contains(entryCommand)) {
                return ServerCommandManager.serverCommands.get(entryCommand).executionForResponse(object, user);
            }
            else {
                return ServerCommandManager.serverCommands.get(entryCommand).executionForResponse(null, user);
            }
        } catch (SQLException e){
            return "Error was threw by inserting in StudyGroup, if i'm correct " + e.getMessage();
        }
        finally {
            locker.unlock();
        }
    }
}