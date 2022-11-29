package com.pawn_shop.service;


import com.pawn_shop.dto.projection.IEmployeeDto;
import com.pawn_shop.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Page<IEmployeeDto> getAllEmployeeSearch(String searchKeyWordName, String searchKeyWordCode,Pageable pageable);

    void deleteEmployee(Long id);
   
    void update(Employee employee);

    IEmployeeDto findByUser(String user);

    Employee findById(Long id);


}
