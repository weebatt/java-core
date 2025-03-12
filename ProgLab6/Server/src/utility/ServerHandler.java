package utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServerHandler {
    public List requestHanding(String request){
        List<String> list = new ArrayList<>();

        String[] bufferCommandAndValue;
        bufferCommandAndValue = request.split(" ");
        Collections.addAll(list, bufferCommandAndValue);
        return list;
    }
}
