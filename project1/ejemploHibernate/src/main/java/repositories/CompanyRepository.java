package repositories;

import entities.Address;
import entities.Company;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;

public class CompanyRepository {

    private Session session;

    public CompanyRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public void insert(Company company){
        session.beginTransaction();
        session.persist(company);
        session.getTransaction().commit();
    }

    public void update(Company company){
        session.beginTransaction();
        session.merge(company);
        session.getTransaction().commit();
    }

    public void delete(Company company){
        session.beginTransaction();
        session.remove(company);
        session.getTransaction().commit();
    }

    public Company findById(Long id){
        return session.find(Company.class, id);
    }

    public List<Company> findAll() {
        return session.createQuery("SELECT c FROM Company c", Company.class)
                .getResultList();
    }

    public void close(){
        session.close();
    }
}
