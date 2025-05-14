import entities.Address;
import entities.Company;
import entities.Employee;
import entities.Project;
import org.hibernate.Session;
import repositories.AddressRepository;
import repositories.CompanyRepository;
import repositories.EmployeeRepository;
import repositories.ProjectRepository;
import utils.HibernateUtil;

public class App {
    public static void main(String[] args) {

        //Creamos repositorios
        EmployeeRepository eRepo = new EmployeeRepository();
        AddressRepository aRepo = new AddressRepository();
        var cRepo = new CompanyRepository();
        var pRepo = new ProjectRepository();

        //Insercción de proyectos
        var pr1 = new Project("IoT PrimaFlor", 6,
                "Proyecto IoT", "En desarrollo");
        var pr2 = new Project("Intranet PrimaFlor", 6,
                "Intranet PF", "Iniciado");
        pRepo.insert(pr1);
        pRepo.insert(pr2);

        //Creación de empresa, persiste con el empleado
        Company c1 = new Company("PrimaFlor S.L.", "Address 1");
        //cRepo.insert(c1);

        //Creación de direcciones, persiste con el empleado
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

        //Creación de empleados
        Employee emp1 = new Employee("Pascual", 25, ad1);
        emp1.setCompany(c1);
        eRepo.insert(emp1);

        Employee emp2 = new Employee("Nuria", 26, ad2);
        emp2.setCompany(c1);
        eRepo.insert(emp2);

        Employee emp3 = new Employee("Marc", 21, ad3);
        emp3.setCompany(c1);
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

        System.out.println("--- Address empleado ---");
        Employee nuevo = eRepo.findById(3L);
        System.out.println(nuevo.getAddress());
        System.out.println(nuevo.getCompany());

        System.out.println("--- Company ----");
        Company comp1 = cRepo.findById(1L);
        System.out.println(comp1);
        System.out.println(comp1.getEmployees());
        //comp1.getEmployees().forEach(System.out::println);


        //Añadir empleados a un proyecto
        pr1.getEmployees().add(emp1);
        pr2.getEmployees().add(emp2);
        pr2.getEmployees().add(emp3);
        pRepo.update(pr1);

        pRepo.findById(2L).getEmployees().forEach(System.out::println);

        //Cerrar sesión BBDD
        eRepo.close();
        aRepo.close();

    }
}
