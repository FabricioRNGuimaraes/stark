package stark.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import stark.dao.IStudentDAO;
import stark.entity.RelStudentCourse;
import stark.entity.Student;
import stark.service.GenericService;
import stark.service.IRelStudentCourseService;
import stark.service.IStudentService;

@Named
public class StudentService extends GenericService<Student> implements IStudentService {

	private static final long serialVersionUID = 1L;

	@Inject
	private IStudentDAO studentDAO;

	@Inject IRelStudentCourseService relStudentCourseService;
	
	@PostConstruct
	public void init() {
		super.setGenericDAOGiava(studentDAO);
	}
	
	@Override
	public boolean remove(Student student) {
		
		student.setActive(false);
		return studentDAO.update(student);
	}

	@Override
	public Set<Student> findByCourse(Integer idCourse) {

		List<RelStudentCourse> studentsCourse = relStudentCourseService.findByCourse(idCourse);
		Set<Student> students = new HashSet<Student>();

		if(studentsCourse != null) {
			
			for(RelStudentCourse rec : studentsCourse) {
				students.add(rec.getStudent());
			}
		}
		
		return students;
	}
	
}