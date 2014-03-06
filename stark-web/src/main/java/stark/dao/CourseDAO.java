package stark.dao;

import javax.inject.Named;

import stark.entity.Course;

@Named
public class CourseDAO extends GenericDAO<Course> implements ICourseDAO {

	private static final long serialVersionUID = 1L;

}
