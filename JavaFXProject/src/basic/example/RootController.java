package basic.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {
	@FXML
	TableView<Student> tableView;
	@FXML
	Button btnAdd, btnBarChart;
	
	ObservableList<Student> list;
	
	Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		TableColumn<Student, ?> tc = tableView.getColumns().get(0); // 첫번째칼럼
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("korean"));
		
		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("math"));
		
		
		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("english"));
		
		//성적저장
		 list = FXCollections.observableArrayList();
		
		tableView.setItems(list);
		
		
		
		//추가버튼
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				handleBtnAddAction();
				
				
			}
			
		});
		//차트버튼
		btnBarChart.setOnAction(e -> handleBtnChartAction());
		
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println(event);
				if(event.getClickCount()==2) {
					String selectedName = tableView.getSelectionModel().getSelectedItem().getName();
					handleDoubleClickAction(selectedName);
				}
				
			}
			
		});
		
	}// end of initialize
	
	public void handleDoubleClickAction(String name) {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		
		AnchorPane ap = new AnchorPane();
		ap.setPrefSize(210, 230);
		
		Label lName, lKorean, lMath ,lEnglish;
		TextField tName, tKorean, tMath, tEnglish;
		
		lName = new Label("이름");
		lName.setLayoutX(35);
		lName.setLayoutY(30);
		
		lKorean = new Label("국어");
		lKorean.setLayoutX(35);
		lKorean.setLayoutY(73);
		
		lMath = new Label("수학");
		lMath.setLayoutX(35);
		lMath.setLayoutY(99);
		
		lEnglish = new Label("영어");
		lEnglish.setLayoutX(35);
		lEnglish.setLayoutY(132);
		
		tName = new TextField();
		tName.setPrefWidth(110);
		tName.setLayoutX(72);
		tName.setLayoutY(30);
		
		tName.setText(name);
		tName.setEditable(false);
		
		tKorean = new TextField();
		tKorean.setPrefWidth(110);
		tKorean.setLayoutX(72);
		tKorean.setLayoutY(69);
		
		tMath = new TextField();
		tMath.setPrefWidth(110);
		tMath.setLayoutX(72);
		tMath.setLayoutY(95);
		
		tEnglish = new TextField();
		tEnglish.setPrefWidth(110);
		tEnglish.setLayoutX(72);
		tEnglish.setLayoutY(128);
		
		Button btnUpdate = new Button("수정");
		btnUpdate.setLayoutX(85);
		btnUpdate.setLayoutY(184);
		
		btnUpdate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for(int i=0; i<list.size(); i++) {
					if(list.get(i).getName().equals(name)) {
						Student student = new Student(name,
								Integer.parseInt(tKorean.getText()),
								Integer.parseInt(tMath.getText()),
								Integer.parseInt(tEnglish.getText())
								);
						list.set(i, student);
						
						stage.close();
					}
				}
				
			}
			
		});
		
		//이름기준으로 국어,수학,영어점수 화면에 입력
		for(Student stu : list) {
			if(stu.getName().equals(name)) {
				tKorean.setText(String.valueOf(stu.getKorean()));
				tMath.setText(String.valueOf(stu.getMath()));
				tEnglish.setText(String.valueOf(stu.getEnglish()));
			}
		}
		ap.getChildren().addAll(btnUpdate ,tName, tKorean, tMath, tEnglish, lKorean, lMath, lEnglish,lName);
		
		Scene scene = new Scene(ap);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void handleBtnChartAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		
		try {
			BorderPane chart = FXMLLoader.load(getClass().getResource("BarChart.fxml"));
			Scene scene = new Scene(chart);
			stage.setScene(scene);
			stage.show();
			
			// chart가지고와서 series를 추가해야한다
			BarChart barChart = (BarChart) chart.lookup("#barChart");
			
			//국어 카테고리
			XYChart.Series<String, Integer> seriesK = new XYChart.Series<String, Integer>();
			seriesK.setName("국어");
			
			ObservableList<XYChart.Data<String, Integer>> koreanList = FXCollections.observableArrayList();
			
			for(int i=0; i<list.size(); i++) {
				koreanList.add(new XYChart.Data<>(list.get(i).getName(), 
												  list.get(i).getKorean()));
				
			}
			XYChart.Series<String, Integer> seriesM = new XYChart.Series<String, Integer>();
			seriesM.setName("수학");
			
			ObservableList<XYChart.Data<String, Integer>> mathList = FXCollections.observableArrayList();
			
			for(int i=0; i<list.size(); i++) {
				mathList.add(new XYChart.Data<>(list.get(i).getName(), 
												  list.get(i).getMath()));
				
			}
			XYChart.Series<String, Integer> seriesE = new XYChart.Series<String, Integer>();
			seriesE.setName("영어");
			
			ObservableList<XYChart.Data<String, Integer>> englishList = FXCollections.observableArrayList();
			
			for(int i=0; i<list.size(); i++) {
				englishList.add(new XYChart.Data<>(list.get(i).getName(), 
												  list.get(i).getEnglish()));
				
			}
			seriesK.setData(koreanList);
			barChart.getData().add(seriesK);
			
			seriesM.setData(mathList);
			barChart.getData().add(seriesM);
			
			seriesE.setData(englishList);
			barChart.getData().add(seriesE);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//추가화면 보여주는 작업
	public void handleBtnAddAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());
		
		try {
			BorderPane borderPane = FXMLLoader.load(getClass().getResource("AddForm.fxml"));
			Scene scene = new Scene(borderPane);
			stage.setScene(scene);
			stage.show();
			//추가화면의 컨트롤 사용하기
			Button btnFormAdd = (Button) borderPane.lookup("#btnFormAdd");
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TextField txtName = (TextField) borderPane.lookup("#txtName");
					TextField txtKorean = (TextField) borderPane.lookup("#txtKorean");
					TextField txtMath = (TextField) borderPane.lookup("#txtMath");
					TextField txtEnglish = (TextField) borderPane.lookup("#txtEnglish");
					Student student = new Student(txtName.getText(),
							Integer.parseInt(txtKorean.getText()),
							Integer.parseInt(txtMath.getText()),
							Integer.parseInt(txtEnglish.getText())
							);
				
					list.add(student);
					//추가화면 닫기
					stage.close();
				}
				
			});
			//추가화면에 있는 취소버튼
			Button btnFormCancel = (Button) borderPane.lookup("#btnFormCancel");
			btnFormCancel.setOnAction(e -> {
				TextField txtName = (TextField) borderPane.lookup("#txtName");
				TextField txtKorean = (TextField) borderPane.lookup("#txtKorean");
				TextField txtMath = (TextField) borderPane.lookup("#txtMath");
				TextField txtEnglish = (TextField) borderPane.lookup("#txtEnglish");
				
				txtName.clear();
				txtKorean.clear();
				txtMath.clear();
				txtEnglish.clear();
				
				stage.close();
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
