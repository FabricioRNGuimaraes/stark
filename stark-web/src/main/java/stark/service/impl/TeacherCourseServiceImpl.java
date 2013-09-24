package stark.service.impl;

import java.util.List;

import stark.dao.TeacherCourseDAO;
import stark.entity.TeacherCourse;
import stark.service.ITeacherCourseService;

//@Service
public class TeacherCourseServiceImpl implements ITeacherCourseService {

//	@Autowired
	TeacherCourseDAO dao = new TeacherCourseDAO();

	@Override
	public List<TeacherCourse> findByCourse(Integer idCourse) {
		return dao.findByCourse(idCourse);
	}
}
