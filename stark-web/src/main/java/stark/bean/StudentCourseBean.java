package stark.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.dao.CourseDAO;
import stark.dao.StudentCourseDAO;
import stark.dao.StudentDAO;
import stark.entity.Course;
import stark.entity.Student;
import stark.entity.StudentCourse;

@ManagedBean(name="studentCourseBean")
@ViewScoped
public class StudentCourseBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private List<StudentCourse> studentCourses;
	private List<Student> students;
	private List<Course> courses;
	private StudentCourse studentCourse = new StudentCourse();
	private StudentCourse studentCourseViewRemove;
	
	private StudentCourseDAO dao;
	private StudentDAO studentDao;
	private CourseDAO courseDao;
	
	private Boolean isEditEntity = false;
	
	public void initialize() {
		
		dao = new StudentCourseDAO();
		studentDao = new StudentDAO();
		courseDao = new CourseDAO();

		studentCourses = dao.findAll();
		students = studentDao.findAll();
		courses = courseDao.findAll();
	}

	public void save(ActionEvent event) {

		if(!isEditEntity && studentCourse.getId() == null) {

			addInfoGrowlMessage("Values for student " + studentCourse.getStudent().getName() + " saved!");
			dao.save(studentCourse);
			studentCourses = dao.findAll();
		} else {
			
			addInfoGrowlMessage("Values for student" + studentCourse.getStudent().getName() + " updated!");
			dao.update(studentCourse);
		}
	}
	
	public void remove(ActionEvent event) {
		
		dao.remove(studentCourseViewRemove);
		studentCourses = dao.findAll();
		addInfoGrowlMessage("Values for student " + studentCourseViewRemove.getStudent().getName() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		clean();
	}
	
	private void clean() {
		
		studentCourse = new StudentCourse();
		isEditEntity = false;
	}
	
	public void prepareEdit() {
		
		isEditEntity = true;
	}

	public List<StudentCourse> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(List<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public StudentCourse getStudentCourse() {
		return studentCourse;
	}

	public void setStudentCourse(StudentCourse studentCourse) {
		this.studentCourse = studentCourse;
	}

	public StudentCourse getStudentCourseViewRemove() {
		return studentCourseViewRemove;
	}

	public void setStudentCourseViewRemove(StudentCourse studentCourseViewRemove) {
		this.studentCourseViewRemove = studentCourseViewRemove;
	}

	public Boolean getIsEditEntity() {
		return isEditEntity;
	}

	public void setIsEditEntity(Boolean isEditEntity) {
		this.isEditEntity = isEditEntity;
	}

}