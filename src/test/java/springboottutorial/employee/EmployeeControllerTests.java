package springboottutorial.employee;

import com.example.springboottutorial.employee.Employee;
import com.example.springboottutorial.employee.EmployeeController;
import com.example.springboottutorial.employee.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeControllerTests
{
    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeService service;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddEmployee()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(service.addEmployee(any(Employee.class))).thenReturn(true);

        Employee employee = new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
        ResponseEntity<Object> responseEntity = employeeController.addEmployee(employee);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }

    @Test
    public void testFindAll()
    {
        // given
        Employee employee1 = new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
        Employee employee2 = new Employee(2, "Alex", "Gussin", "example@gmail.com");
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee1);
        employees.add(employee2);

        when(service.getEmployeeList()).thenReturn(employees);

        // when
        List<Employee> result = employeeController.getEmployees();

        // then
        assertThat(result.size()).isEqualTo(2);

        assertThat(result.get(0).getFirstName())
                .isEqualTo(employee1.getFirstName());

        assertThat(result.get(1).getFirstName())
                .isEqualTo(employee2.getFirstName());
    }
}
