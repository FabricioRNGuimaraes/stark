package stark.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.model.DualListModel;

import stark.entity.ClassRoom;
import stark.entity.Course;
import stark.entity.Student;
import stark.entity.StudentCourse;
import stark.entity.Teacher;
import stark.entity.TeacherCourse;
import stark.service.IClassRoomService;
import stark.service.ICourseService;
import stark.service.IStudentCourseService;
import stark.service.ITeacherCourseService;
import stark.service.impl.ClassRoomServiceImpl;
import stark.service.impl.CourseServiceImpl;
import stark.service.impl.StudentCourseServiceImpl;
import stark.service.impl.TeacherCourseServiceImpl;

@ManagedBean(name="reportBean")
@ViewScoped
public class ReportBean extends GenericBean {

	private static final long serialVersionUID = 1L;
	
	private List<ClassRoom> classRooms = new ArrayList<ClassRoom>();
    private List<Course> courses = new ArrayList<Course>();

    private DualListModel<Student> studentsPickList;
    private DualListModel<Teacher> teachersPickList;

	private ClassRoom classRoom;
	private Course course;
//	private Student student = new Student();
//	private List<Course> courses = new ArrayList<Course>();
//	private List<Course> selectedCourses = new ArrayList<Course>();
	
//    private IStudentService studentService;
    private ICourseService courseService;
    private IClassRoomService classRoomService;
    private IStudentCourseService studentCourseService;
    private ITeacherCourseService teacherCourseService;
    
    private Date startDate;
    private Integer hour;
    
    @Override
	public void initialize() {
	
//		studentService = new StudentServiceImpl();
//		courseService = new CourseServiceImpl();
		classRoomService = new ClassRoomServiceImpl();
		courseService = new CourseServiceImpl();
		studentCourseService = new StudentCourseServiceImpl();
		teacherCourseService = new TeacherCourseServiceImpl();

    	classRooms = classRoomService.findAll();
		courses = courseService.findAll();
		
		studentsPickList = new DualListModel<Student>(new ArrayList<Student>(), new ArrayList<Student>());
		teachersPickList = new DualListModel<Teacher>(new ArrayList<Teacher>(), new ArrayList<Teacher>());
	}
	
	public void onChangeOneMenuCourse(AjaxBehaviorEvent event) {

		studentsPickList.getSource().clear();
		studentsPickList.getTarget().clear();
		
		teachersPickList.getSource().clear();
		teachersPickList.getTarget().clear();
		
		if(course != null && course.getId() != null) {
			
			List<StudentCourse> studentCourses = studentCourseService.findByCourse(course.getId());
			for(StudentCourse studentCourse : studentCourses) {
				studentsPickList.getSource().add(studentCourse.getStudent());
			}
			
			List<TeacherCourse> teacherCourses = teacherCourseService.findByCourse(course.getId());
			for(TeacherCourse teacherCourse : teacherCourses) {
				teachersPickList.getSource().add(teacherCourse.getTeacher());
			}
		}
	}

	public void save(ActionEvent event) {
		addInfoGrowlMessage("User " + " logged in!");
	}

	public List<ClassRoom> getClassRooms() {
		return classRooms;
	}

	public void setClassRooms(List<ClassRoom> classRooms) {
		this.classRooms = classRooms;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public DualListModel<Student> getStudentsPickList() {
		return studentsPickList;
	}

	public void setStudentsPickList(DualListModel<Student> studentsPickList) {
		this.studentsPickList = studentsPickList;
	}

	public DualListModel<Teacher> getTeachersPickList() {
		return teachersPickList;
	}

	public void setTeachersPickList(DualListModel<Teacher> teachersPickList) {
		this.teachersPickList = teachersPickList;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

}