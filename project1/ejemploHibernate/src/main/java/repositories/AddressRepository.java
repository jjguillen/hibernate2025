package repositories;

import entities.Address;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;

public class AddressRepository {

    private Session session;

    public AddressRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public void insert(Address address){
        session.beginTransaction();
        session.persist(address);
        session.getTransaction().commit();
    }

    public void update(Address address){
        session.beginTransaction();
        session.merge(address);
        session.getTransaction().commit();
    }

    public void delete(Address address){
        session.beginTransaction();
        session.remove(address);
        session.getTransaction().commit();
    }

    public Address findById(Long id){
        return session.find(Address.class, id);
    }

    public List<Address> findAll() {
        return session.createQuery("SELECT a FROM Address a", Address.class)
                .getResultList();
    }

    public void close(){
        session.close();
    }
}
