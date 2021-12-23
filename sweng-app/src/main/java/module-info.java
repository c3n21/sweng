module com.sweng.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.configuration2;
    // requires org.apache.commons.configuration2.builder.fluent;
    // requires org.apache.commons.configuration2.ex;
    requires java.sql;
    opens gui.controller;
    exports com.sweng.app;
    exports gui;
    exports gui.controller;
}
