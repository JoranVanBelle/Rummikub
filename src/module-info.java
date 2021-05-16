module module.info {
	exports persistentie;
	exports ui;
	exports gui;
	exports main;
	exports domein;
	exports testen;
	exports exceptions;

	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires org.junit.jupiter.api;
	
	opens gui;
}