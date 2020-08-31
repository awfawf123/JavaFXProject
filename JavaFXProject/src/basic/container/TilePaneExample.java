package basic.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		TilePane root = new TilePane();
		root.setPrefTileWidth(100);
		root.setPrefTileHeight(100);
		
		ImageView[] ary = new ImageView[5];
		for(int i=0; i<5; i++) {
			ImageView iv = new ImageView();
			iv.setImage(new Image("/basic/images/fruit" + (i+1)+".jpg"));
			ary[i]=iv;
			root.getChildren().add(ary[i]);
		}
		
		
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("AppMain");
		
		
		
		
	}
	public static void main(String[] args) {
		Application.launch(args);
	}

}
