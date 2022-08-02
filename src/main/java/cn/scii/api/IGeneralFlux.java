package cn.scii.api;

public interface IGeneralFlux<F> {

    
    void addEnergy(F amt);

    F extractEnergy(F amt, boolean simulate);

    F getGeneralEnergyStored();

    F getMaxEnergyStored();

}
