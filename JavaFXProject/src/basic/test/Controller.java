package basic.test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import basic.test.Video;
import basic.test.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller implements Initializable {
	@FXML
	TableView<Video> tableView;
	@FXML
	Button btnAdd;

	ObservableList<Video> list;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		TableColumn<Video, ?> tc = tableView.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("title"));

		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("director"));

		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("price"));

		list = FXCollections.observableArrayList();
		

		tableView.setItems(list);

		btnAdd.setOnAction(event -> {
			handleBtnAddAction();

		});

	}

	public void handleBtnAddAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("VideoAdd.fxml"));

			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();

			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TextField txtTitle = (TextField) parent.lookup("#txtTitle");
					TextField txtDirector = (TextField) parent.lookup("#txtDirector");
					TextField txtPrice = (TextField) parent.lookup("#txtPrice");
					Video video = new Video(txtTitle.getText(),txtDirector.getText(),
							Integer.parseInt(txtPrice.getText()));

					list.add(video);

					stage.close();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
}
