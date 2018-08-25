/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

/**
 *
 * @author Sara
 */
public class Appointment {
    private String date;
    private String hour;
    private String description;
    
    public Appointment(){
        
    }
    
    public Appointment(String date, String hour, String description){
        this.date = date;
        this.hour = hour;
        this.description = description;
    }
    
    public String getDate(){
        return this.date;
    }
    
    public String getHour(){
        return this.hour;
    }
    
    @Override
    public String toString(){
        return(this.date + " " + this.hour + " : " + this.description + " ");
    }
    
}
