package commands;

import models.Command;
import utility.Invoker;

/**
 * Команда 'execute_script'. Выполняет скрипт.
 * @author butareyka
 */

public class ExecuteScript extends Command {
    public ExecuteScript(){
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }

    /**
     * Выполняет команду
     */
    @Override
    public void executionResponse(String fileName){
        Invoker invoker = new Invoker();
        invoker.invokeScript(fileName);
    }
}
