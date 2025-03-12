module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.apache.commons.lang3;
    requires org.jline.reader;
    requires org.jline.terminal;

    opens org.example to javafx.fxml, javafx.media, java.base, javafx.controls, javafx.graphics;
    opens org.example.stages to javafx.fxml, javafx.media, java.base, javafx.controls, javafx.graphics;
    opens org.example.models to java.base;
    exports org.example;
}
