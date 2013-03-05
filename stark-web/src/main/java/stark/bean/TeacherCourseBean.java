package stark.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.dao.CourseDAO;
import stark.dao.TeacherCourseDAO;
import stark.dao.TeacherDAO;
import stark.entity.Course;
import stark.entity.Teacher;
import stark.entity.TeacherCourse;

@ManagedBean(name="teacherCourseBean")
@ViewScoped
public class TeacherCourseBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private List<TeacherCourse> teacherCourses;
	private List<Teacher> teachers;
	private List<Course> courses;
	private TeacherCourse teacherCourse = new TeacherCourse();
	private TeacherCourse teacherCourseViewRemove;
	
	private TeacherCourseDAO dao;
	private TeacherDAO teacherDao;
	private CourseDAO courseDao;
	
	private Boolean isEditEntity = false;
	
	public void initialize() {
		
		dao = new TeacherCourseDAO();
		teacherDao = new TeacherDAO();
		courseDao = new CourseDAO();

		teacherCourses = dao.findAll();
		teachers = teacherDao.findAll();
		courses = courseDao.findAll();
	}

	public void save(ActionEvent event) {

		if(!isEditEntity && teacherCourse.getId() == null) {

			addInfoGrowlMessage("Values for teacher " + teacherCourse.getTeacher().getName() + " saved!");
			dao.save(teacherCourse);
			teacherCourses = dao.findAll();
		} else {
			
			addInfoGrowlMessage("Values for teacher" + teacherCourse.getTeacher().getName() + " updated!");
			dao.update(teacherCourse);
		}
	}
	
	public void remove(ActionEvent event) {
		
		dao.remove(teacherCourseViewRemove);
		teacherCourses = dao.findAll();
		addInfoGrowlMessage("Values for teacher " + teacherCourseViewRemove.getTeacher().getName() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		clean();
	}
	
	private void clean() {
		
		teacherCourse = new TeacherCourse();
		isEditEntity = false;
	}
	
	public void prepareEdit() {
		
		isEditEntity = true;
	}

	public List<TeacherCourse> getTeacherCourses() {
		return teacherCourses;
	}

	public void setTeacherCourses(List<TeacherCourse> teacherCourses) {
		this.teacherCourses = teacherCourses;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public TeacherCourse getTeacherCourse() {
		return teacherCourse;
	}

	public void setTeacherCourse(TeacherCourse teacherCourse) {
		this.teacherCourse = teacherCourse;
	}

	public TeacherCourse getTeacherCourseViewRemove() {
		return teacherCourseViewRemove;
	}

	public void setTeacherCourseViewRemove(TeacherCourse teacherCourseViewRemove) {
		this.teacherCourseViewRemove = teacherCourseViewRemove;
	}

	public Boolean getIsEditEntity() {
		return isEditEntity;
	}

	public void setIsEditEntity(Boolean isEditEntity) {
		this.isEditEntity = isEditEntity;
	}

}