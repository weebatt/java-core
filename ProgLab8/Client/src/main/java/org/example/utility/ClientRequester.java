package org.example.utility;

import org.apache.commons.lang3.SerializationUtils;
import org.example.exceptions.ServerUnavailableException;
import org.example.models.StudyGroup;
import org.example.models.User;

import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static org.example.utility.ClientInvoker.clientInvoker;

public class ClientRequester implements Serializable {
    private String userName = "new User";
    private String passwd = "1234";
    private String userNameBackUp = "new User";
    private String passwdBackUp = "1234";

    private User user = new User(userName, passwd);
    public void sendRequest(String request, String username, String password, StudyGroup studyGroup) throws IOException, ClassNotFoundException, ServerUnavailableException {
        ByteBuffer buffer = clientInvoker.getBuffer();
        SocketChannel channel = clientInvoker.getSocketChannel();

        if (request.equalsIgnoreCase("exit")) {
            ClientCommandManager.clientCommands.get(request).executionForRequestVoid(null);
        }
        else if (request.equalsIgnoreCase("execute_script")) {
            clientInvoker.invoke(request);
        }
        else if (ClientCommandManager.clientCommandsContainsObject.contains(request) || ClientCommandManager.clientCommandsContainsValueAndObject.contains(request.split(" ")[0])) {
            if (request.equalsIgnoreCase("log_in") || request.equalsIgnoreCase("register")){
                userNameBackUp = userName;
                passwdBackUp = passwd;
                userName = username;
                passwd = password;
            }
            user.setUserName(userName);
            user.setPassword(passwd);

            sendCommandAndUser(channel, request);

            if (request.equalsIgnoreCase("register")){
                userName = userNameBackUp;
                passwd = passwdBackUp;
                user.setUserName(userNameBackUp);
                user.setPassword(passwdBackUp);
                System.out.println(user.getUserName());
            }
            if (!request.equalsIgnoreCase("log_in") && !request.equalsIgnoreCase("register")) {
                buffer.clear();
                buffer.put(SerializationUtils.serialize(studyGroup));
                buffer.flip();
                while (buffer.hasRemaining()) {
                    channel.write(buffer);
                }
            }
        } else {
            sendCommandAndUser(channel, request);
        }
    }

    public void sendCommandAndUser(SocketChannel channel, String request) throws IOException {
        byte[] commandBytes = request.getBytes();
        byte[] userBytes = SerializationUtils.serialize(user);

        ByteBuffer combinedBuffer = ByteBuffer.allocate(commandBytes.length + userBytes.length + Integer.BYTES);
        combinedBuffer.clear();
        combinedBuffer.putInt(commandBytes.length);
        combinedBuffer.put(commandBytes);
        combinedBuffer.put(userBytes);
        combinedBuffer.flip();

        while (combinedBuffer.hasRemaining()) {
            channel.write(combinedBuffer);
        }
    }

    public void setUser(User newUser){
        this.user = newUser;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName, String passwd) {
        this.userName = userName;
        this.passwd = passwd;
    }
}
