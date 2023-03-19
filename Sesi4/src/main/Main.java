package main;


import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;



public class Main extends Application{
	
	ArrayList<user> registeredUser = new ArrayList<>();
	user currUser = null;
	Scene homePage, profilePage, signInPage, signUpPage;
	Menu login, dashboard;
	MenuBar mb;
	MenuItem home, profile, signIn, signUp;
	VBox menuContainer;
	
	public void initialize() {
		mb = new MenuBar();
		login = new Menu("Login");
		dashboard = new Menu("Dashboard");
		
		home = new MenuItem("Home");
		profile = new MenuItem("View Profile");
		signIn = new MenuItem("Sign In");
		signUp = new MenuItem("Sign Up");
		menuContainer = new VBox();
		
		login.getItems().addAll(signIn, signUp);
		dashboard.getItems().addAll(home, profile);
		
		mb.getMenus().addAll(login, dashboard);
		
		menuContainer.getChildren().addAll(mb);
		
		
	}
	
	public void homePage(Stage arg0) {
		Label lbl = new Label("You Are In Home");
		VBox page = new VBox();
		page.getChildren().addAll(menuContainer,lbl);
		homePage = new Scene(page, 600, 300);
		arg0.setScene(homePage);
	}
	
	public void registerPage(Stage arg0) {
//		System.out.println("TEST");
		BorderPane bp = new BorderPane();
		VBox container = new VBox();
		Label emaillbl = new Label("Email");
		TextField email = new TextField();
		Label passlbl = new Label("Password");
		PasswordField password = new PasswordField();
		ComboBox<String> countryBox = new ComboBox<>();
		Label countrylbl = new Label("Copunry");
		countryBox.getItems().addAll("Indonesia", "Malaysia", "Singapore", "China", "Japan");
		Spinner<Integer> ageSpinner = new Spinner<>(1, 100, 1);
		Label agelbl = new Label("Age");
		Button signUpbtn = new Button("Sign Up");
		Label errorlbl = new Label("");
		
//		ageSpinner.setEditable(true);
		
		container.getChildren().addAll(menuContainer, emaillbl, email, passlbl, password, countrylbl, countryBox, agelbl, ageSpinner, signUpbtn, errorlbl);
		container.setMinWidth(600);
		bp.setLeft(container);
		
//		Validasi
		signUpbtn.setOnMouseClicked(e->{
			if (!email.getText().contains("@")) {
				errorlbl.setText("Invalid Email Format");
			}
			else if(password.getText().length()<6) {
				errorlbl.setText("Password Has To Be At Least 6 Characters");
			}
			else if (countryBox.getValue()==null) {
				errorlbl.setText("Please Select A Country");
			}
			else {
				Integer check = 1;
				for (user user : registeredUser) {
					if (user.getEmail().equals(email.getText())) {
						errorlbl.setText("Email Already Registered");
						check = 0;
					}
				}
				if (check == 1) {
					registeredUser.add(new user(email.getText(),password.getText(),countryBox.getValue(),ageSpinner.getValue()));
					errorlbl.setText("New User Registered");
				}
			}
		});
		
		signUpPage = new Scene(bp, 600, 300);
		arg0.setScene(signUpPage);
	}
	
	public void signInPage(Stage arg0) {
		BorderPane bp = new BorderPane();
		VBox container = new VBox();
		Label emaillbl = new Label("Email");
		TextField email = new TextField();
		Label passlbl = new Label("Password");
		PasswordField password = new PasswordField();
		Button signInbtn = new Button("Sign In");
		Label errorlbl = new Label("");
		
		container.getChildren().addAll(menuContainer,emaillbl,email,passlbl,password, signInbtn, errorlbl);
		
		signInbtn.setOnMouseClicked(e->{
			for(user user: registeredUser) {
				if (user.getEmail().equals(email.getText()) && user.getPassword().equals(password.getText())) {
					currUser = user;				}
			}
			errorlbl.setText("Wrong");
		});
		
		container.setMinWidth(600);
		bp.setLeft(container);
		
		signInPage = new Scene(bp, 600, 300);
		arg0.setScene(signInPage);
		
	}
	
	public void profilePage() {
		Label email = new Label("Email : " + currUser.getEmail());
		Label country = new Label("Country: " + currUser.getCountry());
		VBox container = new VBox();
		
		container.getChildren().addAll(menuContainer, email, country);
		
		profilePage = new Scene(container,600,300);
		arg0.s
	}
	
	public void setAction(Stage arg0) {
		home.setOnAction(e->{
			homePage(arg0);
		});
		signUp.setOnAction(e->{
			registerPage(arg0);
			
		});
		signIn.setOnAction(e->{
			signInPage(arg0);
		});
		profile.setOnAction(e->{
			profilePage(arg0);
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		initialize();
		homePage(arg0);
		setAction(arg0);
		arg0.show();
		
	}
}
