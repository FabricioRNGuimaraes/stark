package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.dao.CourseDAO;
import stark.entity.Course;

@ManagedBean(name="courseBean")
@ViewScoped
public class CourseBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private List<Course> courses = new ArrayList<Course>();
	private Course course = new Course();
	private Course courseViewRemove;
	
	private CourseDAO dao;
	
	private Boolean isEditEntity = false;
	
	public void initialize() {

		dao = new CourseDAO();
		courses = dao.findAll();
	}

	public void save(ActionEvent event) {

		course.setActive(Boolean.TRUE);
		if(!isEditEntity && course.getId() == null) {
			addInfoGrowlMessage("Course " + course.getDescription() + " saved!");
			dao.save(course);
			courses = dao.findAll();
		} else {
			
			addInfoGrowlMessage("User " + course.getDescription() + " updated!");
			dao.update(course);
		}
	}
	
	public void remove(ActionEvent event) {
		
		courseViewRemove.setActive(Boolean.FALSE);
		dao.update(courseViewRemove);
		courses = dao.findAll();
		addInfoGrowlMessage("Course " + courseViewRemove.getDescription() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		clean();
	}
	
	private void clean() {
		
		course = new Course();
		isEditEntity = false;
	}
	
	public void prepareEdit() {
		
		isEditEntity = true;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Boolean getIsEditEntity() {
		return isEditEntity;
	}

	public void setIsEditEntity(Boolean isEditEntity) {
		this.isEditEntity = isEditEntity;
	}

	public Course getCourseViewRemove() {
		return courseViewRemove;
	}

	public void setCourseViewRemove(Course courseViewRemove) {
		this.courseViewRemove = courseViewRemove;
	}

}