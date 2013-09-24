package stark.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.entity.Address;
import stark.entity.Course;
import stark.entity.Student;
import stark.entity.StudentCourse;
import stark.service.ICourseService;
import stark.service.IStudentService;
import stark.service.impl.CourseServiceImpl;
import stark.service.impl.StudentServiceImpl;
import stark.util.StudentTeacherCourseBO;

@ManagedBean(name="studentBean")
@ViewScoped
//@Component
//@Scope("session")
public class StudentBean extends GenericBean {

	private static final long serialVersionUID = 1L;
	
	private List<Student> students = new ArrayList<Student>();
	private Student student = new Student();
	
	private List<Course> courses = new ArrayList<Course>();
	private List<StudentTeacherCourseBO> studentCoursesBO = new ArrayList<StudentTeacherCourseBO>();

	private Boolean newEntity = false;
//	@Autowired
//	@ManagedProperty(value="#{studentService}")
	private IStudentService studentService;
	private ICourseService courseService;
//	private IStudentCourseService studentCourseService;
	
	public void initialize() {
		
		studentService = new StudentServiceImpl();
		courseService = new CourseServiceImpl();
//		studentCourseService = new StudentCourseServiceImpl();
		
		students = studentService.findAll();
		courses = courseService.findAll();
		
//		studentCourseService.findAll();
		createCourses();
		createStudentAddress();
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
				studentCoursesBO.add(bo);
			}
		}
	}

	public void save(ActionEvent event) {
		
		List<StudentCourse> studentCourses = new ArrayList<StudentCourse>();
		
		for(StudentTeacherCourseBO bo : studentCoursesBO) {
			
			if(bo.isSelected()) {
				
				StudentCourse sc = new StudentCourse();
				Course course = new Course();
				course.setId(bo.getId());
				
				sc.setCourse(course);
				sc.setStudent(student);
				sc.setDtRegister(new Date());
				sc.setValueClass(bo.getValueClass());
				studentCourses.add(sc);
			}
		}
		
		if(studentCourses.isEmpty()) {
			addErrorGrowlMessage("At least one course must be selected.");
			return;
		}
		
		student.setStudentCourses(studentCourses);
		
		if(!newEntity && student.getId() == null) {

			addInfoGrowlMessage("Student " + student.getName() + " saved!");
			studentService.save(student);
//			students = studentService.findAll();
		} else {
			
			addInfoGrowlMessage("Student " + student.getName() + " updated!");
			studentService.update(student);
		}
		
		students = studentService.findAll();
	}
	
	public void remove(ActionEvent event) {
		
		studentService.remove(student);
		students = studentService.findAll();
		addInfoGrowlMessage("Student " + student.getName() + " removed!");
	}
	
	public void clean(ActionEvent ev) {
		clean();
		cleanStudentCourse();
	}
	
	public void editOnComplete(ActionEvent ev) {
		
		cleanStudentCourse();
		List<StudentCourse> courses = student.getStudentCourses();
		if(courses != null) {
			
			for(StudentCourse studentCourse : courses) {
				
				for(StudentTeacherCourseBO bo : studentCoursesBO) {

					if(studentCourse.getCourse().getId().equals(bo.getId())) {
						
						bo.setSelected(true);
						bo.setVisibleValue(true);
						bo.setValueClass(studentCourse.getValueClass());
					}
				}
			}
		}
	}
	
	public void prepareEdit() {
		
		newEntity = true;
	}
	
	public void changeEventSelectBoxCourse() {
		
		for(StudentTeacherCourseBO bo : studentCoursesBO) {
			
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
		
		student = new Student();
		createStudentAddress();
		newEntity = false;
	}
	
	private void cleanStudentCourse() {
		
		if(studentCoursesBO != null) {
			
			for(StudentTeacherCourseBO bo : studentCoursesBO) {
				bo.setSelected(false);
				bo.setVisibleValue(false);
				bo.setValueClass(null);
			}
		}
	}
	
	private void createStudentAddress() {
		student.setAddress(new Address());
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Boolean getNewEntity() {
		return newEntity;
	}

	public void setNewEntity(Boolean newEntity) {
		this.newEntity = newEntity;
	}

//	public List<Course> getCourses() {
//		return courses;
//	}
//
//	public void setCourses(List<Course> courses) {
//		this.courses = courses;
//	}

	public List<StudentTeacherCourseBO> getStudentCoursesBO() {
		return studentCoursesBO;
	}

	public void setStudentCoursesBO(List<StudentTeacherCourseBO> studentCoursesBO) {
		this.studentCoursesBO = studentCoursesBO;
	}
}