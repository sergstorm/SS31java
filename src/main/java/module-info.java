module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;
    requires org.postgresql.jdbc;
    //requires com.example.demo;
    //requires com.example.demo;
    // requires com.example.demo;
    // requires com.example.demo;
    // requires com.example.demo;
    // requires com.example.demo;
    //requires com.example.demo;

    opens com.example.demo.Controllers to javafx.fxml;

    exports com.example.demo.Personas;
    opens com.example.demo.Personas to javafx.base, javafx.fxml;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.Controllers;
   // opens com.example.demo.Controllers to javafx.fxml;



    opens com.example.demo.BaseData to javafx.base;
}

