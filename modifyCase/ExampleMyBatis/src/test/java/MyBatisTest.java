import com.alibaba.fastjson.JSON;
import com.mybatis.bean.Student;
import com.mybatis.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/30.
 */
public class MyBatisTest {
    private static final Logger  logger = Logger.getLogger(MyBatisTest.class);

    /**
     *
     * @return
     * @throws IOException
     */
    public  SqlSession getSession() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        return sessionFactory.openSession();
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void insertStudent() throws Exception {
        SqlSession sqlSession = getSession();
        Student student =  new Student();
        student.setName("王小二");
        student.setSex(1);
        student.setTclass("301");
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.insertStudent(student);
        logger.debug(JSON.toJSONString(student));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateStudent () throws IOException {
        SqlSession sqlSession = getSession();
        Student student =  new Student();
        student.setId(4);
        student.setName("王小丫");
        student.setSex(0);
        student.setTclass("303");
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.updateStudent(student);
        logger.debug(JSON.toJSONString(student));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteStudent () throws IOException {
        SqlSession sqlSession = getSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.deleteStudent(3);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectStudent() throws IOException {
        SqlSession sqlSession = getSession();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",1);
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> list = mapper.selectStudent(map);
        logger.debug(JSON.toJSONString(list));
        sqlSession.close();
    }

    @Test
    public void selectStudentChoose() throws IOException {
        SqlSession sqlSession = getSession();
        Map<String,Object> map = new HashMap<String, Object>();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> list = mapper.selectStudentChoose(map);
        logger.debug(JSON.toJSONString(list));
        sqlSession.close();
    }

    @Test
    public void selectStudentByIds() throws IOException {
        SqlSession sqlSession = getSession();
        List<Integer> listIds = new ArrayList<Integer>();
        listIds.add(1);
        listIds.add(2);
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> list = mapper.selectStudentByIds(listIds);
        logger.debug(JSON.toJSONString(list));
        sqlSession.close();
    }

    @Test
    public void selectStudentAll() throws IOException {
        SqlSession sqlSession = getSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> list = mapper.selectStudentAll();
        logger.debug(JSON.toJSONString(list));
        sqlSession.close();
    }
}
