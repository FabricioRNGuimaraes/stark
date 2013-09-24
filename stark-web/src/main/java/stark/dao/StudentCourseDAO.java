package stark.dao;

import java.util.List;

import stark.entity.StudentCourse;

public class StudentCourseDAO extends GenericDAOImpl<StudentCourse> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<StudentCourse> findByCourse(Integer idCourse) {
		
		List<StudentCourse> studentCourses = entityManager.createQuery("from " + StudentCourse.class.getName() +  " sc where sc.course.id = :idCourse")
							.setParameter("idCourse", idCourse).getResultList();

		return studentCourses;
	}
}
