package com.sky.service;

import com.sky.entity.AddressBook;
import java.util.List;

public interface AddressBookService {

    /**
     * 条件查询.
     *
     * @param addressBook 地址簿
     * @return 地址簿列表
     */
    List<AddressBook> list(AddressBook addressBook);

    /**
     * 新增.
     *
     * @param addressBook 地址簿
     */
    void save(AddressBook addressBook);

    /**
     * 根据id查询.
     *
     * @param id 地址id
     * @return 地址簿
     */
    AddressBook getById(Long id);

    /**
     * 根据id修改.
     *
     * @param addressBook 地址簿
     */
    void update(AddressBook addressBook);

    /**
     * 设置默认地址.
     *
     * @param addressBook 地址簿
     */
    void setDefault(AddressBook addressBook);

    /**
     * 根据id删除.
     *
     * @param id 地址id
     */
    void deleteById(Long id);

}
