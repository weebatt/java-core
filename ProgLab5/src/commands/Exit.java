package commands;

import models.Command;

/**
 * Команда 'exit'. Завершает работу программы.
 * @author butareyka
 */

public class Exit extends Command {

    public Exit(){
        super("exit", "завершить программу (без сохранения в файл)");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String value){
        System.exit(0);
    }
}
