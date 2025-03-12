package utility;

import models.StudyGroup;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAndRequestsProcessor implements Runnable {
    private final Socket clientSocket;
    private final Logger LOGGER;
    ServerHandler serverHandler = new ServerHandler();
    ServerInvoker invoker = new ServerInvoker();

    public DataAndRequestsProcessor(Socket clientSocket, Logger logger) {
        this.clientSocket = clientSocket;
        this.LOGGER = logger;
    }

    @Override
    public void run() {
        try {
            while (true) {
                LOGGER.log(Level.INFO, String.format("Connection established with client %s\n", clientSocket.getInetAddress().getHostName()));
                while (clientSocket.isConnected()) {
                    byte[] data = new byte[4096];
                    int bytesRead = clientSocket.getInputStream().read(data);
                    ByteBuffer buffer = ByteBuffer.wrap(data, 0, bytesRead);

                    buffer.rewind();
                    String request = StandardCharsets.UTF_8.decode(buffer).toString().trim();
                    buffer.clear();

                    String entryCommand = (String) serverHandler.requestHanding(request).get(0);
                    LOGGER.log(Level.INFO, String.format("Server received a request - %s from client %s\n", request, clientSocket.getInetAddress().getHostName()));

                    if (entryCommand.equalsIgnoreCase("quit")) {
                        clientSocket.getOutputStream().write(invoker.invoke(request, null).getBytes());
                        clientSocket.getOutputStream().flush();
                        LOGGER.log(Level.INFO, String.format("Server sent a response of command - %s to client %s\n", entryCommand, clientSocket.getInetAddress().getHostName()));
                        break;
                    }
                    else if (ServerCommandManager.serverCommandsContainsObject.contains(entryCommand)) {
                        byte[] dataOutput = new byte[4096];
                        int bytesReadForObject = clientSocket.getInputStream().read(dataOutput);
                        StudyGroup entryStudyGroup = SerializationUtils.deserialize(Arrays.copyOf(dataOutput, bytesReadForObject));
                        LOGGER.log(Level.INFO, String.format("Server got request - %s with object - %s\n", entryCommand, entryStudyGroup));
                        clientSocket.getOutputStream().write(invoker.invoke(request, entryStudyGroup).getBytes());
                        clientSocket.getOutputStream().flush();
                        LOGGER.log(Level.INFO, String.format("Server sent a response of command - %s to client %s\n", entryCommand, clientSocket.getInetAddress().getHostName()));
                    }
                    else if (ServerCommandManager.serverCommandsContainsValueAndObject.contains(entryCommand)){
                        byte[] dataOutput = new byte[4096];
                        int bytesReadForObject = clientSocket.getInputStream().read(dataOutput);
                        StudyGroup entryStudyGroup = SerializationUtils.deserialize(Arrays.copyOf(dataOutput, bytesReadForObject));
                        entryStudyGroup.setGroupId(Long.valueOf((String) serverHandler.requestHanding(request).get(1)));
                        System.out.println(entryStudyGroup);
                        LOGGER.log(Level.INFO, String.format("Server got request - %s with object - %s\n", request, entryStudyGroup));
                        clientSocket.getOutputStream().write(invoker.invoke(request, entryStudyGroup).getBytes());
                        clientSocket.getOutputStream().flush();
                        LOGGER.log(Level.INFO, String.format("Server sent a response of command - %s to client %s\n", entryCommand, clientSocket.getInetAddress().getHostName()));
                    }
                    else {
                        LOGGER.log(Level.INFO, String.format("Server got request with argument - %s\n", request));
                        clientSocket.getOutputStream().write(invoker.invoke(request, null).getBytes());
                        clientSocket.getOutputStream().flush();
                        LOGGER.log(Level.INFO, String.format("Server sent a response of command - %s to client %s\n", entryCommand, clientSocket.getInetAddress().getHostName()));
                    }
                }
                clientSocket.close();
                LOGGER.log(Level.INFO, String.format("Connection closed for %s\n", clientSocket.getInetAddress().getHostName()));
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, String.format("IOException: %s\n", e.getMessage()));
        }
    }
}
