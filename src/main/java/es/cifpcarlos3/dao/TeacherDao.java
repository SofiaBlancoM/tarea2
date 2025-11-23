package es.cifpcarlos3.dao;

import es.cifpcarlos3.model.Teacher;

import java.util.List;

public interface TeacherDao {

    Teacher getByDni (String dni);
    void add (Teacher teacher);
    void delete (String dni);
    List<Teacher> getAll();

}
