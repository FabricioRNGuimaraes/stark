package stark.service.impl;

import java.util.Date;
import java.util.List;

import stark.dao.StudentDAO;
import stark.entity.Student;
import stark.service.IStudentService;

public class StudentServiceImpl implements IStudentService {

	StudentDAO dao = new StudentDAO();
	
	@Override
	public boolean save(Student student) {
		
		student.setActive(Boolean.TRUE);
		student.setDtRegister(new Date());
		return dao.save(student);
	}

	@Override
	public List<Student> findAll() {
		return dao.findAllActive();
	}

	@Override
	public boolean update(Student student) {
		return dao.update(student);
	}

	@Override
	public boolean remove(Student student) {
		
		student.setActive(false);
		return dao.update(student);
	}

}