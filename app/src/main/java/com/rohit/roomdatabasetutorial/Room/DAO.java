package com.rohit.roomdatabasetutorial.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface DAO {
    @Insert
    public void studentInsertion(Student student);
    @Query("Select * from Student")
    List<Student> getStudent();

    @Query("Update Student set stuFirstName = :stuName where stuId=:stuID")
    void update(String stuName,int  stuID);
    @Query("Delete from Student where stuId=:studID")
    void delete(int studID);

}
