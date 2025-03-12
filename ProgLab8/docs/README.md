#### Проброс порта для БД

```bash
ssh -p 2222 -l s408116 -L5432:localhost:5432 helios.cs.ifmo.ru
```

```bash
psql -h localhost -d studs
```

#### Переменные окружения и запуск сервера + клиента
```bash
export PROP=/Users/macbook/Programming/ProgLab8/configs/properties.cfg
export URL_DB=jdbc:postgresql://localhost:5432/studs
```

```bash
java -jar Server/target/Server-1.0-SNAPSHOT-jar-with-dependencies.jar
java -jar Client/target/Client-1.0-SNAPSHOT-jar-with-dependencies.jar
```

#### 
```bash
alias runclient='java --module-path /Users/macbook/Downloads/javafx-sdk-23/lib --add-modules javafx.controls,javafx.fxml,javafx.base,javafx.graphics -jar Client/target/Client-1.0-SNAPSHOT-jar-with-dependencies.jar'
export PROP=/Users/macbook/Programming/ProgLab8/configs/properties.cfg
export URL_DB=jdbc:postgresql://localhost:5432/studs
```


