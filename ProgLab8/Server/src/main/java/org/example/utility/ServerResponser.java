package org.example.utility;

import org.example.models.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerResponser implements Runnable, Serializable {
    private final Socket clientSocket;
    private final Logger LOGGER;
    private final String request;
    private final String entryCommand;
    private final User user;
    private final ReentrantLock locker;
    private Object commandObject;

    public ServerResponser(Socket socket, Logger logger, ReentrantLock reentrantLock, String req, String command, User usr) {
        this.clientSocket = socket;
        this.LOGGER = logger;
        this.locker = reentrantLock;
        this.request = req;
        this.entryCommand = command;
        this.user = usr;
    }

    public ServerResponser(Socket socket, Logger logger, ReentrantLock reentrantLock, String req, String command, Object object, User usr) {
        this.clientSocket = socket;
        this.LOGGER = logger;
        this.locker = reentrantLock;
        this.request = req;
        this.entryCommand = command;
        this.commandObject = object;
        this.user = usr;
    }

    @Override
    public void run() {
        ServerInvoker serverInvoker = new ServerInvoker(locker);

        try {
            locker.lock();
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            if (ServerCommandManager.serverCommandsContainsObject.contains(entryCommand) ||
                    ServerCommandManager.serverCommandsContainsValueAndObject.contains(entryCommand)) {
                oos.writeObject(serverInvoker.invoke(request, commandObject, user));
                oos.flush();
            } else {
                oos.writeObject(serverInvoker.invoke(request, null, user));
                oos.flush();
            }
            LOGGER.log(Level.INFO, String.format("Server sent a response of command - %s to port %s\n", entryCommand, clientSocket.getPort()));
        } catch (IOException e) {
            throw new RuntimeException("Error during response handling", e);
        } finally {
            locker.unlock();
        }
    }
}
