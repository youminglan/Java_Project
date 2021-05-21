package com.repairsystem.service.Impl;

import com.repairsystem.dao.AdministratorMapper;
import com.repairsystem.entity.Administrator;
import com.repairsystem.exception.AdministratorIdIsNullException;
import com.repairsystem.exception.AdministratorNameIsNullException;
import com.repairsystem.exception.AdministratorPasswordIsNullException;
import com.repairsystem.exception.AdministratorPhoneIsNullException;
import com.repairsystem.service.AdministratorService;
import com.repairsystem.utils.PasswordEncryptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author youminglan
 * @date 2021/04/20
 * @time 19:54
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorMapper adminMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Administrator> searchAllAdministrator() {
        return adminMapper.selectAll();

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public String countAllAdministrator() {

        String count = adminMapper.getAdministratorCount().toString();

        return count;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Administrator searchAdministratorById(Integer id) {

        if (StringUtils.isBlank(id.toString())) {
            throw new AdministratorIdIsNullException("传入的管理员ID为空");
        }
        return adminMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Administrator> searchAdministratorByName(String name) {

//        由于可能会出现形参id为空的可能性，所以我们需要做一个自定义异常
//        在com.repairsystem.exception创建自定义异常AdministratorIdIsNullException

        if (StringUtils.isBlank(name)) {
            throw new AdministratorNameIsNullException("传入的管理员姓名为空");
        }

        Example example = new Example(Administrator.class);
        example.createCriteria().andLike("adminName", "%" + name + "%");

        return adminMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Administrator searchAdministratorByPhoneNum(String phoneNum) {
        Example example = new Example(Administrator.class);
        example.createCriteria().andEqualTo("adminPhone", phoneNum);
        return adminMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Administrator loginAdministrator(String phone, String password) {

        if (StringUtils.isBlank(phone)) {
            throw new AdministratorPhoneIsNullException("传入的管理员电话号码为空");
        }

        if (StringUtils.isBlank(password)) {
            throw new AdministratorPasswordIsNullException("传入的管理员密码为空");
        }

//        在管理员登录的时候，我们需要为密码进行MD5加密，毕竟如果数据库中的密码是原密码的话，
//        数据库管理员就能够直接看到，数据的私密性就没有了，所以需要加上一层加密。
//        由于MD5的加密是不可逆的，也就是说密码加密后无法反向破解，我们在其他网站修改密码的时候，
//        并没有提供当前密码显示的功能，也是因为这个原因。

        password = PasswordEncryptionUtils.plainText2MD5Encrypt(password);
        Example example = new Example(Administrator.class);
        example.createCriteria().andEqualTo("adminPhone", phone).andEqualTo("adminPassword", password);

        Administrator admin = adminMapper.selectOneByExample(example);

        return admin;

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean administratorPhoneNumberIsExist(String number) {

        if (StringUtils.isBlank(number)) {
            throw new AdministratorPhoneIsNullException("传入的管理员密码为空");
        }

        Administrator admin = new Administrator();
        admin.setAdminPhone(number);

        Administrator result = adminMapper.selectOne(admin);

        boolean flag = result != null;
        return flag;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveAdministrator(Administrator admin) {

        String password = admin.getAdminPassword();
        admin.setAdminPassword(PasswordEncryptionUtils.plainText2MD5Encrypt(password));

        adminMapper.insert(admin);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateAdministrator(Administrator admin) {

        if (StringUtils.isBlank(admin.getAdminId().toString())) {
            throw new AdministratorIdIsNullException("传入的管理员ID为空");
        }

        adminMapper.updateByPrimaryKeySelective(admin);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteAdministrator(Integer id) {

        if (StringUtils.isBlank(id.toString())) {
            throw new AdministratorIdIsNullException("传入的管理员ID为空");
        }
        adminMapper.deleteByPrimaryKey(id);

    }
}
