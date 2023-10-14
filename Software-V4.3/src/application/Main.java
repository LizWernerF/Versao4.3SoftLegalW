package application;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Logger;

import gui8.Tela1_control;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

	public static final Logger logger = Logger.getLogger(Main.class.getName());
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_CONNECTION = "jdbc:mysql://localhost:3305/clienteswerneradv";
	public static final String DB_USER = "username1";
	public static final String DB_PASSWORD = "LizWerner01-";

	@Override
	public void start(Stage stage) {

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/gui8/Tela1.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("/gui8/Tela1.fxml"));
			Scene scene = new Scene(parent);
			System.out.println("Iniciando");
			
			// Adicione a imagem na barra de título
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/gui8/Imagens/LOGO.png")));
            stage.setTitle("Werner Advogados");
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("/gui8/styles.css").toExternalForm());
            
            
			Stage primaryStage;
			stage.setScene(scene);

			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		launch(args);
	}

}
