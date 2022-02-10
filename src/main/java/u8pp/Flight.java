package u8pp;
import java.util.ArrayList;

public class Flight{
    //2d array of the Reservations on the airplane
    private Reservation[][] airplane;
    //Integer for the column of the aisle of the airplane
    private int aisleColumn = 0;

    /**
     * 
     * @param numRows : The number of rows in the airplane
     * @param numSeats : The number of seats per row in the airplane (the column of the 2d array)
     * Determines and assigns the aisle row of the airplane
     */
    public Flight(int numRows, int numSeats){
        airplane = new Reservation[numRows][numSeats + 1];

        if((numSeats % 2)== 0){
            aisleColumn = (numSeats / 2);
        } else{
            aisleColumn = ((numSeats + 1) / 2);
        }

        for(Reservation[] row: airplane){
            row[aisleColumn] = new Reservation("AISLE", false);
        }
    }

    /**
     * 
     * @return : Returns an ArrayList of strings with all of the names of the passengers on the flight that are frequent flyers. If none, returns as empty.
     */
    public ArrayList<String> getFrequentFlyers(){
        ArrayList<String> frequentFlyers = new ArrayList<String>();

        for (int row = 0; row < this.airplane.length; row++){
            for (int col = 0; col < this.airplane[0].length; col++){
                if(this.airplane[row][col] != null){
                    if(this.airplane[row][col].isFrequentFlyer() == true){
                        frequentFlyers.add(this.airplane[row][col].getPassengerName());
                    }
                }
            }
        }

        return frequentFlyers;
    }

    /**
     * 
     * @param name : The passenger's name as a string.
     * @param freqFlyer : Whether the passenger is a frequent flyer or not as a boolean.
     * @return : Returns true if a seat was reserved for the passenger and false if a seat was not reserved.
     * This method does not change any reservations that were previously made.
     */
    public boolean reserveNextAvailableSeat(String name, boolean freqFlyer){
        boolean result = false;

        for (int row = 0; row < this.airplane.length; row++){
            for (int col = 0; col < this.airplane[0].length; col++){
                if(this.airplane[row][col] == null){
                    this.airplane[row][col] = new Reservation(name, freqFlyer);
                    result = true;
                    return result;
                }
            }
        }

        return result;
    }

    /**
     * 
     * @param passengerName : The name of the first passenger as a string.
     * @param firstFrequentFlyer : Whether the first passenger is a frequent flyer or not as a boolean.
     * @param passengerNameTwo : The name of the second passenger as a string.
     * @param secondFrequentFlyer : Whether the second passenger is a frequent flyer or not as a boolean.
     * @return : Returns true if at least 1 pair of empty adjacent seats exists and were reserved for the passengers. Returns false if no pair of empty adjacent seats exists and no reservations are made.
     * This method does not change any reservations that were previously made.
     */
    public boolean reserveAdjacentSeats(String passengerName, boolean firstFrequentFlyer, String passengerNameTwo, boolean secondFrequentFlyer){
        boolean result = false;

        for (int row = 0; row < this.airplane.length; row++){
            for (int col = 0; col < this.airplane[0].length - 1; col++){
                if(this.airplane[row][col] == null && this.airplane[row][col + 1] == null){
                    this.airplane[row][col] = new Reservation(passengerName, firstFrequentFlyer);
                    this.airplane[row][col + 1] = new Reservation(passengerNameTwo, secondFrequentFlyer);
                    result = true;
                    System.out.println("true");
                    return result;
                }
            }
        }
        System.out.println("false");
        return result;
    }

    /**
     * 
     * @param name : The passenger's name as a string.
     * @param freqFlyer : Whether the passenger is a frequent flyer or not as a boolean.
     * @return : Returns true if at least 1 empty window seat exists and was reserved. Returns false if no empty window seat exists and no reservation was made.
     * This method does not change any reservations that were previously made.
     */
    public boolean reserveWindowSeat(String name, boolean freqFlyer){
        boolean result = false;

        for (int row = 0; row < this.airplane.length; row++){
            for (int col = 0; col < this.airplane[0].length; col++){
                if((col == 0 && this.airplane[row][col] == null)|| (col == this.airplane[0].length - 1 && this.airplane[row][col] == null)){
                    this.airplane[row][col] = new Reservation(name, freqFlyer);
                    result = true;
                    return result;
                }
            }
        }

        return result;
    }

    /**
     * 
     * @param name : The passenger's name as a string.
     * @param freqFlyer : Whether the passenger is a frequent flyer or not as a boolean.
     * @return : Returns true if at least 1 empty aisle seat exists and was reserved. Returns false if no empty aisle seat exists and no reservation was made.
     * This method does not change any reservations that were previously made.
     */
    public boolean reserveAisleSeat(String name, boolean freqFlyer){
        boolean result = false;

        for (int row = 0; row < this.airplane.length; row++){
            for (int col = 0; col < this.airplane[0].length; col++){
                if((col == this.aisleColumn - 1 && this.airplane[row][col] == null)|| (col == this.aisleColumn + 1 && this.airplane[row][col] == null )){
                    this.airplane[row][col] = new Reservation(name, freqFlyer);
                    result = true;
                    return result;
                }
            }
        }

        return result;
    }

    /**
     * 
     * @return : Returns an ArrayList of strings with all of the names of the passengers on the flight that have no adjacent reservations. If none, returns as empty. 
     * Aisle is also counted as a blank passenger, same with window.
     */
    public ArrayList<String> getIsolatedPassengers(){
        ArrayList<String> isolatedPassengers = new ArrayList<String>();
        boolean isLeftEmpty = false;
        boolean isRightEmpty = false;

        for (int row = 0; row < this.airplane.length; row++){
            for (int col = 0; col < this.airplane[0].length; col++){
                if(col == 0 || (col > 0 && (this.airplane[row][col - 1] == null) || (col > 0 && this.airplane[row][col - 1].getPassengerName().equals("AISLE")))){
                    isLeftEmpty = true;
                }

                if(col == this.airplane[0].length - 1 || (col < this.airplane[0].length - 1 && (this.airplane[row][col + 1] == null) || (col < this.airplane[0].length - 1 && (this.airplane[row][col + 1].getPassengerName().equals("AISLE"))))){
                    isRightEmpty = true;
                }

                if(isLeftEmpty == true && isRightEmpty == true && this.airplane[row][col] != null && this.airplane[row][col].getPassengerName() != "AISLE"){
                    isolatedPassengers.add(this.airplane[row][col].getPassengerName());
                }
                isLeftEmpty = false;
                isRightEmpty = false;
            }
        }
        return isolatedPassengers;
    }

    /**
     * @return : Returns a string of the Reservations on the flight. Each aisle is represented as "AISLE," each passenger is represented by their name as a string, and empty seats/nulls are represented as string "EMPTY." A single space between each seat.
     */
    public String toString(){
        String airplaneString = "";

        for (int row = 0; row < this.airplane.length; row++){
            for (int col = 0; col < this.airplane[0].length; col++){
                if(col != 0){
                    airplaneString += " ";
                }

                if(this.airplane[row][col] == null){
                    airplaneString += "EMPTY";
                } else{
                    airplaneString += this.airplane[row][col].getPassengerName();
                }

                if(col == this.airplane[0].length - 1){
                    airplaneString += "\n";
                }
            }
        }

        return airplaneString;
    }

    /**
     * 
     * @return : Returns the 2d array of the seats on the airplane.
     */
    public Reservation[][] getSeats(){
        return airplane;
    }
}