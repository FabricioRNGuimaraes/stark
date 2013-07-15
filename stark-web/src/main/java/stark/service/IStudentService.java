package stark.service;

import java.util.List;

import stark.entity.Student;

public interface IStudentService {

	boolean save(Student student);
	List<Student> findAll();
	boolean update(Student student);
	boolean remove(Student student);
}
