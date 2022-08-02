package cn.scii.api;

public class ScienceFlux {
    private int value = 0;
    public static final String name = "Science Flux";
    public static final String abbr = "SF";

    public ScienceFlux(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + abbr;
    }

    public int getValue() {
        return value;
    }

    public void addValue(int value) {
        this.value += value;
    }

    public void setValue(int max) { this.value = max;
    }

    public ScienceFlux extract(ScienceFlux amt, boolean simulate) {
        //TODO Finish the simulation part
        this.addValue(-amt.getValue());
        return new ScienceFlux(amt.getValue());
    }
}
