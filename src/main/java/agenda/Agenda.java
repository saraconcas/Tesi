/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import com.google.common.collect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.net.UnknownHostException;

/**
 *
 * @author Sara
 */
public class Agenda {
    private AgendaCollection agendaCollection;
    
    protected AgendaCollection getAgendaCollection(){
        return agendaCollection;
    }
    
    public Agenda() throws UnknownHostException {
        this(new AgendaCollection());        
    }
    
    protected Agenda(AgendaCollection collection){
        agendaCollection = collection;
    }
    
    private ArrayList<Appointment> appointments = new ArrayList();
    
    SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy", Locale.UK); 
    SimpleDateFormat format2 = new SimpleDateFormat("EEEE dd-MM-yyyy", Locale.UK);
    
        public boolean isHoliday(String date) throws ParseException{
            Date date_format = format1.parse(date);
            String date_string = format2.format(date_format);
            return date_string.startsWith("Sunday");
        }
        
        public String showAppointments(String date){
            ArrayList<Appointment> app_db = agendaCollection.findAppointments(date);
            appointments.addAll(app_db);
            String list = "";
            for(Appointment app: appointments){
                if(app.getDate().equals(date)) list += app.toString();
            }
            return list;
        }
        
        public void setAppointment(Appointment appointment){
            boolean flag;
            for (Appointment app : appointments){
                if(app.getDate().equals(appointment.getDate())&& 
                        app.getHour().equals(appointment.getHour()))
                    throw new RuntimeException("There is already an appointment at this hour");
            }
            this.appointments.add(appointment);
            flag = agendaCollection.saveAppointment(appointment);
            if(!flag) throw new RuntimeException("saving on db failed");
        }
}
