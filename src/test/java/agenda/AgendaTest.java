/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.net.UnknownHostException;
import java.text.ParseException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

/**
 *
 * @author Sara
 */
public class AgendaTest {
    
    private Agenda agenda;
    private AgendaCollection collection;
    
    @Before
    public void before() throws UnknownHostException{
        collection = mock(AgendaCollection.class);
        agenda = new Agenda(collection);
        doReturn(true).when(collection).saveAppointment(any(Appointment.class));
    }
    
    @Test
    public void WhenSundayThenIsHolidayTrue() throws ParseException {
        assertTrue(agenda.isHoliday("30-09-2018"));
    }   
    
    @Test
    public void WhenWednesdayThenIsHolidayFalse() throws ParseException{
        assertFalse(agenda.isHoliday("26-09-2018"));
        
    }
    
    @Test
    public void WhenShowAppointmentsThenEmptyString(){
        assertThat(agenda.showAppointments("01-10-2018"), equalTo(""));
        
    }
    
    @Test
    public void WhenSetAppointmentThenShow(){
        agenda.setAppointment(new Appointment("02-10-2018", "09:00", "dentist"));
        String actual = agenda.showAppointments("02-10-2018");
        assertThat(actual, equalToIgnoringWhiteSpace("02-10-2018 09:00 : dentist"));
    }
    
    @Test
    public void WhenSetAppointmentsThenShow(){
        agenda.setAppointment(new Appointment("03-10-2018", "09:00", "software engineering lecture"));
        agenda.setAppointment(new Appointment("05-10-2018", "17:00", "meeting"));
        String actual1, actual2, expected1, expected2;
        actual1 = agenda.showAppointments("03-10-2018");
        actual2 = agenda.showAppointments("05-10-2018");
        expected1 = "03-10-2018 09:00 : software engineering lecture";
        expected2 = "05-10-2018 17:00 : meeting";
        assertThat(actual1, equalToIgnoringWhiteSpace(expected1));
        assertThat(actual2, equalToIgnoringWhiteSpace(expected2));
    }
    
    @Test
    public void WhenSetAppointmentsSameDayThenShow(){
        agenda.setAppointment(new Appointment("05-10-2018", "09:00", "yoga class"));
        agenda.setAppointment(new Appointment("05-10-2018", "17:00", "meeting"));
        String actual = agenda.showAppointments("05-10-2018");
        String expected = "05-10-2018 09:00 : yoga class 05-10-2018 17:00 : meeting";
        assertThat(actual, equalToIgnoringWhiteSpace(expected));
    }
    
    @Test(expected = RuntimeException.class)
    public void WhenAppointmentsSameHourThenException(){
        agenda.setAppointment(new Appointment("02-10-2018", "09:00", "exam"));
        agenda.setAppointment(new Appointment("02-10-2018", "09:00", "beakfast with friends"));
    }
    
    @Test
    public void WhenInstantiatedThenSetCollection(){
        assertNotNull(agenda.getAgendaCollection());
    }
    
    @Test
    public void WhenSetAppointmentThenSaveAppointment(){
        Appointment appointment = new Appointment("28-09-2018", "20:00", "cinema with friends");
        agenda.setAppointment(appointment);
        verify(collection).saveAppointment(appointment);
    }
    
    @Test(expected = RuntimeException.class)
    public void WhenSaveAppointmentReturnsFalseThenThrowException(){
        doReturn(false).when(collection).saveAppointment(any(Appointment.class));
        agenda.setAppointment(new Appointment("29-10-2018", "09:00", "tennis match"));
    }
    
    @Test
    public void WhenShowAppointmentsThenFindAppointments(){
        agenda.showAppointments("26-09-2018");
        verify(collection).findAppointments("26-09-2018");
    }
}
