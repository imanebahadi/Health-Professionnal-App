package fr.univ_lyon1.info.m1.mes.model;

public interface PrescriptionObservable {
    //methods to register and unregister observers

    /**
     * Add an observer to the list of observers
     * @param obj
     */
    void register(PrescriptionObserver obj);

    /**
     * Remove an observer from the list of observers
     * @param obj
     */
    void unregister(PrescriptionObserver obj);

    /**
     * Notify observers of change
     */
    void notifyObservers();


}
