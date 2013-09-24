package stark.service.impl;

import java.util.List;

import stark.dao.CourseDAO;
import stark.entity.Course;
import stark.service.ICourseService;

public class CourseServiceImpl implements ICourseService {

	CourseDAO dao = new CourseDAO();
	
	@Override
	public List<Course> findAll() {
		return dao.findAllActive();
	}

	@Override
	public boolean save(Course course) {
		
		course.setActive(true);
		return dao.save(course);
	}

	@Override
	public boolean remove(Course course) {

		course.setActive(false);
		return dao.update(course);
	}

	@Override
	public boolean update(Course course) {
		return dao.update(course);
	}

}
