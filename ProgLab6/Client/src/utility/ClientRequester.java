package utility;

import exceptions.ServerUnavailableException;

import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import static utility.ClientInvoker.clientInvoker;

public class ClientRequester implements Serializable {
    public void sendRequest(String request) throws IOException, ClassNotFoundException, ServerUnavailableException {
        ByteBuffer buffer = clientInvoker.getBuffer();
        SocketChannel channel = clientInvoker.getSocketChannel();

        if (request.contains("exit")) {
            ClientCommandManager.clientCommands.get(request).executionForRequestVoid(null);
        } else if (request.contains("execute_script")) {
            clientInvoker.invoke(request);
        } else if (ClientCommandManager.clientCommandsContainsObject.contains(request) || ClientCommandManager.clientCommandsContainsValueAndObject.contains(request.split(" ")[0])) {
            buffer.clear();
            buffer.put(request.getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }
            buffer.clear();
            buffer.put((byte[]) clientInvoker.invoke(request));
            buffer.flip();
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }
        } else {
            buffer.clear();
            buffer.put(request.getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }
        }
    }

    public String makeRequest(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
