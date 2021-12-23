module com.sweng.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.configuration2;
    requires java.sql;
    opens gui.controller;
    requires java.sql.rowset;
    exports com.sweng.app;
    exports gui;
    exports gui.controller;
}
