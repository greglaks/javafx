package application;
	
import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.getIcons().add(Util.getOnlineImage());
			AnchorPane anchorPane = new AnchorPane();
			primaryStage.setMinWidth(980);
			primaryStage.setMinHeight(550);
			primaryStage.setWidth(980);
			primaryStage.setHeight(550);
			primaryStage.setTitle("PRIPOJENO - Inovio365");
			Scene scene = new Scene(anchorPane);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			TabPane tabPane = new TabPane();	
			
			AnchorPane.setTopAnchor(tabPane, 0.0);
			AnchorPane.setLeftAnchor(tabPane, 0.0);
			AnchorPane.setRightAnchor(tabPane, 0.0);
			AnchorPane.setBottomAnchor(tabPane, 0.0);
			anchorPane.getChildren().add(tabPane);
			
			Tab tab1 = new SendingTab("Incoming Document");
			tabPane.getTabs().add(tab1);
			
			Tab tab2 = new SendedTab("Outcoming Document");
			tabPane.getTabs().add(tab2);
			
			Tab tab3 = new DownloadTab("Backup from server");
			tabPane.getTabs().add(tab3);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private Tab createTab2(String text) {
		Tab tab = new Tab(text);

		return tab;
	}

	public static void main(String[] args) {
		launch(args);
		
	}




}
