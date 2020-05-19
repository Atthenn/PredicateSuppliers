package com.company;

import sun.lwawt.macosx.CSystemTray;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee red = new Employee("Red RidingHood", 35);
        Employee charming = new Employee("Prince Charming", 31);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charming);

        Function<Employee,String> getLastName = (Employee employee) -> {
            return employee.getName().substring(employee.getName().indexOf(" ")+1);
        };
        Function<Employee,String> getFirstName = (Employee employee) -> {
            return employee.getName().substring(0, employee.getName().indexOf(" "));
        };


        Random random1= new Random();
        for(Employee employee:employees){
            if(random1.nextBoolean())
            getName(getFirstName,employee);
            else
                getName(getLastName,employee);
        }
        employees.forEach(employee ->
                System.out.println(getLastName.apply(employee)));

//        printEmployeesByAge(employees, "Employees over 30", employee -> employee.getAge() > 30);
//        printEmployeesByAge(employees, "\nEmployees 30 and under", employee ->employee.getAge() <= 30);


        employees.forEach(employee -> {
            if (employee.getAge() > 30) {
                System.out.println(employee.getName());
            }
        });

        printEmployeesNameByAge(employees,"age > 30", employee -> employee.getAge()>30);
        printEmployeesNameByAge(employees,"age < 30", employee -> employee.getAge()<=30);
        printEmployeesNameByAge(employees, "age < 25", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge()<25;
            }
        });

        IntPredicate intp = i -> i>15;
        IntPredicate intp2 = i -> i<150;

        int a =10;
      //  System.out.println(intp.test(a+5)&intp2.test(145));
        System.out.println(intp.and(intp2).test(90));

        Random random = new Random();
        Supplier<Integer> supplier = () -> random.nextInt(1000);
        for (int i = 0; i <10 ; i++) {
            System.out.println(random.nextInt(15));
            System.out.println(supplier.get());

        }

        employees.forEach(employee -> {
            String lastName = employee.getName().substring(employee.getName().indexOf(" ")+1);
            System.out.println("last name: " +lastName);
        });
    }
    private static String getName(Function<Employee,String> getName,Employee employee){
        return getName.apply(employee);
    }

    private static void printEmployeesNameByAge(List<Employee> employees,
                                                String ageText,
                                                Predicate<Employee> ageCondition){

        System.out.println(ageText);
        for(Employee employee: employees){
            if (ageCondition.test(employee)){
                System.out.println(employee.getName());
            }
        }
    }


 //   private static void printEmployeesByAge(List<Employee> employees,
//                                            String ageText,
//                                            Predicate<Employee> ageCondition) {
//
//        System.out.println(ageText);
//        System.out.println("==================");
//        for(Employee employee : employees) {
//            if (ageCondition.test(employee)) {
//                System.out.println(employee.getName());
//            }
//        }
//    }
}
