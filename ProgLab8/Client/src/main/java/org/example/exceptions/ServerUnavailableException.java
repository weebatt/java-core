package org.example.exceptions;

public class ServerUnavailableException extends Exception {
    public ServerUnavailableException(){super("Server was restarted or temporarily unavailable. You need to reconnecting!");}
}
