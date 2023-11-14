package com.ra.model.service;

import com.ra.dto.StudentDTO;
import com.ra.model.dao.StudentDAOImp;

import java.util.List;

public class StudentService implements IGenericService<StudentDTO, Integer>{
    private final StudentDAOImp studentDAOImp = new StudentDAOImp();
    @Override
    public List<StudentDTO> findAll() {
        return studentDAOImp.findAll();
    }

    @Override
    public Boolean create(StudentDTO student) {
        return studentDAOImp.create(student);
    }

    @Override
    public Boolean update(StudentDTO student, Integer studentId) {
        return studentDAOImp.update(student, studentId);
    }

    @Override
    public StudentDTO findId(Integer studentId) {
        return studentDAOImp.findId(studentId);
    }

    @Override
    public void delete(Integer studentId) {
        studentDAOImp.delete(studentId);
    }
}
