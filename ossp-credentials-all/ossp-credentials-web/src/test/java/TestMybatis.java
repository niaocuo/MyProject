import com.alibaba.fastjson.JSON;
import com.hang.up.controller.IndexController;
import com.hang.up.service.ManagerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

/**
 * Created by 顺 on 2018/7/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring-mvc.xml"})
public class TestMybatis {

    public static final Logger logger = LogManager.getLogger(TestMybatis.class);

    @Autowired
    private ManagerService managerService;

    @Autowired
    private IndexController indexController;


    @Test
    public void selectStudentAll() throws IOException {
        logger.debug(JSON.toJSONString( managerService.getProductById(1)));
    }

}
