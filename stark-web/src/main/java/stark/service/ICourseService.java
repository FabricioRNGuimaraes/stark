package stark.service;

import java.util.List;

import stark.entity.AbstractEntity;
import stark.entity.ClassRoom;
import stark.entity.Course;
import stark.entity.Teacher;

public interface ICourseService {

	List<Course> findAll();
	boolean save(Course course);
	boolean remove(Course course);
	boolean update(Course course);
}
