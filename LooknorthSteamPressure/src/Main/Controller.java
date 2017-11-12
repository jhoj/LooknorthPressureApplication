package Main;

import GUI.Frame;
import Model.Pressure;
import Reader.SerialReader;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author jákup høj
 */
public class Controller implements Observer {
    private final Pressure model;
    private final Frame view;
    
    public Controller(Pressure pressure, Frame frame) {
        this.model = pressure;
        this.view = frame;
    }

    @Override
    public void update(Observable o, Object arg) {
        String message = (String)arg; 
        float analogValue = Float.parseFloat(message);
        model.CalculatePressure(analogValue);
        view.UpdateView(model.getPressure());
        view.revalidate();
    }
    
    public static void main(String[] args) {
        SerialReader serialReader = new SerialReader();
        Frame view = new Frame();
        view.setVisible(true);
        Pressure model = new Pressure(3f, 3.5f);
        Controller controller = new Controller(model, view);        
        serialReader.addObserver(controller);
        
        new Thread(serialReader).start();
    }
    
    
        
}
