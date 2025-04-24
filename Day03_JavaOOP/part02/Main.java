package Day03_JavaOOP.part02;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // implement encapsulation
        Employee emp1 = new Employee(); //default/empty constructor
        emp1.setEmpId(101);
        emp1.setFullName("Anton");
        emp1.setHireDate(LocalDate.of(2025,4,12));
        emp1.setRole("Programmer");
        emp1.setSalary(6_000_000);

        //System.out.println(emp1.toString());

        Employee emp2 = new Employee(102,"Budi",
                LocalDate.of(2025,1,12),
                "PROGRAMMER",6_000_000);

        //System.out.println(emp2.toString());

        Employee emp3 = new Employee("Rini",
                LocalDate.of(2025,1,12),
                "SALES",6_000_000);

        //System.out.println(emp3.toString());

        List<Employee> listEmployee= List.of(emp1,emp2,emp3);
        for (Employee emp : listEmployee){
            System.out.println(emp.toString());
        }
    }
}