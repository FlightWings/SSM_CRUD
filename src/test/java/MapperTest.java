import com.zhao.ssm_crud_new.bean.Department;
import com.zhao.ssm_crud_new.bean.Employee;
import com.zhao.ssm_crud_new.dao.DepartmentMapper;
import com.zhao.ssm_crud_new.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试dao层的工作
 * 推荐SPring项目中使用Spring的单元测试，可以自动注入需要的组件
 * 1.导入SPring单元测试的包
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;

    /**
     * 测试departmentMapper
     */
    @Test
    public void testCRUD(){
//        //1.创建SpringIOC容器
//        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//        //2.从容器中获取mapper
//        DepartmentMapper departmentMapper = ioc.getBean(DepartmentMapper.class);
//        System.out.println(departmentMapper);

        //1. 插入部门
        departmentMapper.insertSelective(new Department(null,"开发部"));
        departmentMapper.insertSelective(new Department(null,"测试部"));

        //测试员工插入
        employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@163.com",15));

        //批量插入员工,使用可以执行批量操作的SQLSession
        for(int i=0;i<1000;i++){
            String uid = UUID.randomUUID().toString().substring(0, 5)+i;
            employeeMapper.insertSelective(new Employee(null,"uid","M",uid+"@163.com",16));
        }

    }
}
