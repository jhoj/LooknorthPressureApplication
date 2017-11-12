/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reader;

import com.fazecast.jSerialComm.SerialPort;
import java.util.Observable;

/**
 *
 * @author root
 */
public class SerialReader extends Observable implements Runnable {
    public static void main(String[] args) {
      SerialReader reader = new SerialReader();
      new Thread(reader).start();
    }

    @Override
    public void run() {
        SerialPort comPort = SerialPort.getCommPorts()[0];
        comPort.setComPortParameters(9600, 8, 1, 0);
        comPort.openPort();
        try {
            while (true)
            {
               while (comPort.bytesAvailable() < 3)
                  Thread.sleep(20);

               byte[] readBuffer = new byte[comPort.bytesAvailable()];
               comPort.readBytes(readBuffer, readBuffer.length);
               String message = new String(readBuffer);
               System.out.println(message);
               setChanged();
               notifyObservers(message);
            }
        } catch (Exception e) { e.printStackTrace(); }
          comPort.closePort();
    }
}

