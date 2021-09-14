package jpa.dao;

import jpa.domain.Appointment;
import jpa.domain.User;
import jpa.domain.Worker;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class AppointmentDao extends AbstractJpaDao<Long, Appointment>{
    private EntityManager manager;
    protected Logger log = LogManager.getLogger("Logger");

    public AppointmentDao(EntityManager manager) {

        super(Appointment.class);
    this.manager = manager;
    }

    /* DEPRECATED Utiliser save
    public void createAppointment(String title, Worker organizer){

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            manager.persist(new Appointment(title,organizer));
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

    }

*/

    public void addParticipant(Appointment appointment, User user){
        EntityTransaction tx = manager.getTransaction();
        try {
            appointment.setParticipant(user);
            this.update(appointment);
        }
            /*
            Appointment oldAppointment = manager.find(Appointment.class, appointment);
            System.out.println(oldAppointment.getParticipant());
            if(oldAppointment.getParticipant() == null){
                oldAppointment.setParticipant(user);
            }
            else {
                log.info("The appointment " + oldAppointment.getTitle() + "organized by " + oldAppointment.getOrganizer() + " was already reserved");
            }
            */

         catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Appointment> getAppointment(Worker worker){
        String texte = "select a from Appointment a where a.organizer = 'worker' ";
        Query query = manager.createQuery(texte);
        return query.getResultList();

    }
}