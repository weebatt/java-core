//package utility;
//
//import exceptions.ServerUnavailableException;
//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.nio.ByteBuffer;
//import java.nio.channels.SocketChannel;
//import java.nio.charset.StandardCharsets;
//
//import static utility.ClientInvoker.clientInvoker;
//
//public class ClientHandler {
//    public Object receiveResponse() throws IOException, ClassNotFoundException, ServerUnavailableException {
//        ObjectInputStream ios = new ObjectInputStream(clientInvoker.getSocketChannel().socket().getInputStream());
//        Object response = ios.readObject();
////        SocketChannel channel = clientInvoker.getSocketChannel();
////        ByteBuffer buffer = ByteBuffer.allocate(4096);
////        int bytesRead = channel.read(buffer);
////        if (bytesRead == -1) {
////            throw new ServerUnavailableException();
////        }
////        buffer.rewind();
////        String response = StandardCharsets.UTF_8.decode(buffer).toString().trim();
////        buffer.clear();
//        return response;
//    }
//
//    public String responseProcessing(String in){
//        String out;
//        if (in.contains("\"")){
//            out = in.replace("\", ", "}\n{");
//
//        } else {
//            out = in.replace("., ", ",\n");
//            out = out.replace("{", "");
//            out = out.replace("}", "");
//        }
//        return out;
//    }
//}

package utility;
import exceptions.ServerUnavailableException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import static utility.ClientInvoker.clientInvoker;

public class ClientHandler {
    public Object receiveResponse() throws IOException, ClassNotFoundException, ServerUnavailableException {
        SocketChannel channel = clientInvoker.getSocketChannel();
        ByteBuffer buffer = ByteBuffer.allocate(4096);
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

//    public String responseProcessing(String in){
//        String out;
//        if (in.contains("\"")){
//            out = in.replace("\", ", "}\n{");
//        } else {
//            out = in.replace("., ", ",\n");
//            out = out.replace("{", "");
//            out = out.replace("}", "");
//        }
//        return out;
//    }
}
