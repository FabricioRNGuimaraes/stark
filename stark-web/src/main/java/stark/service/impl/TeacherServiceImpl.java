package stark.service.impl;

import java.util.List;

import stark.dao.TeacherDAO;
import stark.entity.Teacher;
import stark.service.ITeacherService;

//@Service
public class TeacherServiceImpl implements ITeacherService {

//	@Autowired
	TeacherDAO teacherDAO = new TeacherDAO();
	
	@Override
	public List<Teacher> findAll() {
		return teacherDAO.findAllActive();
	}

	@Override
	public boolean save(Teacher teacher) {
		
		teacher.setActive(true);
		return teacherDAO.save(teacher);
	}

	@Override
	public boolean remove(Teacher teacher) {

		teacher.setActive(false);
		return teacherDAO.update(teacher);
	}

	@Override
	public boolean update(Teacher teacher) {
		return teacherDAO.update(teacher);
	}

	@Override
	public Teacher findById(Integer id) {
		
		return teacherDAO.findById(id);
	}
}
