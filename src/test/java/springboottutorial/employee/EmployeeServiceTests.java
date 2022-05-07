package springboottutorial.employee;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.springboottutorial.employee.Employee;
import com.example.springboottutorial.employee.EmployeeRepository;
import com.example.springboottutorial.employee.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTests {

    @InjectMocks
    EmployeeService service;

    @Mock
    EmployeeRepository repository;

//    @Before
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    public void getAllEmployeesTest()
    {
        List<Employee> list = new ArrayList<Employee>();
        Employee empOne = new Employee(1, "John", "John", "howtodoinjava@gmail.com");
        Employee empTwo = new Employee(2, "Alex", "kolenchiski", "alexk@yahoo.com");
        Employee empThree = new Employee(3, "Steve", "Waugh", "swaugh@gmail.com");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(repository.getEmployeeList()).thenReturn(list);

        //test
        List<Employee> empList = service.getEmployeeList();

        assertEquals(3, empList.size());
        verify(repository, times(1)).getEmployeeList();
    }

    @Test
    public void getEmployeeByIdTest()
    {
        when(repository.getEmployeeById(1)).thenReturn(new Employee(1,"Lokesh","Gupta","user@email.com"));

        Employee emp = service.getEmployeeById(1);

        assertEquals("Lokesh", emp.getFirstName());
        assertEquals("Gupta", emp.getLastName());
        assertEquals("user@email.com", emp.getEmail());
    }

    @Test
    public void createEmployeeTest()
    {
        Employee emp = new Employee(1,"Lokesh","Gupta","user@email.com");

        service.addEmployee(emp);

        verify(repository, times(1)).addEmployee(emp);
    }
}