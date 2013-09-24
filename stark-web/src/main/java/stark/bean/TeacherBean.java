package stark.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.entity.Address;
import stark.entity.Course;
import stark.entity.StudentCourse;
import stark.entity.Teacher;
import stark.entity.TeacherCourse;
import stark.service.ICourseService;
import stark.service.ITeacherService;
import stark.service.impl.CourseServiceImpl;
import stark.service.impl.TeacherServiceImpl;
import stark.util.StudentTeacherCourseBO;

@ManagedBean(name="teacherBean")
@ViewScoped
public class TeacherBean extends GenericBean {

	private static final long serialVersionUID = 1L;

	private List<Teacher> teachers = new ArrayList<Teacher>();
	private Teacher teacher = new Teacher();
	private List<Course> courses = new ArrayList<Course>();
	private List<StudentTeacherCourseBO> teacherCoursesBO = new ArrayList<StudentTeacherCourseBO>();
	
	private ICourseService courseService;
	private Boolean newEntity = false;
	
//	@ManagedProperty(value="#{teacherService}")
	private ITeacherService teacherService;
	
	public void initialize() {
		
		teacherService = new TeacherServiceImpl(); 
		courseService = new CourseServiceImpl();
		
		teachers = teacherService.findAll();

		courses = courseService.findAll();
		
		createCourses();
		createTeacherAddress();
	}

	private void createCourses() {
		
		if(courses != null) {
			for(Course course : courses) {
				
				StudentTeacherCourseBO bo = new StudentTeacherCourseBO();
				bo.setSelected(false);
				bo.setVisibleValue(false);
//				bo.setValueClass(0.00);
				bo.setId(course.getId());
				bo.setDescription(course.getDescription());
				teacherCoursesBO.add(bo);
			}
		}
	}
	
	public void save(ActionEvent event) {

		List<TeacherCourse> teacherCourses = new ArrayList<TeacherCourse>();
		
		for(StudentTeacherCourseBO bo : teacherCoursesBO) {
			
			if(bo.isSelected()) {
				
				TeacherCourse sc = new TeacherCourse();
				Course course = new Course();
				course.setId(bo.getId());
				
				sc.setCourse(course);
				sc.setTeacher(teacher);
				sc.setDtRegister(new Date());
				sc.setValueClass(bo.getValueClass());
				teacherCourses.add(sc);
			}
		}
		
		if(teacherCourses.isEmpty()) {
			addErrorGrowlMessage("At least one course must be selected.");
			return;
		}
		
		teacher.setTeacherCourses(teacherCourses);
		
		if(!newEntity && teacher.getId() == null) {

			teacherService.save(teacher);
			addInfoGrowlMessage("Teacher " + teacher.getName() + " saved!");
		} else {
			teacherService.update(teacher);
			addInfoGrowlMessage("Teacher " + teacher.getName() + " updated!");
		}

		teachers = teacherService.findAll();
	}
	
	public void remove(ActionEvent event) {
		
		teacherService.remove(teacher);
		teachers = teacherService.findAll();

		addInfoGrowlMessage("Teacher " + teacher.getName() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		clean();
		cleanTeacherCourse();
	}
	
	public void prepareEdit() {
		
		newEntity = true;
	}
	
	public void editOnComplete(ActionEvent ev) {
		
		cleanTeacherCourse();
		List<TeacherCourse> courses = teacher.getTeacherCourses();
		if(courses != null) {
			
			for(TeacherCourse teacherCourse : courses) {
				
				for(StudentTeacherCourseBO bo : teacherCoursesBO) {

					if(teacherCourse.getCourse().getId().equals(bo.getId())) {
						
						bo.setSelected(true);
						bo.setVisibleValue(true);
						bo.setValueClass(teacherCourse.getValueClass());
					}
				}
			}
		}
	}
	
	public void changeEventSelectBoxCourse() {
		
		for(StudentTeacherCourseBO bo : teacherCoursesBO) {
			
			if(bo.isSelected() == true) {
				
				bo.setVisibleValue(true);
//				bo.setValueClass(bo.getValueClass());
			} else {
				bo.setValueClass(null);
				bo.setVisibleValue(false);
			}
		}
	}
	
	private void clean() {
		
		teacher = new Teacher();
		createTeacherAddress();
		newEntity = false;
	}
	
	private void cleanTeacherCourse() {
		
		if(teacherCoursesBO != null) {
			
			for(StudentTeacherCourseBO bo : teacherCoursesBO) {
				bo.setSelected(false);
				bo.setVisibleValue(false);
				bo.setValueClass(null);
			}
		}
	}
	
	
	private void createTeacherAddress() {
		teacher.setAddress(new Address());
	}
	
	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Boolean getNewEntity() {
		return newEntity;
	}

	public void setNewEntity(Boolean newEntity) {
		this.newEntity = newEntity;
	}

	public List<StudentTeacherCourseBO> getTeacherCoursesBO() {
		return teacherCoursesBO;
	}

	public void setTeacherCoursesBO(List<StudentTeacherCourseBO> teacherCoursesBO) {
		this.teacherCoursesBO = teacherCoursesBO;
	}

}