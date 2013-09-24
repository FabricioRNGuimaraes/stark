package stark.service;

import java.util.List;

import stark.entity.StudentCourse;

public interface IStudentCourseService {

	List<StudentCourse> findByCourse(Integer idCourse);
}
