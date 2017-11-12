/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Main.Controller;
import Utility.Converter;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author root
 */
public class Pressure {
    private float pressure;
    private float lowLimit;
    private float highLimit;
    private Converter converter;
    
    public Pressure() {
      this.converter = new Converter(5f, 1023f, 0.5f);
    };
    public Pressure(float lowLimit, float highLimit) {
        this.lowLimit = lowLimit;
        this.highLimit = highLimit;
        this.converter = new Converter(5f, 1023f, 0.5f);
    }
    
    public void CalculatePressure(float analogValue) {
        System.out.print("Received message! AnalogValue: " + analogValue);
        float voltage = converter.ToVoltage(analogValue);
        System.out.print(" Voltage: " + voltage);
        float pascal = converter.ToPascal(voltage);
        System.out.print(" Pascal: " + pascal);
        float bar = converter.ToBar(pascal);
        System.out.println(" Bar: " + bar);
        pressure = bar;
    }

    public float getPressure() {
        return pressure;
    }

    public float getLowLimit() {
        return lowLimit;
    }

    public float getHighLimit() {
        return highLimit;
    }        
}
