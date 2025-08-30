module com.example.journeythroughfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;
    requires java.desktop;


    opens com.example.journeythroughfx to javafx.fxml;
    exports com.example.journeythroughfx;
}