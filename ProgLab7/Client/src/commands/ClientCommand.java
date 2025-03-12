package commands;

import java.io.Serializable;

public abstract class ClientCommand implements Serializable {

    public Object executionForRequestReturn(Object object){
        return null;
    }
    public void executionForRequestVoid(Object object){}
}
