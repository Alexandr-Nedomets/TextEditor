package com.company;

public class IndexFoundText {
    int i;

    public IndexFoundText() {
        this.i = 0;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public  void increment() {
        i++;
    }

    public  void decrement() {
        if (i > 0) {
            i--;
        }
    }
}