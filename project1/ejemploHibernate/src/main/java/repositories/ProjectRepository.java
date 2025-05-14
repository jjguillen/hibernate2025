package repositories;

import entities.Company;
import entities.Project;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;

public class ProjectRepository {

    private Session session;

    public ProjectRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public void insert(Project project){
        session.beginTransaction();
        session.persist(project);
        session.getTransaction().commit();
    }

    public void update(Project project){
        session.beginTransaction();
        session.merge(project);
        session.getTransaction().commit();
    }

    public void delete(Project project){
        session.beginTransaction();
        session.remove(project);
        session.getTransaction().commit();
    }

    public Project findById(Long id){
        return session.find(Project.class, id);
    }

    public List<Project> findAll() {
        return session.createQuery("SELECT p FROM Project p", Project.class)
                .getResultList();
    }

    public void close(){
        session.close();
    }

}
