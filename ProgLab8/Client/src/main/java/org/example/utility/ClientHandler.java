package org.example.utility;

import org.example.exceptions.ServerUnavailableException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static org.example.utility.ClientInvoker.clientInvoker;

public class ClientHandler {
    public Object receiveResponse() throws IOException, ClassNotFoundException, ServerUnavailableException {
        SocketChannel channel = clientInvoker.getSocketChannel();
        ByteBuffer buffer = ByteBuffer.allocate(Integer.MAX_VALUE - 1000);
        int bytesRead = channel.read(buffer);
        if (bytesRead == -1) {
            throw new ServerUnavailableException();
        }
        buffer.flip();
        byte[] data = new byte[buffer.remaining()];
        buffer.get(data);
        Object response = deserializeObject(data);
        buffer.clear();
        System.out.println(response.getClass());
        return response;
    }

    private Object deserializeObject(byte[] data) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return ois.readObject();
        }
    }
}
