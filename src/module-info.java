module ProjetSARAS {
	requires javafx.controls;
	requires java.sql;
	requires java.rmi;
	requires javafx.fxml;
	requires javafx.graphics;
	exports entitiesInterface;
	opens application to javafx.graphics, javafx.fxml;
	opens entities to javafx.base;
	opens entitiesInterfaceImplementation to javafx.base;
	opens entitiesInterface to javafx.base;
}
