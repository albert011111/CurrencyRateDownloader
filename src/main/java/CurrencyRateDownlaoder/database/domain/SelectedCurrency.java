package CurrencyRateDownlaoder.database.domain;

import java.time.LocalDate;

/**
 * Created by Patryk on 26.07.2017.
 */
public class CurrencySelected extends Currency {

    private double val1;
    private double val2;
    private double val3;
    private double val4;
    private double val5;
    private double val6;
    private double val7;
    private double val8;
    private double val9;
    private double val10;
    private double valMin;
    private double valMax;
    private double valAvg;
    private LocalDate addDate;

    public CurrencySelected() {
    }

    public CurrencySelected(int id, String code, String currencyName, double valAvg, double valMax, double valMin, double val1, double val2, double val3, double val4, double val5, double val6, double val7, double val8, double val9, double val10, LocalDate addDate) {
        super(id, code, currencyName);
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
        this.val4 = val4;
        this.val5 = val5;
        this.val6 = val6;
        this.val7 = val7;
        this.val8 = val8;
        this.val9 = val9;
        this.val10 = val10;
        this.valMin = valMin;
        this.valMax = valMax;
        this.valAvg = valAvg;
        this.addDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return super.toString() + " avg: " + valAvg + " max: " + valMax + " min: " + valMin + " val1: " + val1 + " val8: " + val8;
    }

    public double getVal1() {
        return val1;
    }

    public void setVal1(double val1) {
        this.val1 = val1;
    }

    public double getVal2() {
        return val2;
    }

    public void setVal2(double val2) {
        this.val2 = val2;
    }

    public double getVal3() {
        return val3;
    }

    public void setVal3(double val3) {
        this.val3 = val3;
    }

    public double getVal4() {
        return val4;
    }

    public void setVal4(double val4) {
        this.val4 = val4;
    }

    public double getVal5() {
        return val5;
    }

    public void setVal5(double val5) {
        this.val5 = val5;
    }

    public double getVal6() {
        return val6;
    }

    public void setVal6(double val6) {
        this.val6 = val6;
    }

    public double getVal7() {
        return val7;
    }

    public void setVal7(double val7) {
        this.val7 = val7;
    }

    public double getVal8() {
        return val8;
    }

    public void setVal8(double val8) {
        this.val8 = val8;
    }

    public double getVal9() {
        return val9;
    }

    public void setVal9(double val9) {
        this.val9 = val9;
    }

    public double getVal10() {
        return val10;
    }

    public void setVal10(double val10) {
        this.val10 = val10;
    }

    public double getValMin() {
        return valMin;
    }

    public void setValMin(double valMin) {
        this.valMin = valMin;
    }

    public double getValMax() {
        return valMax;
    }

    public void setValMax(double valMax) {
        this.valMax = valMax;
    }

    public double getValAvg() {
        return valAvg;
    }

    public void setValAvg(double valAvg) {
        this.valAvg = valAvg;
    }


    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }
}
