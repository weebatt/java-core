package utility;

import models.Command;

/**
 * Интерфейс хранящий методы CommandManager.
 * @author butareyka
 */
public interface CommandMethods {
    void register(String key, Command command);
}
