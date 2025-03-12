package org.example.commands;

import org.example.exceptions.ServerUnavailableException;
import org.example.utility.ClientHandler;
import org.example.utility.ClientRequester;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static org.example.utility.ClientInvoker.clientInvoker;

public class ExecuteScript extends ClientCommand implements Serializable {
    public static List<String> list = new ArrayList<>();

    @Override
    public Object executionForRequestReturn(Object value){
        String valueStr = (String) value;
        String[] commandAndValue = valueStr.split(" ");
        String fileName = commandAndValue[1];

        ByteBuffer buffer = clientInvoker.getBuffer();
        SocketChannel channel = clientInvoker.getSocketChannel();

        ClientHandler clientHandler = new ClientHandler();
        ClientRequester clientRequester = new ClientRequester();

        try {
            String filePath = System.getenv(fileName);
            if (filePath == null) {
                throw new IllegalArgumentException("Environment variable not set: " + fileName);
            }

            Scanner scannerFile = new Scanner(new File(filePath));
            while (scannerFile.hasNext()) {
                String request = scannerFile.nextLine();
                if (Objects.equals(request.split(" ")[0], "execute_script")) {
                    list.add(fileName);
                    if (list.contains(fileName)){
                        int counter = 0;
                        for (String item: list){
                            if (Objects.equals(item, fileName)){
                                counter++;
                            }
                        }
                        if (counter > 4){
                            System.out.println("Suspicion of recursion detected!\n");
                            buffer.clear();
                            buffer.put(clientInvoker.invoke(nextCommand()).toString().getBytes());
                            buffer.flip();
                            while (buffer.hasRemaining()) {
                                channel.write(buffer);
                            }
                            scannerFile.close();
                            break;
                        }
                    }
                    clientInvoker.invoke(request);
                } else {
                    if (!request.contains("execute_script")){
                        clientRequester.sendRequest(request, null, null, null);
                        clientHandler.receiveResponse();
                    }
                }
            }
            if (!scannerFile.hasNext()){
                buffer.clear();
                buffer.put(clientInvoker.invoke(nextCommand()).toString().getBytes());
                buffer.flip();
                while (buffer.hasRemaining()) {
                    channel.write(buffer);
                }
            }
        } catch (IOException | ClassNotFoundException | ServerUnavailableException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String nextCommand(){
        System.out.println("Print next command: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
