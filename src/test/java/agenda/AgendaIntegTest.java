/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import org.junit.Test;
import static org.junit.Assert.*;
import java.net.UnknownHostException;
import static org.hamcrest.Matchers.*;
/**
 *
 * @author Sara
 */
public class AgendaIntegTest {
    
    @Test
    public void GivenMongoDBRunningWhenSetAppointmentThenNoException()
        throws UnknownHostException {
        Agenda agenda = new Agenda();
        agenda.setAppointment(new Appointment("31-10-2018", "15:00", "lecture"));
          agenda.setAppointment(new Appointment("30-10-2018", "9:00" , "work"));
          agenda.setAppointment(new Appointment("31-10-2018", "23:00" , "party")); 
    }
    
    @Test
    public void GivenMongoDBRunningThenShowAppointments()throws UnknownHostException{
        Agenda agenda = new Agenda();
        agenda.showAppointments("25-09-2018");
        String actual = agenda.showAppointments("31-10-2018");
        String expected = "31-10-2018 15:00 : lecture 31-10-2018 23:00 : party";
        assertThat(actual, equalToIgnoringWhiteSpace(expected));

    }
}

