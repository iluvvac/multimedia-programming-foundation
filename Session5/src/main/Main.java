package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	BorderPane bp;
	GridPane gp;
	Label title, userLabel, passLabel,confirmPassLabel,errorLabel;
	TextField userField;
	PasswordField passField, confirmPassField;
	Button regisBtn;
	
	public void init() {
		title = new Label("Register Form");
		userLabel= new Label("Username");
		passLabel = new Label("Password");
		confirmPassLabel = new Label("Confirm Password");
		userField = new TextField();
		passField = new PasswordField();
		confirmPassField = new PasswordField();
		errorLabel = new Label("");
		regisBtn = new Button("Register Now!");
		bp = new BorderPane();
		gp = new GridPane();
		
	}
	
	public void Layouting() {
		bp.setCenter(gp);
		gp.add(userLabel, 0, 1);
		gp.add(userField, 1, 1);
		gp.add(passLabel, 0, 2);
		gp.add(passField, 1, 2);
		gp.add(confirmPassLabel, 0, 3);
		gp.add(confirmPassField, 1, 3);
		gp.add(regisBtn, 0, 4);
		gp.add(errorLabel, 1, 4);
		
		bp.setTop(title);
		
		BorderPane.setAlignment(title, Pos.CENTER);
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(50);
		gp.setVgap(50);
//		Layout 30
//		Component 20
//		Logic 50
//		Inline CSS
		regisBtn.setStyle("-fx-background-color:green;");
//		External CSS
		gp.setId("buttongrey");
		
	}
	
	public void handleListener() {
		regisBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
//			set validasi
			String usernameInput = userField.getText();
			String passwordInput = passField.getText();
			String confirmPassword = confirmPassField.getText();
			
			if (usernameInput.isEmpty()) {
				errorLabel.setText("Username is Empty");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Username is kosong");
				alert.showAndWait();
			}
			if (!passwordInput.equals(confirmPassword)) {
				errorLabel.setText("password and confirm password is not same");
			}
			if (!usernameInput.isEmpty()&&passwordInput.equals(confirmPassword)) {
				errorLabel.setText("Selamat mendaftar");
			}
		});
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method st0ub
		init();
		Layouting();
		handleListener();
		Scene scene = new Scene(bp, 500, 500);
		scene.getStylesheets().add("style.css");
		arg0.setScene(scene);
		arg0.show();
	}

}
