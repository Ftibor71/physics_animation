package javafxapplication7;

/*
 * Sample Skeleton for 'FXMLDocument.fxml' Controller Class
 */

import static java.lang.Double.parseDouble;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label alabel;
    @FXML
    private Label vlabel;
    @FXML
    private TextField weight;
    @FXML
    private TextField sledgeheight;
    @FXML
    private TextField alfa;
    @FXML
    private LineChart stChart;
    @FXML
    private LineChart vtChart;
    @FXML
    private ImageView car;
        
    @FXML
    private void startcounting(ActionEvent event) {
        boolean iscorrect = true;
        
        BackOffice doit = new BackOffice();
        
        iscorrect = doit.checkData(weight.getText(), sledgeheight.getText(), 
                alfa.getText());
        
        if (iscorrect == false) {
            Alert wrongdata = new Alert(AlertType.ERROR);
            wrongdata.setTitle("Hibás adatbevitel!");
            wrongdata.setHeaderText("A megadott adatok hibásak.");
            wrongdata.setContentText("A megadott adatok nem számok, "
                    + "vagy kívül esnek a megadható értéktartományon. ");
            wrongdata.showAndWait();
        }
        if (iscorrect == true){
            mainprocess(doit);
            }
        
    }

    private void mainprocess(BackOffice doit) {
        double velocity;
        double speed;
        double time;
        double length;
        double x = 0;
        double y;
        double v;
        velocity = doit.Counta(parseDouble(alfa.getText()));
        speed = doit.Countv(parseDouble(sledgeheight.getText()));
        time = speed / velocity;
        length = 0.5*velocity*time*time;
        XYChart.Series stdata = new XYChart.Series();
        stdata.setName("idő [sec]");
        XYChart.Series vtdata = new XYChart.Series();
        vtdata.setName("idő [sec]");
        stChart.getData().clear();
        vtChart.getData().clear();
        while (x < time){
            y = 0.5 * velocity * x * x;
            v = velocity * x;
            stdata.getData().add(new XYChart.Data(x, y));
            vtdata.getData().add(new XYChart.Data(x, v));
            x = x + 0.1;
        }
        stChart.getData().add(stdata);                
        vtChart.getData().add(vtdata);
        alabel.setText(String.format("%,.2f", velocity));
        vlabel.setText(String.format("%,.2f", speed));
        Path carpath = new Path();
        carpath.getElements().add(new MoveTo(58, 62));
        carpath.getElements().add(new LineTo(431, 203));
        PathTransition carAnimation = new PathTransition();
        carAnimation.setDuration(Duration.millis(1200));
        carAnimation.setDelay(Duration.millis(100));
        carAnimation.setPath(carpath);
        carAnimation.setNode(car);
        carAnimation.setAutoReverse(false);
        carAnimation.play();
    }

     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

