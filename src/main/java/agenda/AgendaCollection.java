/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;
import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.util.ArrayList;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.net.UnknownHostException;


/**
 *
 * @author Sara
 */
public class AgendaCollection {
    private MongoCollection mongoCollection;
    
    protected MongoCollection getMongoCollection(){
        return mongoCollection;
    }
    
    public AgendaCollection() throws UnknownHostException {
        DB db = new MongoClient().getDB("AgendaDB");
        mongoCollection = new Jongo(db).getCollection("Appointments");
    }
    
    public boolean saveAppointment(Appointment appointment){
        try {            
            getMongoCollection().save(appointment);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    
    public ArrayList findAppointments(String date){
        Iterable<Appointment> found = getMongoCollection().find("{date:#}", date).as(Appointment.class);
        ArrayList<Appointment> found_list = new ArrayList();
        if(found != null) found.forEach(found_list :: add);
        return found_list;
    }
    
}
