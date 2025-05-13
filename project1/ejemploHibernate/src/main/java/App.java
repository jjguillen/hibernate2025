import entities.Address;
import entities.Employee;
import org.hibernate.Session;
import repositories.AddressRepository;
import repositories.EmployeeRepository;
import utils.HibernateUtil;

public class App {
    public static void main(String[] args) {

        //Creamos repositorios
        EmployeeRepository eRepo = new EmployeeRepository();
        AddressRepository aRepo = new AddressRepository();

        Employee emp1 = new Employee("Pascual", 25);
        eRepo.insert(emp1);

        Employee emp2 = new Employee("Nuria", 26);
        eRepo.insert(emp2);

        Employee emp3 = new Employee("Marc", 21);
        eRepo.insert(emp3);

        emp2.setName("Sara");
        eRepo.update(emp2);

        //eRepo.delete(emp2);

        eRepo.findAll().forEach(System.out::println);
        //System.out.println(eRepo.findById(1L));

        eRepo.findByAgeGreaterThan(24).forEach(System.out::println);

        System.out.println("Ahora con Streams ----------------");

        eRepo.findAll().stream()
                .filter(e -> e.getAge() > 24)
                .forEach(System.out::println);


        aRepo.insert(new Address("Avenida Federico", "Vera", "España",
                "28000"));
        aRepo.insert(new Address("Calle Vicente Alexandre", "Garrucha",
                "España","28050"));

        aRepo.findAll().forEach(System.out::println);


        //Cerrar sesión BBDD
        eRepo.close();
        aRepo.close();

    }
}
