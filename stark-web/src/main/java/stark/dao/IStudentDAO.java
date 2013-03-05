package stark.dao;

import java.util.List;

import stark.entity.Student;

public interface IStudentDAO extends IGenericDAO {

	List<Student> findAll();
}
