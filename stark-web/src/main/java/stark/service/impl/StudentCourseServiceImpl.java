package stark.service.impl;

import java.util.List;

import stark.dao.StudentCourseDAO;
import stark.entity.StudentCourse;
import stark.service.IStudentCourseService;

//@Service
public class StudentCourseServiceImpl implements IStudentCourseService {

//	@Autowired
	StudentCourseDAO dao = new StudentCourseDAO();

	@Override
	public List<StudentCourse> findByCourse(Integer idCourse) {
		return dao.findByCourse(idCourse);
	}
}
