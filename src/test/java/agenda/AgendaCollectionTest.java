/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import org.junit.*;
import com.mongodb.MongoException;
import java.net.UnknownHostException;
import org.jongo.MongoCollection;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import  org.mockito.*;
import static org.mockito.Mockito.*;
import  org.jongo.Find;
import org.junit.runner.RunWith;       
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Sara
 */
@RunWith(MockitoJUnitRunner.class)
public class AgendaCollectionTest {
    private Appointment appointment;
    
    @Before
    public void before(){
        appointment = new Appointment("27-10-2018", "20:00", "Dinner with friends");
    }
    
    
    @Spy
    AgendaCollection collection;
    
    @Mock
    MongoCollection mongoCollection;
    

    @Test
    public void WhenInstantiatedThenMongoHasDBNameAgendaDB(){
      String actual = collection.getMongoCollection().getDBCollection().getDB().getName();
      assertThat(actual, equalTo("AgendaDB"));
  }
  
    @Test
    public void WhenInstantiatedThenMongoCollectionHasNameAppointments(){
        assertThat(collection.getMongoCollection().getName(), equalTo("Appointments"));
    }
    
    @Test
    public void WhenSaveAppointmentThenInvokeMongoCollectionSave(){
        doReturn(mongoCollection).when(collection).getMongoCollection();
        collection.saveAppointment(appointment);
        verify(mongoCollection, times(1)).save(appointment);
    }
    
    @Test
    public void WhenSaveAppointmentThenReturnTrue(){
        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertTrue(collection.saveAppointment(appointment));
    }
    
    @Test
    public void GivenExceptionWhenSaveAppointmentThenReturnFalse(){
        doThrow(new MongoException("Error")).when(mongoCollection).save(appointment);
        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertFalse(collection.saveAppointment(appointment));
    }
    
    @Test
    public void WhenFindAppointmentsThenInvokeMongoCollectionFind(){
        String date = "25-10-2018";
        doReturn(mongoCollection).when(collection).getMongoCollection();
        Find findResult = mock(Find.class);
        doReturn(findResult).when(mongoCollection).find("{date:#}", date);
        collection.findAppointments(date);
        verify(mongoCollection).find("{date:#}", date);
    }
}
