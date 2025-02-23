package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录.
     *
     * @param employeeLoginDTO 登录信息
     * @return 员工信息
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * Add an employee.
     *
     * @param employeeDTO 员工信息
     * @return 员工id
     */
    Long addEmployee(EmployeeDTO employeeDTO);

    /**
     * 分页查询员工.
     *
     * @param employeePageQueryDTO 分页查询条件
     * @return 分页查询结果
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * Enable or disable an employee account.
     *
     * @param status 状态
     * @param id     员工id
     */
    void enableOrDisableEmployee(Integer status, Long id);

    /**
     * Edit employee information.
     *
     * @param employeeDTO 员工信息
     */
    void updateEmployee(EmployeeDTO employeeDTO);

    /**
     * Query an employee by id.
     *
     * @param id 员工id
     * @return 员工信息
     */
    Employee getEmployeeById(Long id);
}
