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
	TableView<Users> tableView1;
	@FXML
	Button btnAdd, btnUserList, btnSearch;

	ObservableList<Video> list;
	ObservableList<Users> list1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Video
		TableColumn<Video, ?> tc = tableView.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("title"));

		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("director"));

		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("price"));

		// Users
		TableColumn<Users, ?> tc1 = tableView1.getColumns().get(0);
		tc1.setCellValueFactory(new PropertyValueFactory<>("name"));

		tc1 = tableView1.getColumns().get(1);
		tc1.setCellValueFactory(new PropertyValueFactory<>("email"));

		tc1 = tableView1.getColumns().get(2);
		tc1.setCellValueFactory(new PropertyValueFactory<>("number"));

		tc1 = tableView1.getColumns().get(3);
		tc1.setCellValueFactory(new PropertyValueFactory<>("age"));

		list1 = FXCollections.observableArrayList();
		list = FXCollections.observableArrayList();

		tableView.setItems(list);
		tableView1.setItems(list1);

		btnAdd.setOnAction(event -> {
			handleBtnAddAction();

		});

		btnUserList.setOnAction(event -> {
			handleBtnUserListAction();
		});

		btnSearch.setOnAction(event -> {
			handleBtnSearchAction();
		});
	}

	public void handleBtnUserListAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnUserList.getScene().getWindow());

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("UserAdd.fxml"));

			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();

			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TextField txtName = (TextField) parent.lookup("#txtName");
					TextField txtEmail = (TextField) parent.lookup("#txtEmail");
					TextField txtNumber = (TextField) parent.lookup("#txtNumber");
					TextField txtAge = (TextField) parent.lookup("#txtAge");
					Users user = new Users(txtName.getText(), txtEmail.getText(), txtNumber.getText(),
							Integer.parseInt(txtAge.getText()));

					list1.add(user);

					stage.close();
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}

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
					Video video = new Video(txtTitle.getText(), txtDirector.getText(),
							Integer.parseInt(txtPrice.getText()));

					list.add(video);

					stage.close();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void handleBtnSearchAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnSearch.getScene().getWindow());

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("Search.fxml"));

			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();

			Button btns = (Button) parent.lookup("#btns");
			btns.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TextField txtTitle = (TextField) parent.lookup("#txtTitle");
					TextField txtDirector = (TextField) parent.lookup("#txtDirector");
					TextField txtPrice = (TextField) parent.lookup("#txtPrice");

					for (Video vi : list) {
						if (vi.getTitle().equals(txtTitle.getText())) {
							txtDirector.setText(vi.getDirector());
							txtPrice.setText(String.valueOf(vi.getPrice()));
						}
					}

				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
