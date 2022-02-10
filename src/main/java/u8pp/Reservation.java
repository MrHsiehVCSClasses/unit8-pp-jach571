package u8pp;

public class Reservation{
    String name = "";
    boolean frequentFlyer;
    
    /**
     * 
     * @param name : A string that takes the name of the passenger.
     * @param frequentFlyer : A boolean that takes whether the passenger is a frequent flyer or not.
     */
    public Reservation(String name, boolean frequentFlyer){
        this.name = name;
        this.frequentFlyer = frequentFlyer;
    }

    /**
     * 
     * @return : Returns the name of the passenger on the Reservation as a string.
     */
    public String getPassengerName(){
        return name;
    }

    /**
     * 
     * @return : Returns the whether the passenger on the Reservation is a frequent flyer (true) or not (false) as a boolean.
     */
    public boolean isFrequentFlyer(){
        return frequentFlyer;
    }
}