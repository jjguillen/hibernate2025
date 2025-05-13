package repositories;

import entities.Employee;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;

public class EmployeeRepository {

    private Session session;

    public EmployeeRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * MÉTODOS CRUD: findAll, findByPk, insert, update, delete
     */

    /**
     * Insertar Employee en BBDD
     * @param employee
     */
    public void insert(Employee employee) {
        session.beginTransaction();
        session.persist(employee);
        session.getTransaction().commit();
    }

    /**
     * Update en BBDD el Employee con el id employee.id, y modifica
     * todos los valores con los de employee.*
     * @param employee
     */
    public void update(Employee employee) {
        session.beginTransaction();
        session.merge(employee);
        session.getTransaction().commit();
    }

    /**
     * Remove Employee by employee.id (DELETE FROM employees WHERE id = employee.id)
     * @param employee
     */
    public void delete(Employee employee) {
        session.beginTransaction();
        session.remove(employee);
        session.getTransaction().commit(); //Confirmar cambios en BBDD
    }

    /**
     * Get all Employees from BBDD
     * @return
     */
    public List<Employee> findAll(){
        return session
                .createQuery("SELECT e FROM Employee e", Employee.class)
                .getResultList();
    }

    /**
     * Return Employee with pk = id
     * @param id
     * @return
     */
    public Employee findById(Long id){
        return session.find(Employee.class, id);
    }

    public List<Employee> findByAgeGreaterThan(int age){
        return session.createQuery("SELECT e FROM Employee e WHERE e.age > :age", Employee.class)
                .setParameter("age", age)
                .getResultList();
    }

    public void close(){
        session.close();
    }


}
