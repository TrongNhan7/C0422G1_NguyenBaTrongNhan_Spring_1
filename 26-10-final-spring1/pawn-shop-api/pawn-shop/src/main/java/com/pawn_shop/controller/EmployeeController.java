package com.pawn_shop.controller;

import com.pawn_shop.dto.EmployeeDto;
import com.pawn_shop.dto.projection.IEmployeeDto;
import com.pawn_shop.model.employee.Employee;
import com.pawn_shop.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")

public class EmployeeController {
    @Autowired
    private IEmployeeService iEmployeeService;

    @GetMapping("/list")
    public ResponseEntity<Page<IEmployeeDto>> getAllListEmployee(@PageableDefault(value = 5) Pageable pageable,
                                                                 @RequestParam Optional<String> name,
                                                                 @RequestParam Optional<String> employeeCode) {

        String searchKeyWordName = name.orElse("");
        String searchKeyWordCode = employeeCode.orElse("");
        Page<IEmployeeDto> employeeDtos = iEmployeeService.getAllEmployeeSearch(searchKeyWordName, searchKeyWordCode, pageable);
        if (employeeDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);


    }
    
    @PatchMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Long id) {
        Employee employee1 = this.iEmployeeService.findById(id);

        if (Objects.equals(id, employee1.getId())) {
            iEmployeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.OK);

        }
        return new ResponseEntity<>(employee1, HttpStatus.BAD_REQUEST);
    }
        
    @GetMapping("/{id}")
    public ResponseEntity<Employee> findByIdEmployee(@PathVariable Long id){
        Employee employee= this.iEmployeeService.findById(id);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<Void>updateById(@RequestBody @Valid EmployeeDto employeeDto, BindingResult bindingResult){
        new EmployeeDto().validate(employeeDto,bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeDto, employee);
        this.iEmployeeService.update(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/findUser/{user}")
    public ResponseEntity<IEmployeeDto> findByUser(@PathVariable String user) {
        IEmployeeDto employee = this.iEmployeeService.findByUser(user);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

}
