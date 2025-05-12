import entities.Employee;
import org.hibernate.Session;

public class App {
    public static void main(String[] args) {

        Session sesion = HibernateUtil.getSessionFactory().openSession();

        //Iniciar transacción
        sesion.beginTransaction();

        //Operaciones
        Employee employee = new Employee("Ana", 25);
        sesion.persist(employee); //Insertar en la BBDD

        Employee employee2 = new Employee("Maria", 26);
        sesion.persist(employee2);

        //Cerrar transacción
        sesion.getTransaction().commit();

        //Cerrar sesión a la BBDD
        sesion.close();

    }
}
