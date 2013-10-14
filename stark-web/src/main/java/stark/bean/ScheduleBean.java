package stark.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DualListModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

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

@ManagedBean(name="scheduleBean")
@ViewScoped
public class ScheduleBean extends GenericBean {

	private static final long serialVersionUID = 1L;
	
	private ScheduleModel scheduleModel ;  
    private ScheduleEvent event = new DefaultScheduleEvent();

    private Course course = new Course();
    private ClassRoom classRoom = new ClassRoom();
    private Date classDate;
    
    private List<ClassRoom> classRooms = new ArrayList<ClassRoom>();
    private List<Course> courses = new ArrayList<Course>();

    private DualListModel<Student> studentsPickList;
    private DualListModel<Teacher> teachersPickList;
    
    private IClassRoomService classRoomService;
    private ICourseService courseService;
    private IStudentCourseService studentCourseService;
    private ITeacherCourseService teacherCourseService;
    
    @Override
	public void initialize() {

		classRoomService = new ClassRoomServiceImpl();
		courseService = new CourseServiceImpl();
		studentCourseService = new StudentCourseServiceImpl();
		teacherCourseService = new TeacherCourseServiceImpl();

		classRooms = classRoomService.findAll();
		courses = courseService.findAll();
		
		studentsPickList = new DualListModel<Student>(new ArrayList<Student>(), new ArrayList<Student>());
		teachersPickList = new DualListModel<Teacher>(new ArrayList<Teacher>(), new ArrayList<Teacher>());
		
		scheduleModel = new DefaultScheduleModel();
		scheduleModel.addEvent(new DefaultScheduleEvent("Ingles: theo; Fa", new Date(2013, 9, 12, 9, 00), new Date(2013, 9, 12, 10, 00)));
		scheduleModel.addEvent(new DefaultScheduleEvent("Ingles: theo; Fa", new Date(2013, 9, 12, 10, 00), new Date(2013, 9, 12, 11, 00)));
	}
//	#ppgcc_ufla#
	public void onChangeOneMenuCourse(AjaxBehaviorEvent event) {

		studentsPickList.getSource().clear();
		studentsPickList.getTarget().clear();
		
		teachersPickList.getSource().clear();
		teachersPickList.getTarget().clear();
		
		course = new Course();
		course.setId(18);
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
		
		addInfoGrowlMessage("Saved");
	}	
	
	public void clean(ActionEvent event) {
		
		classDate = null;
		studentsPickList.getSource().clear();
		studentsPickList.getTarget().clear();
		
		teachersPickList.getSource().clear();
		teachersPickList.getTarget().clear();
	}
	
	public void onEventSelect(SelectEvent selectEvent) {  
        event = (ScheduleEvent) selectEvent.getObject();  
    }  
      
    public void onDateSelect(SelectEvent selectEvent) {  
    	
    	classDate = (Date) selectEvent.getObject();
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());  

    }

	public ScheduleModel getScheduleModel() {
		return scheduleModel;
	}

	public void setScheduleModel(ScheduleModel scheduleModel) {
		this.scheduleModel = scheduleModel;
	}

	public List<ClassRoom> getClassRooms() {
		return classRooms;
	}

	public void setClassRooms(List<ClassRoom> classRooms) {
		this.classRooms = classRooms;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
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

	public Date getClassDate() {
		return classDate;
	}

	public void setClassDate(Date classDate) {
		this.classDate = classDate;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

}