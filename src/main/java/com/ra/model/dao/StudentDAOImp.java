package com.ra.model.dao;

import com.ra.dto.StudentDTO;
import com.ra.model.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements IGenericDAO<StudentDTO, Integer>{
    @Override
    public List<StudentDTO> findAll() {
        Connection connection = null;
        List<StudentDTO> list = new ArrayList<>();

        connection = ConnectionDB.getConnection();
        try {
            CallableStatement cbsm = connection.prepareCall("{CALL ShowStudentList()}");
            ResultSet rs = cbsm.executeQuery();

            while (rs.next()){
                StudentDTO student = new StudentDTO();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setBirthday(rs.getDate("birthday"));
                student.setClassName(rs.getString("class_name"));
                list.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    @Override
    public Boolean create(StudentDTO student) {
        Connection connection = null;
        connection = ConnectionDB.getConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL AddNewStudent(?,?,?)");
            callableStatement.setString(1,student.getName());
            callableStatement.setDate(2,student.getBirthday());
            callableStatement.setInt(3,student.getClassId());
            int check = callableStatement.executeUpdate();
            if(check > 0){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    @Override
    public Boolean update(StudentDTO student, Integer studentId) {
        // Khai báo biến connection để quản lý kết nối đến cơ sở dữ liệu
        Connection connection = null;

        try {
            // Thực hiện mở kết nối đến cơ sở dữ liệu thông qua lớp ConnectionDB
            connection = ConnectionDB.getConnection();
            // Tạo đối tượng CallableStatement để thực thi stored procedure UpdateStudent
            CallableStatement callableStatement = connection.prepareCall("CALL UpdateStudent(?,?,?,?)");
            // Đặt giá trị cho các tham số của stored procedure
            callableStatement.setInt(1, studentId);
            callableStatement.setString(2, student.getName());
            callableStatement.setDate(3, student.getBirthday());
            callableStatement.setInt(4, student.getClassId());

            // Thực hiện stored procedure và nhận kết quả số lượng bản ghi được cập nhật
            int check = callableStatement.executeUpdate();
            // Trả về kết quả, check > 0 nếu có ít nhất một bản ghi được cập nhật thành công
            return check > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                // Đảm bảo đóng kết nối sau khi thực hiện xong
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public StudentDTO findId(Integer studentId) {
        Connection connection = null;
        StudentDTO student = null;

        try {
            connection = ConnectionDB.getConnection();
            CallableStatement cbsm = connection.prepareCall("{CALL GetStudentById(?)}");
            cbsm.setInt(1, studentId);
            ResultSet rs = cbsm.executeQuery();

            if (rs.next()) {
                student = new StudentDTO();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setBirthday(rs.getDate("birthday"));
                student.setClassId(rs.getInt("class_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return student;
    }


    @Override
    public void delete(Integer studentId) {
        Connection connection = null;
        connection = ConnectionDB.getConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL DeleteStudent(?)");
            callableStatement.setInt(1, studentId);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
