package com.ra.model.dao;

import com.ra.model.entity.ClassRoom;
import com.ra.model.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassRoomDAOImp implements IGenericDAO<ClassRoom, Integer>{
    @Override
    public List<ClassRoom> findAll() {
        Connection connection = null;
        List<ClassRoom> list = new ArrayList<>();

        connection = ConnectionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("CALL ShowClassList()");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                ClassRoom classRoom = new ClassRoom();
                classRoom.setId(rs.getInt("id"));
                classRoom.setName(rs.getString("name"));
                list.add(classRoom);
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
    public Boolean create(ClassRoom classRoom) {
        return null;
    }

    @Override
    public Boolean update(ClassRoom classRoom, Integer integer) {
        return null;
    }

    @Override
    public ClassRoom findId(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
