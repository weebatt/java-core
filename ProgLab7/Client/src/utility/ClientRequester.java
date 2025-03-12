package utility;

import exceptions.ServerUnavailableException;
import models.StudyGroup;
import models.User;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import static utility.ClientInvoker.clientInvoker;

public class ClientRequester implements Serializable {
    private User user = new User("testUser", "1234");
    public void sendRequest(String request) throws IOException, ClassNotFoundException, ServerUnavailableException {
        ByteBuffer buffer = clientInvoker.getBuffer();
        SocketChannel channel = clientInvoker.getSocketChannel();

        if (request.equalsIgnoreCase("exit")) {
            ClientCommandManager.clientCommands.get(request).executionForRequestVoid(null);
        } else if (request.equalsIgnoreCase("execute_script")) {
            clientInvoker.invoke(request);
        } else if (ClientCommandManager.clientCommandsContainsObject.contains(request) || ClientCommandManager.clientCommandsContainsValueAndObject.contains(request.split(" ")[0])) {
            if (request.equalsIgnoreCase("log_in") || request.equalsIgnoreCase("register")){
                user = (User) clientInvoker.invoke(request);
            }
            sendCommandAndUser(channel, request);
            if (!request.equalsIgnoreCase("log_in") && !request.equalsIgnoreCase("register")) {
                buffer.clear();
                buffer.put(SerializationUtils.serialize((StudyGroup) clientInvoker.invoke(request)));
                buffer.flip();
                while (buffer.hasRemaining()) {
                    channel.write(buffer);
                }
            }
        } else {
            if (request.equalsIgnoreCase("quit")){
                user = new User("testUser", "1234");
            }
            sendCommandAndUser(channel, request);
        }
    }

    public void sendCommandAndUser(SocketChannel channel, String request) throws IOException {
        byte[] commandBytes = request.getBytes();
        byte[] userBytes = SerializationUtils.serialize(user);

        ByteBuffer combinedBuffer = ByteBuffer.allocate(commandBytes.length + userBytes.length + Integer.BYTES);
        combinedBuffer.putInt(commandBytes.length);
        combinedBuffer.put(commandBytes);
        combinedBuffer.put(userBytes);
        combinedBuffer.flip();

        while (combinedBuffer.hasRemaining()) {
            channel.write(combinedBuffer);
        }
    }

    public String makeRequest(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void setUser(User newUser){
        this.user = newUser;
    }
}
