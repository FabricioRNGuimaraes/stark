package stark.service;

import java.util.Set;

import stark.entity.Student;

public interface IStudentService extends IGenericService<Student> {

	boolean remove(Student student);

	Set<Student> findByCourse(Integer id);
}
