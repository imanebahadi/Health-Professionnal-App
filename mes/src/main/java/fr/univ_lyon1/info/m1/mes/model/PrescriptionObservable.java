package fr.univ_lyon1.info.m1.mes.model;

public interface PrescriptionObservable {
    //methods to register and unregister observers
    void register(PrescriptionObserver obj);
    void unregister(PrescriptionObserver obj);
    //method to notify observers of change
    void notifyObservers();


}
