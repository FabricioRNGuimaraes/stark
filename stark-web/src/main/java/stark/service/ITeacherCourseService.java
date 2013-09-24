package stark.service;

import java.util.List;

import stark.entity.TeacherCourse;

public interface ITeacherCourseService {

	List<TeacherCourse> findByCourse(Integer idCourse);
}
