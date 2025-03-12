package org.example.commands;

public class Exit extends ClientCommand {

    @Override
    public void executionForRequestVoid(Object value){
        System.exit(0);
    }
}

