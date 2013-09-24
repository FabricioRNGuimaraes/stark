package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.entity.Course;
import stark.service.ICourseService;
import stark.service.impl.CourseServiceImpl;

@ManagedBean(name="courseBean")
@ViewScoped
public class CourseBean extends GenericBean {

	private static final long serialVersionUID = 1L;

	private List<Course> courses = new ArrayList<Course>();
	private Course course = new Course();
	private ICourseService courseService;
	
	private Boolean newEntity = false;
	
	public void initialize() {

		courseService = new CourseServiceImpl();
		courses = courseService.findAll();
	}

	public void save(ActionEvent event) {

		if(!newEntity && course.getId() == null) {
			addInfoGrowlMessage("Course " + course.getDescription() + " saved!");
			courseService.save(course);
		} else {
			
			addInfoGrowlMessage("Course " + course.getDescription() + " updated!");
			courseService.update(course);
		}
		courses = courseService.findAll();
	}
	
	public void remove(ActionEvent event) {
		
		courseService.remove(course);
		courses = courseService.findAll();
		addInfoGrowlMessage("Course " + course.getDescription() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		clean();
	}
	
	public void prepareEdit() {
		
		newEntity = true;
	}

	private void clean() {
		
		course = new Course();
		newEntity = false;
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

	public Boolean getNewEntity() {
		return newEntity;
	}

	public void setNewEntity(Boolean newEntity) {
		this.newEntity = newEntity;
	}

}