package stark.dao;

import javax.inject.Named;

import stark.entity.Student;

@Named
public class StudentDAO extends GenericDAO<Student> implements IStudentDAO {

	private static final long serialVersionUID = 1L;

}
