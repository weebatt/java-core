    package utility;

    import models.StudyGroup;
    import models.User;
    import org.apache.commons.lang3.SerializationUtils;

    import java.util.concurrent.locks.ReentrantLock;
    import java.io.IOException;
    import java.net.Socket;
    import java.nio.ByteBuffer;
    import java.nio.charset.StandardCharsets;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.Collections;
    import java.util.List;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;
    import java.util.logging.Level;
    import java.util.logging.Logger;

    public class ServerHandler implements Runnable {
        private Socket clientSocket;
        private Logger LOGGER;
        private ReentrantLock locker;
        private User user;
        static ExecutorService ftp = Executors.newFixedThreadPool(10);

        public ServerHandler(Socket clientSocket, Logger logger, ReentrantLock reentrantLock) {
            this.clientSocket = clientSocket;
            this.LOGGER = logger;
            this.locker = reentrantLock;
        }

        public ServerHandler(){}

        @Override
        public void run() {

            try {
//                locker.lock();
                while (clientSocket.isConnected()){
                    byte[] lengthBytes = new byte[Integer.BYTES];
                    clientSocket.getInputStream().read(lengthBytes);
                    int commandLength = ByteBuffer.wrap(lengthBytes).getInt();
                    if (commandLength == -1){
                        LOGGER.log(Level.INFO, String.format("Get the end off buffer of port - %s\n", clientSocket.getPort()));
//                        Authorization.list.remove(user);
//                        System.out.println(Authorization.list);
//                        clientSocket.close();
                    } else {
                        byte[] commandBytes = new byte[commandLength];
                        clientSocket.getInputStream().read(commandBytes);
                        String request = new String(commandBytes, StandardCharsets.UTF_8);
                        String entryCommand = (String) requestHanding(request).get(0);

                        byte[] userBytes = new byte[4096]; // Здесь стоит использовать динамический подход к определению размера
                        int userBytesRead = clientSocket.getInputStream().read(userBytes);
                        User user = SerializationUtils.deserialize(Arrays.copyOf(userBytes, userBytesRead));

                        System.out.println(user);
                        LOGGER.log(Level.INFO, String.format("Server received a request - %s from port %s\n", request, clientSocket.getPort()));
                        if (ServerCommandManager.serverCommandsContainsObject.contains(entryCommand)) {
                            byte[] dataOutput = new byte[4096];
                            int bytesReadForObject = clientSocket.getInputStream().read(dataOutput);
                            Object commandObject = SerializationUtils.deserialize(Arrays.copyOf(dataOutput, bytesReadForObject));
                            LOGGER.log(Level.INFO, String.format("Server got request - %s with object - %s from user - %s\n", request, commandObject, user));
                            ftp.execute(new ServerResponser(clientSocket, LOGGER, locker, request, entryCommand, commandObject, user));
                        } else if (ServerCommandManager.serverCommandsContainsValueAndObject.contains(entryCommand)) {
                            byte[] dataOutput = new byte[4096];
                            int bytesReadForObject = clientSocket.getInputStream().read(dataOutput);
                            StudyGroup commandObject = SerializationUtils.deserialize(Arrays.copyOf(dataOutput, bytesReadForObject));
                            commandObject.setGroupId(Long.valueOf((String) requestHanding(request).get(1)));
                            LOGGER.log(Level.INFO, String.format("Server got request - %s with object - %s from user - %s\n", request, commandObject, user));
                            ftp.execute(new ServerResponser(clientSocket, LOGGER, locker, request, entryCommand, commandObject, user));
                        } else {
                            LOGGER.log(Level.INFO, String.format("Server got request - %s from user - %s\n", request, user));
                            ftp.execute(new ServerResponser(clientSocket, LOGGER, locker, request, entryCommand, user));
                        }
                    }
                }
                clientSocket.close();
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, String.format("IOException: %s: port - %s\n", e.getMessage(), clientSocket.getPort()));
            } finally {
//                locker.unlock();
            }
        }

        public Socket getClientSocket(){
            return clientSocket;
        }

        public List requestHanding(String request){
            List<String> list = new ArrayList<>();

            String[] bufferCommandAndValue;
            bufferCommandAndValue = request.split(" ");
            Collections.addAll(list, bufferCommandAndValue);
            return list;
        }
    }