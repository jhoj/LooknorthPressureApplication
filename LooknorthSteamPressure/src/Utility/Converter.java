package Utility;

import java.math.BigDecimal;

/**
 *
 * @author jákup høj
 */
public class Converter {
    private float systemVoltage;
    private float adcResolution;
    private float lowOutputLimit;
    
    public Converter(){};
    
    public Converter(float systemVoltage, float adcResolution, float lowOutputLimit) {
        this.systemVoltage = systemVoltage;
        this.adcResolution = adcResolution;
        this.lowOutputLimit = lowOutputLimit;
    }
    public float ToVoltage(float analogValue) {
        float voltage = (analogValue * systemVoltage) / adcResolution;
        return round(voltage, 2);
    }
 
    public float ToPascal(float voltage) {
        float deltaVoltage = voltage - lowOutputLimit;
        float pascal = 3.0f * deltaVoltage * 1000000.0f;
        return round(pascal, 2);
    }
    
    public float ToBar(float pascal) {
        float bar = pascal / 10e5f;
        return round(bar, 2);
    }

    public float getSystemVoltage() {
        return systemVoltage;
    }

    public void setSystemVoltage(float systemVoltage) {
        this.systemVoltage = systemVoltage;
    }

    public float getAdcResolution() {
        return adcResolution;
    }

    public void setAdcResolution(float adcResolution) {
        this.adcResolution = adcResolution;
    }

    public float getLowOutputLimit() {
        return lowOutputLimit;
    }

    public void setLowOutputLimit(float lowOutputLimit) {
        this.lowOutputLimit = lowOutputLimit;
    }
    
    private float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

}
