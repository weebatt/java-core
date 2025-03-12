package utility;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientInvoker {
    static private ByteBuffer buffer;
    static private SocketChannel channel;
    public static final ClientInvoker clientInvoker = new ClientInvoker();


    public void setBuffer(ByteBuffer byteBuffer){
        buffer = byteBuffer;
    }

    public void setSocketChannel(SocketChannel socketChannel){
        channel = socketChannel;
    }

    public ByteBuffer getBuffer(){
        return buffer;
    }

    public SocketChannel getSocketChannel(){
        return channel;
    }

    public Object invoke(String request){
        if (ClientCommandManager.clientCommandsContainsObject.contains(request)){
            return ClientCommandManager.clientCommands.get("insertObject").executionForRequestReturn(request);
        } else if (ClientCommandManager.clientCommandsContainsValueAndObject.contains(request.split(" ")[0])) {
            return ClientCommandManager.clientCommands.get("insertObject").executionForRequestReturn(request);
        } else if (request.contains("execute_script")) {
            return ClientCommandManager.clientCommands.get("execute_script").executionForRequestReturn(request);
        } else{
            return request;
        }
    }
}
