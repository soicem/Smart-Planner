/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartplanner;

import com.sun.java.swing.plaf.windows.resources.windows;
import static java.awt.SystemColor.window;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import static java.util.Collections.list;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import static javafx.scene.input.KeyCode.T;
import javafx.util.Duration;
import static javax.swing.text.StyleConstants.Orientation;

/**
 *
 * @author soicem's com
 */



public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btnRst;
    @FXML
    private Button btnGo;
    @FXML
    private ProgressBar progressBar;
    
    private Task<Void> task;
    @FXML
    private TextField text6;
    @FXML
    private TextField text5;
    @FXML
    private TextField text4;
    @FXML
    private TextField text3;
    @FXML
    private TextField text2;
    @FXML
    private TextField text1;
    @FXML
    private Button btnSet;
    
    long sum = 0;
    
    int sec1;
    int sec2;
    int sec3;
    int sec4;
    int sec5;
    int sec6;
    
    //int progress_cnt = 0;
        
    Timeline timeline;
    
    @FXML
    private Button btn1HR;
    private TreeView<String> tree;
    private TextField RootText;
    private TextField NodeText;
    @FXML
    private ListView<String> List;
    @FXML
    private Button ListBtn;
    
    public static final ObservableList PriList = 
        FXCollections.observableArrayList();
    public static ObservableList data = 
        FXCollections.observableArrayList();
    @FXML
    private TextField listText;
    @FXML
    private TextField listNumber;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        btnGo.setOnAction(event->handleBtnGo(event));
        btnRst.setOnAction(event->handleBtnRst(event));
        
        
        
        
    }    

    @FXML
    private void handleBtnRst(ActionEvent event) {
        timeline.stop();
        
        text1.setText("0");
        text2.setText("0");
        text3.setText("0");
        text4.setText("0");
        text5.setText("0");
        text6.setText("0");
    }

    @FXML
    private void handleBtnGo(ActionEvent event) { // Progress bar 만들다가 멈춤.
            
            
        	/*task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				
					if(isCancelled()) { 
						updateMessage("��ҵ�");
						 
					}
					updateProgress(progress_cnt, sum);
					updateMessage(String.valueOf(progress_cnt));
					try {Thread.sleep(100); } catch(InterruptedException e) {
						if(isCancelled()) { 
							updateMessage("��ҵ�");
							
						}
					}
				
				return null;
			}
		};
		
		progressBar.progressProperty().bind(task.progressProperty());
		//lblWorkDone.textProperty().bind(task.messageProperty());
		
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();*/
    }

    @FXML
    private void handleBtnSet(ActionEvent event) {
        sec1 = Integer.parseInt(text1.getText());
        sec2 = Integer.parseInt(text2.getText());
        
        sec3 = Integer.parseInt(text3.getText());
        sec4 = Integer.parseInt(text4.getText());
        
        sec5 = Integer.parseInt(text5.getText());
        sec6 = Integer.parseInt(text6.getText());
        
        sum = (sec1 + sec2 * 10 + sec3 * 60 + sec4 * 600 + sec5 * 3600 + sec6 * 36000);
        
        timeline = new Timeline(new KeyFrame(
            Duration.millis(1000),
            ae -> doSomething()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void doSomething() {
        if(sec1 >0){
            sec1--; 
        }
        else{
            if(sec2 >0){
                sec1 = 9;
                sec2--;
                
            }
            else{
                if(sec3>0){
                    sec1 = 9;
                    sec2 = 5;
                    sec3--;
                    
                }
                else
                    if(sec4>0){
                        sec1 = 9;
                        sec2 = 5;
                        sec3 = 9;
                        sec4--;
                        
                    }
                    else
                        if(sec5>0){
                            sec1 = 9;
                            sec2 = 5;
                            sec3 = 9;
                            sec4 = 5;
                            sec5--;
                            
                        }
                        else
                            if(sec6>0 && sec6 <=2){
                                sec1 = 9;
                                sec2 = 5;
                                sec3 = 9;
                                sec4 = 5;
                                sec5 = 3;
                                sec6--;
                                
                            }
                            else{
                                
                                timeline.stop();
                            }
            }
                
        }
        
        text1.setText(Integer.toString(sec1));
        text2.setText(Integer.toString(sec2));
        text3.setText(Integer.toString(sec3));
        text4.setText(Integer.toString(sec4));
        text5.setText(Integer.toString(sec5));
        text6.setText(Integer.toString(sec6));
    }

    @FXML
    private void handleBtn1HR(ActionEvent event) {
        sec5 += 1;
        sum += sec5 * 3600;
        text5.setText(Integer.toString(sec5 ));
    }  

    

    private void handleKeep(ActionEvent event) {
        String RootName = RootText.getText();
        String NodeName = NodeText.getText();
        
        TreeItem<String> root = new TreeItem<>(RootName);
        root.setExpanded(true);
        for(int i =0 ;i<6; i++){
            TreeItem<String> item = new TreeItem<String>(NodeName + i);
            root.getChildren().add(item);
        }
        
      
        
        
        tree.setRoot(root);
    }

    @FXML
    private void handleListBtn(ActionEvent event) {
        
        String listTextTmp = listNumber.getText() +". " +  listText.getText();
        int listNumberTmp = Integer.parseInt(listNumber.getText());
        listNumber.setText(Integer.toString(listNumberTmp+1));
        
        PriList.add(listNumberTmp-1, listTextTmp);
        
        List.setItems(PriList);
    }

    @FXML
    private void ListHandleRst(ActionEvent event) {
        PriList.clear();
        List.setItems(PriList);
    }

    
    
}

