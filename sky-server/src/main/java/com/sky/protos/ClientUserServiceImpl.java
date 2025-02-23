package com.sky.protos;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;
import com.sky.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
// @DubboService
@Service
public class ClientUserServiceImpl
        extends DubboUserServiceTriple.UserServiceImplBase {
    /**
     * Employee service instance.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * Register a new user.
     *
     * @param request registration request
     * @return user id
     */
    @Override
    public RegisterResp register(final RegisterReq request) {
        final long defaultId = 1L;
        final String defaultIdNumber = "123";
        final String defaultSex = "1";
        log.info("register: {}", request);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(defaultId);
        employeeDTO.setUsername(request.getEmail());
        employeeDTO.setName(request.getEmail());
        employeeDTO.setPhone(request.getEmail());
        employeeDTO.setSex(defaultSex);
        employeeDTO.setIdNumber(defaultIdNumber);

        Long id = employeeService.addEmployee(employeeDTO);
        return RegisterResp.newBuilder()
                .setUserId(id.intValue())
                .build();
    }

    /**
     * Login with user credentials.
     *
     * @param request login request
     * @return user id
     */
    @Override
    public LoginResp login(final LoginReq request) {
        log.info("login: {}", request);
        EmployeeLoginDTO employeeLoginDTO = new EmployeeLoginDTO();
        employeeLoginDTO.setUsername(request.getEmail());
        employeeLoginDTO.setPassword(request.getPassword());
        Employee employee = employeeService.login(employeeLoginDTO);
        return LoginResp.newBuilder()
                .setUserId(employee.getId().intValue())
                .build();
    }
}
