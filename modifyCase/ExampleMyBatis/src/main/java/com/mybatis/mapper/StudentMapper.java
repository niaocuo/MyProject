package com.mybatis.mapper;

import com.mybatis.bean.Student;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/30.
 */
public interface  StudentMapper {void insertStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(int id);

    List<Student> selectStudent(Map<String, Object> map);

    List<Student> selectStudentChoose(Map<String, Object> map);

    List<Student> selectStudentByIds(List<Integer> listIds);

    List<Student> selectStudentAll();

    /**
     *
     * @param student
     * @return
     */

}
