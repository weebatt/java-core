package commands;

import models.User;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class ClientRegister extends ClientCommand implements Serializable {
    private int counter = 0;
    @Override
    public Object executionForRequestReturn(Object object){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter new user's name:");
        String userName = scanner.next();
        Terminal terminal = null;
        try {
            terminal = TerminalBuilder.builder().system(true).build();
        } catch (IOException e) {
            System.out.println("IOException in builder by jline" + e.getMessage());
        }
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();
        String password = reader.readLine("Enter new user's password:\n", '*');
        String passwordAgain = reader.readLine("Enter new user's password again:\n", '*');

        if (Objects.equals(passwordAgain, password)) {
            return new User(userName, password);
        } else {
            counter++;
            if (counter < 3) {
                System.out.println("Passwords do not match. Please try again.");
                return executionForRequestReturn(null);
            } else {
                System.out.println("Do you want to register a new user? Yes or No");
                String ans = scanner.next();
                if (ans.equalsIgnoreCase("yes")) {
                    return new ClientRegister().executionForRequestReturn(null);
                } else {
                    counter = 0;
                    return executionForRequestReturn(null);
                }
            }
        }
    }
}
