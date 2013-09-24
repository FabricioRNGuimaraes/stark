package stark.dao;

import java.util.List;

import stark.entity.TeacherCourse;

public class TeacherCourseDAO extends GenericDAOImpl<TeacherCourse> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<TeacherCourse> findByCourse(Integer idCourse) {
		
		List<TeacherCourse> teacherCourses = entityManager.createQuery("from " + TeacherCourse.class.getName() +  " sc where sc.course.id = :idCourse")
							.setParameter("idCourse", idCourse).getResultList();

		return teacherCourses;
	}
}
