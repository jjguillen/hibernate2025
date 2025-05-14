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

        Address ad1 = new Address("Avenida Federico", "Vera", "España",
                "28000");
        Address ad2 = new Address("Calle Vicente Alexandre", "Garrucha",
                "España","28050");
        Address ad3 = new Address("Calle Granada", "Almería",
                "España","28080");
        //aRepo.insert(ad1);
        //aRepo.insert(ad2);
        //aRepo.insert(ad3);

        aRepo.findAll().forEach(System.out::println);

        Employee emp1 = new Employee("Pascual", 25, ad1);
        eRepo.insert(emp1);

        Employee emp2 = new Employee("Nuria", 26, ad2);
        eRepo.insert(emp2);

        Employee emp3 = new Employee("Marc", 21, ad3);
        eRepo.insert(emp3);

        emp2.setName("Sara");
        eRepo.update(emp2);

        //eRepo.delete(emp2);

        eRepo.findAll().forEach(System.out::println);
        //System.out.println(eRepo.findById(1L));

        eRepo.findByAgeGreaterThan(24).forEach(System.out::println);

        System.out.println("Ahora con Streams ----------------");

        //eRepo.findAll().stream()
        //        .filter(e -> e.getAge() > 24)
        //        .forEach(System.out::println);


        emp3.setAddress(new Address("Otra casa","Cuevas","España","30303"));
        //emp3.setAddress(ad1);
        eRepo.update(emp3);

        System.out.println("------------");
        Employee nuevo = eRepo.findById(3L);
        System.out.println(nuevo.getAddress());

        //Cerrar sesión BBDD
        eRepo.close();
        aRepo.close();

    }
}
