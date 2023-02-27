package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application implements EventHandler<ActionEvent> {
	
	public static void main(String[] args) {
		launch(args);
	}

//	Scene, gridpane,button dan tabel
	Scene scene;
	GridPane gp;
	Button button1;
	Button button2;
	Label label1;
	Label label2;
	
	public void init() {
		gp = new GridPane();
		button1 = new Button("Button 1");
		button2 = new Button("Button 2");
		label1 = new Label("Label 1");
		label2 = new Label("Label 2");		
	}
	
	public void Layouting() {
		gp.add(label1, 0, 0);
		gp.add(label2, 0, 1);
		gp.add(button1, 1, 0);
		gp.add(button2, 1, 1);
		
	}
	
	public void setEvent() {
		button1.setOnAction(this); //action event
		
		button2.addEventHandler(KeyEvent.KEY_PRESSED, (e)->{ //key event (pake keyboard)
			if(e.getCode()==KeyCode.A) {
				System.out.println("Dipencet");
				button2.setText("A");
			}
		});
		
		button2.addEventHandler(MouseEvent.MOUSE_ENTERED, (e)->{
			System.out.println("dihover w/ mouse event");
		});
		
		button2.addEventHandler(MouseEvent.MOUSE_EXITED, (e)->{
			System.out.println("cabut hover w/ mouse event");
		});
		
		button2.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			System.out.println("diklik w/ mouse event");
			label2.setText("label 2 di klik masbro");
		});
		
		
		
//		1. Button 2 harus kasi on drag detected event
		button2.setOnDragDetected((e)->{
			Dragboard db = button2.startDragAndDrop(TransferMode.ANY);
			ClipboardContent content = new ClipboardContent();
			content.putString(button2.getText());
			db.setContent(content);
			e.setDragDetect(true);
		});
//		2. Button 1 harus kasi on drag hover
		button1.setOnDragOver((e)->{
			if (e.getGestureSource()!=button1 && e.getDragboard().hasString()) {
				e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}
		});
//		3. Button 1 harus kasi on drag dropped
		button1.setOnDragDropped((e)->{
			Dragboard db = e.getDragboard();
			if (db.hasString()) {
				button1.setText(db.getString());
				e.setDropCompleted(true);
			}else {
				e.setDropCompleted(false);
			}
		});
//		Conlusion : dibuat 3 method diatas
		
		
		
		
		
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		init();
		Layouting();
		setEvent();
		
		scene = new Scene(gp, 500, 400);
		
		arg0.addEventHandler(WindowEvent.WINDOW_SHOWN, (e)->{
			System.out.println("New WINDOWS SHOW");
			e.consume();
		});
		
		arg0.setScene(scene);
		arg0.show();
		System.out.println("Test");
		
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource()==button1) {
			//kalau click button 1, setLabel
			label1.setText("Label Ini Di Click");
		}
		
	}
}



