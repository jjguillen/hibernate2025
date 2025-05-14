package entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company")
    //mappedBy --> nombre del atributo en la clase Employee que hace referencia a Company
    //, al @ManyToOne
    //FetchType.EAGER --> cada vez que se carga la Company se cargan todos los employees
    //Esto haría un JOIN de todas las tablas, y empeoraría el rendimiento
    //FetchType.LAZY --> cada vez que carga Company solo se traen sus datos
    //Cuando quiero sacar los empleados, en ese momento se hace un nuevo Select
    private Set<Employee> employees = new HashSet<>();

    public Company() {
    }

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Company{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
