package smartplanner;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author soicem's com
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btnRst;
    @FXML
    private Button btnAdd;
    @FXML
    private JFXButton btnSet;
    @FXML
    private JFXButton btn1HR;

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

    long sum = 0;

    int sec1;
    int sec2;
    int sec3;
    int sec4;
    int sec5;
    int sec6;

    Timeline timeline;

    @FXML
    private ListView<String> List;
    public Button ListBtn;

    public static ObservableList PriList
            = FXCollections.observableArrayList();
    /*public static ObservableList data
            = FXCollections.observableArrayList();*/
    @FXML
    private TextField listText;
    @FXML
    private TextField listNumber;

    public MediaPlayer Player;

    int timeCnt = 0; // 한 번 이상 음악재생 방지 flag 정수

    String now;
    @FXML
    private JFXButton btnPomo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        final URL resource = getClass().getResource("mp3/music.mp3"); // 경로 조심
        final Media media = new Media(resource.toString());
        Player = new MediaPlayer(media);

        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");
        now = dayTime.format(new Date(time));
        
        timeline = new Timeline(new KeyFrame(
                        Duration.millis(1000),
                        ae -> doSomething()));
                timeline.setCycleCount(Animation.INDEFINITE);

        List.setItems(PriList);

    }

    @FXML
    private void handleBtnRst(ActionEvent event) {
        try {

            timeline.stop();
            timeCnt = 0;
            setTime("00:00:00");
            Player.stop();
            
        } catch (Exception e) {
            setTime("00:00:00");
            Player.stop();
            System.out.println("예외 발생");
        } finally {
            
        }
    }

    @FXML
    private void handleBtnSet(ActionEvent event) {
        try {
            /*String s = System.getProperty("user.dir");
            System.out.println("현재 디렉토리는 " + s + " 입니다");*/
            if (timeCnt == 0) {
                sec1 = (Integer.parseInt(text1.getText())) % 10;
                sec2 = (Integer.parseInt(text2.getText())) % 10;

                sec3 = (Integer.parseInt(text3.getText())) % 10;
                sec4 = (Integer.parseInt(text4.getText())) % 10;

                sec5 = (Integer.parseInt(text5.getText())) % 10;
                sec6 = (Integer.parseInt(text6.getText())) % 10;

                //sum = (sec1 + sec2 * 10 + sec3 * 60 + sec4 * 600 + sec5 * 3600 + sec6 * 36000);
                timelineStart();
            } else {
            }
        } catch (Exception e) {

        }
    }
    private boolean timelineStart(){
        timeline.play();
        timeCnt++;
        return true;
    }
    private void doSomething()  {
        if (sec1 > 0) {
            sec1--;
        } else if (sec2 > 0) {
            sec1 = 9;
            sec2--;

        } else if (sec3 > 0) {
            sec1 = 9;
            sec2 = 5;
            sec3--;

        } else if (sec4 > 0) {
            sec1 = 9;
            sec2 = 5;
            sec3 = 9;
            sec4--;

        } else if (sec5 > 0) {

            sec1 = 9;
            sec2 = 5;
            sec3 = 9;
            sec4 = 5;
            sec5--;

        } else if(sec6 >0){
            sec1 = 9;
            sec2 = 5;
            sec3 = 9;
            sec4 = 5;
            sec5 = 9;
            sec6--;
        }
        else {
            Player.play();
           
            System.out.println("hello");
            timeline.stop();
            timeCnt--;
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
        if(sec5 != 9){
            sec5 += 1;
            
        } else {
            sec6 +=1;
            sec5 = 0;
        }
        text5.setText(Integer.toString(sec5));
        text6.setText(Integer.toString(sec6));
    }

    @FXML
    private void handleListBtn(ActionEvent event) {
        try {
            ObservableList bakup = PriList;
            String listTextTmp = listNumber.getText() + ". " + listText.getText();
            int listNumberTmp = Integer.parseInt(listNumber.getText());
            listNumber.setText(Integer.toString(listNumberTmp + 1));

            bakup.add(listNumberTmp - 1, listTextTmp);

            PriList = bakup;

            List.setItems(bakup);
        } catch (Exception e) {
            System.out.println("예외 발생");
        }
    }

    @FXML
    private void ListHandleRst(ActionEvent event) {
        try {

            ObservableList bakup = PriList;
            bakup.clear();
            listNumber.setText("1");
            listText.setText("");
            PriList.clear();
            PriList = bakup;
            List.setItems(bakup);
            
        } catch (Exception e) {
            System.out.println("예외 발생");
        }
    }

    @FXML
    private void handleWebview(ActionEvent event) {

        try {
            Stage dialog = new Stage(StageStyle.UTILITY);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.setTitle("Hello browser");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("webview.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root1);
            dialog.setScene(scene);
            dialog.show();

        } catch (IOException e) {
        }
    }

    @FXML
    private void handleGraph(ActionEvent event) {

        try {
            Stage dialog = new Stage(StageStyle.UTILITY);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(btnAdd.getScene().getWindow());
            dialog.setTitle("Hello graph");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Graph.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            AreaChart<?, ?> areaChart = (AreaChart) root1.lookup("#AreaChart");

            XYChart.Series series = new XYChart.Series(); //Make a new XYChart object  

            try {
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
                Statement stat = conn.createStatement();

                ResultSet rs = stat.executeQuery("select * from data;");

                while (rs.next()) {

                    series.getData().add(new XYChart.Data(rs.getString("date"), rs.getInt("score")));
                }

                rs.close();
                conn.close();

            } catch (Exception e) {

            }

            System.out.println(series.getData());

            areaChart.getData().addAll(series);

            Scene scene = new Scene(root1);
            dialog.setScene(scene);
            dialog.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buttonSelected(ActionEvent event) {
        ObservableList<String> data;
        ObservableList bakup = null;

        try {
            data = List.getSelectionModel().getSelectedItems();

            bakup = PriList;
            String a = data.get(0);
            bakup.remove(a);

            Integer.parseInt(a.substring(0, 1));
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement stat = conn.createStatement();

            PreparedStatement prep = conn.prepareStatement("insert into data values (?, ?, ?, ?);");

            ResultSet rs_2 = stat.executeQuery("select * from ListView where date ='" + now + "';");

            int thisDataNumber = Integer.parseInt(a.substring(0, 1));
            int logic = rs_2.getInt(11);
            System.out.println("this data Number : " + thisDataNumber);
            logic ^= (logic & (int) Math.pow(2, (thisDataNumber - 1)));

            rs_2.close();

            stat.executeUpdate("update ListView set logic='" + logic + "' where date='" + now + "';");

            System.out.println("logic is : " + logic);

            ResultSet rs = stat.executeQuery("select _id from data where date = '" + now + "';");
            int id = rs.getInt(1);

            rs = stat.executeQuery("select score, count from data where _id=" + Integer.toString(id) + ";");
            int score = rs.getInt("score");
            int count = rs.getInt("count");
            int addVal = oneHundredAlgo(count, thisDataNumber);

            System.out.println(score);
            System.out.println(addVal);
            score += addVal;
            stat.executeUpdate("update data set score=" + Integer.toString(score) + " where _id=" + Integer.toString(id) + ";");

            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            rs.close();

            stat.close();
            conn.close();
            prep.close();

        } catch (Exception e) {

        }
        PriList = bakup;
        List.setItems(bakup);

    }

    public int oneHundredAlgo(int cnt, int no) {

        switch (no) {
            case 1:
                return 50;
            case 2:
                return 30;
            case 3:
                return 15;
            case 4:
                return 5;
            case 5:
                return 3;
            default:
                return 2;
        }
    }

    @FXML
    private void ButtonSave(ActionEvent event) throws Exception { // 
        ObservableList<String> data = PriList;
        System.out.println(data);
        int logic = 0;
        int cnt = 0;
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
        Statement stat = conn.createStatement();

        try {
            ResultSet rs_2 = stat.executeQuery("select * from ListView where date='" + now + "';");

            if (rs_2.getString("date").equals(now)) {

                cnt = 0;
                stat.executeUpdate("delete from ListView where date ='" + now + "';");

                ListIterator<String> itr = data.listIterator();
                PreparedStatement prepListView = conn.prepareStatement("insert into ListView values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                cnt = 0;
                prepListView.setString(2, now);
                System.out.println(now);

                while (itr.hasNext()) {
                    String tmp = itr.next();
                    System.out.println(tmp);
                    prepListView.setString(cnt + 3, tmp);
                    logic <<= 1;
                    logic |= 1;

                    cnt++;
                }

                prepListView.setInt(11, logic);
                prepListView.addBatch();

                conn.setAutoCommit(false);
                prepListView.executeBatch();
                conn.setAutoCommit(true);

                prepListView.close();
            } else {

                PreparedStatement prepListView = conn.prepareStatement("insert into ListView values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                cnt = 0;
                prepListView.setString(2, now);
                System.out.println(now);
                ListIterator<String> itr = data.listIterator();
                while (itr.hasNext()) {
                    String tmp = itr.next();
                    System.out.println(tmp);
                    prepListView.setString(cnt + 3, tmp);
                    logic <<= 1;
                    logic |= 1;

                    cnt++;
                }

                prepListView.setInt(11, logic);
                prepListView.addBatch();

                conn.setAutoCommit(false);
                prepListView.executeBatch();
                conn.setAutoCommit(true);

                prepListView.close();
            }

        } catch (Exception e) {
            PreparedStatement prepListView = conn.prepareStatement("insert into ListView values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            cnt = 0;
            prepListView.setString(2, now);
            System.out.println(now);
            ListIterator<String> itr = data.listIterator();
            while (itr.hasNext()) {
                String tmp = itr.next();
                System.out.println(tmp);
                prepListView.setString(cnt + 3, tmp);
                logic <<= 1;
                logic |= 1;

                cnt++;
            }

            prepListView.setInt(11, logic);
            prepListView.addBatch();

            conn.setAutoCommit(false);
            prepListView.executeBatch();
            conn.setAutoCommit(true);

            prepListView.close();

        }

        try {
            ResultSet rs = stat.executeQuery("select * from data where date='" + now + "';");
            int com = 0;

            if ((now).equals(rs.getString("date"))) {

                stat.executeUpdate("update data set score=0 where date='" + now + "';");

            } else {
                System.out.println("world peace");
            }

            rs.close();
            conn.close();

        } catch (Exception e) { // 없을 경우
            PreparedStatement prep = conn.prepareStatement("insert into data values (?, ?, ?, ?);");

            prep.setString(2, now);
            prep.setInt(3, cnt);
            prep.setInt(4, 0);
            prep.addBatch();

            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);

            prep.close();

        }

    }

    @FXML
    private void ListHandleDBRst(ActionEvent event) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Statement stat;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            
            stat = conn.createStatement();
            stat.executeUpdate("drop table if exists data;");
            stat.executeUpdate(
                    "create table data (_id integer primary key autoincrement, "
                            + "date text not null, count integer, score integer);");
            stat.executeUpdate("drop table if exists ListView;");
            stat.executeUpdate(
                    "create table ListView (_id integer primary key autoincrement, "
                            + "date text not null, string_1 text, string_2 text, string_3 text, "
                            + "string_4 text, string_5 text, string_6 text,"
                            + " string_7 text, string_8 text, logic integer);");
            System.out.println("DB reset!");
            conn.close();
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally{
             
        }
       
        

    }

    @FXML
    private void ListHandleReco(ActionEvent event) {

        try {
            ObservableList bakup = PriList;
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement stat = conn.createStatement();

            ResultSet rs_2 = stat.executeQuery("select * from ListView where date='" + now + "';");

            int ListCnt = 0;
            int logic = rs_2.getInt(11);

            bakup.clear();
            System.out.println(bakup);
            int getStrCnt = 0;
            while (ListCnt < 8) {

                try {

                    if ((logic & (int) (Math.pow(2, ListCnt))) == (int) (Math.pow(2, ListCnt))) {
                        System.out.println("and 연산 : " + (logic & (int) (Math.pow(2, ListCnt))));
                        bakup.add(getStrCnt++, rs_2.getString("string_" + (ListCnt + 1)));

                        logic = (logic ^ (int) (Math.pow(2, ListCnt)));

                    }
                    if (logic == 0) { // 모든 항목이 추가되었음.  logic 0000 0000
                        break;
                    }

                    ListCnt++;
                } catch (Exception e) {
                    ListCnt++;
                }
            }

            PriList = bakup;
            List.setItems(bakup);

            rs_2.close();
            conn.close();
            stat.close();

        } catch (Exception e) {
            System.out.println("예외 처리");
        }

    }

    @FXML
    private void setOnMouseClicked(MouseEvent event) {

    }

    @FXML
    private void recordTime(ActionEvent event) {
    }

    @FXML
    private void handlePlus(ActionEvent event) {
    }

    @FXML
    private void handleEntireOPList(ActionEvent event) {
    }

    @FXML
    private void handleYears(ActionEvent event) {
    }

    @FXML
    private void handleRANK(ActionEvent event) {
    }

    @FXML
    private void handleTimeCheck(ActionEvent event) {
    }

    @FXML
    private void handlePomo(ActionEvent event) {
        setTime("00:25:00");
        timelineStart();
        /*if(isTimeZero())
            setTime("00:05:00");
        timelineStart();*/
         
    }
    
    private void setTime(String time){
        String[] split = time.split(":");
        sec6 = Integer.parseInt(split[0])/10;
        sec5 = Integer.parseInt(split[0])%10;
        sec4 = Integer.parseInt(split[1])/10;
        sec3 = Integer.parseInt(split[1])%10;
        sec2 = Integer.parseInt(split[2])/10;
        sec1 = Integer.parseInt(split[2])%10;
        
        text1.setText(Integer.toString(sec1));
        text2.setText(Integer.toString(sec2));
        text3.setText(Integer.toString(sec3));
        text4.setText(Integer.toString(sec4));
        text5.setText(Integer.toString(sec5));
        text6.setText(Integer.toString(sec6));
    }
    
    /*private boolean isTimeZero(){
        if(sec1 == 0 && sec2 == 0 && sec3 ==0 && sec4 == 0 && sec5 == 0 && sec6 ==0)
            return true;
        else
            return false;
    }*/

}
