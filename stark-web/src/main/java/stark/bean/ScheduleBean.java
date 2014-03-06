package stark.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DualListModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.context.annotation.Scope;

import stark.entity.ClassRoom;
import stark.entity.Course;
import stark.entity.RelStudentClass;
import stark.entity.RelStudentCourse;
import stark.entity.RelTeacherClass;
import stark.entity.ScheduledClass;
import stark.entity.Student;
import stark.entity.Teacher;
import stark.service.IClassRoomService;
import stark.service.ICourseService;
import stark.service.IScheduledClassService;
import stark.service.IStudentService;
import stark.service.ITeacherService;
import stark.util.DateUtil;

@Named(value="scheduleBean")
@Scope("view")
public class ScheduleBean extends GenericBean {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICourseService courseService; 
	
	@Inject
	private IClassRoomService classRoomService;
	
	@Inject
	private IStudentService studentService; 
	
	@Inject
	private ITeacherService teacherService;

	@Inject
	private IScheduledClassService scheduledClassService;
	
	private List<Course> courses = new ArrayList<Course>();
	private Course course = new Course();
	
	private List<ClassRoom> classRooms = new ArrayList<ClassRoom>();
	private ClassRoom classRoom = new ClassRoom();

	private List<ScheduledClass> scheduledClasses = new ArrayList<ScheduledClass>();
	
	private DualListModel<Student> students = new DualListModel<Student>();
	private DualListModel<Teacher> teachers = new DualListModel<Teacher>();
	
	private ScheduleModel scheduleModel = new DefaultScheduleModel();
	private ScheduleEvent scheduleEvent = new DefaultScheduleEvent();
	
	private Date dateStart;
	private Date dateEnd;

	private String dateStartString;
	private String dateEndString;
	
	private boolean isUpdate;
	
	@PostConstruct
	public void initialize() {
		
		courses = courseService.findAll();
		classRooms = classRoomService.findAll();
		scheduledClasses = scheduledClassService.findAll();
		for(ScheduledClass sc : scheduledClassService.findAll()) {
			
			scheduleModel.addEvent(new DefaultScheduleEvent(sc.getClassRoom().getDescription() + "-" 
							+ sc.getCourse().getDescription(), sc.getDtStart(), sc.getDtEnd(), sc));
		}
	}

	public void save(ActionEvent event) {
		
		if(validate()) {

			ScheduledClass scheduledClass = new ScheduledClass();
			scheduledClass.setClassRoom(classRoom);
			scheduledClass.setCourse(course);
			scheduledClass.setDtStart(dateStart);
			scheduledClass.setDtEnd(dateEnd);

			for(Student student : students.getTarget()) {
				RelStudentClass relStudentClass = new RelStudentClass();
				relStudentClass.setDtRegister(new Date());
				relStudentClass.setScheduledClass(scheduledClass);
				relStudentClass.setStudent(student); 
				scheduledClass.addRelStudentsClasses(relStudentClass);
			}
			
			for(Teacher teacher : teachers.getTarget()){
				RelTeacherClass relTeacherClass = new RelTeacherClass();
				relTeacherClass.setDtRegister(new Date());
				relTeacherClass.setScheduledClass(scheduledClass);
				relTeacherClass.setTeacher(teacher); 
				scheduledClass.addRelTeachersClasses(relTeacherClass);
			}
			
			scheduledClassService.save(scheduledClass);
			addInfoGrowlMessage("Saved with sucess!");
			scheduleModel.addEvent(new DefaultScheduleEvent(scheduledClass.getClassRoom().getDescription() + "-" 
					+ scheduledClass.getCourse().getDescription(), scheduledClass.getDtStart(), scheduledClass.getDtEnd(), scheduledClass));
			scheduledClasses = scheduledClassService.findAll();
		}
	}
	
	public void remove(ActionEvent event) {

		ScheduledClass schedClass = (ScheduledClass) scheduleEvent.getData();
		scheduleModel.deleteEvent(scheduleEvent);
		
//		scheduledClassService.remove(schedClass);
		scheduledClasses = scheduledClassService.findAll();
		
		addInfoGrowlMessage("Class dismissed!");
	}
	
	public void clean(ActionEvent ev) {
		
		course = null;
		classRoom = null;
		students.getSource().clear();
		students.getTarget().clear();
		teachers.getSource().clear();
		teachers.getTarget().clear();
	}
	
	public void onEventSelect(SelectEvent selectEvent) {  

		isUpdate = true;
		scheduleEvent = (ScheduleEvent) selectEvent.getObject();

		dateStart = scheduleEvent.getStartDate();
		dateStartString = scheduleEvent.getStartDate().toString();//DataUtil.formatToYearMonthDayHourMinSec(dateStart);
		dateEnd = scheduleEvent.getEndDate();
		dateEndString = scheduleEvent.getEndDate().toString();//DataUtil.formatToYearMonthDayHourMinSec(dateEnd);
		
		ScheduledClass scheduledClass = (ScheduledClass) scheduleEvent.getData();
		
		course = scheduledClass.getCourse();
		classRoom = scheduledClass.getClassRoom();

		for(RelStudentClass sc : scheduledClass.getRelStudentsClasses()) {
			students.getTarget().add(sc.getStudent());
		}
		
		Set<Student> studentsCourse = studentService.findByCourse(course.getId());
		for(Student s : studentsCourse) {
			
			if(!students.getTarget().contains(s)) {
				students.getSource().add(s);
			}
		}

		for(RelTeacherClass sc : scheduledClass.getRelTeachersClasses()) {
			teachers.getTarget().add(sc.getTeacher());
		}
		
		Set<Teacher> teachersCourse = teacherService.findByCourse(course.getId());
		for(Teacher t : teachersCourse){
			
			if(!teachers.getTarget().contains(t)) {
				teachers.getSource().add(t);
			}
		}
		
		scheduledClasses = scheduledClassService.findAll();
	}  
	
	public void onDateSelect(SelectEvent selectEvent) {  
		
		isUpdate = false;
		scheduledClasses = scheduledClassService.findAll();
		
		dateStart = (Date) selectEvent.getObject();
		dateStart.setHours(dateStart.getHours() - 1);
		dateStart.setMinutes(0);
		dateStart.setSeconds(0);
		dateStartString = DateUtil.formatToYearMonthDayHourMinSec(dateStart);		
		
		dateEnd = new Date(dateStart.getTime());
		dateEnd.setHours(dateEnd.getHours() + 1);
		dateEnd.setMinutes(0);
		dateEnd.setSeconds(0);
		dateEndString = DateUtil.formatToYearMonthDayHourMinSec(dateEnd);
		
//		scheduleEvent = new DefaultScheduleEvent("New Scheduled Class", dateStart, dateEnd);  
	}
	 
	public void onChangeOneMenuCourses(AjaxBehaviorEvent event) {
		
		students.getSource().clear();
		students.getTarget().clear();
		teachers.getSource().clear();
		teachers.getTarget().clear();
		
		if(course != null) {
			
			Set<Student> studentsCourse = studentService.findByCourse(course.getId());
			
			for(Student s : studentsCourse) {
				students.getSource().add(s);
			}
			
			Set<Teacher> teachersCourse = teacherService.findByCourse(course.getId());
			for(Teacher t : teachersCourse){
				teachers.getSource().add(t);
			}
		}
		
	}

	public void onTransferStudent(TransferEvent event) {
		
	}
	
	public void onTransferTeacher(TransferEvent event) {  
        StringBuilder builder = new StringBuilder();  
        for(Object item : event.getItems()) {
        	
        	
//        	System.out.println(((Student)item).getName());
//            builder.append(((Player) item).getName()).append("<br />");  
        }  
	}
	
	private boolean validate() {
		
		if(course == null || classRoom == null || students.getTarget() == null || students.getTarget().isEmpty() ||
				teachers.getTarget() == null || teachers.getTarget().isEmpty()) {
			
			addErrorGrowlMessage("All fields (*) are required");
			return false;
		}
		return true;
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

	public List<ClassRoom> getClassRooms() {
		return classRooms;
	}

	public void setClassRooms(List<ClassRoom> classRooms) {
		this.classRooms = classRooms;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	public ScheduleModel getScheduleModel() {
		return scheduleModel;
	}

	public void setScheduleModel(ScheduleModel scheduleModel) {
		this.scheduleModel = scheduleModel;
	}

	public ScheduleEvent getScheduleEvent() {
		return scheduleEvent;
	}

	public void setScheduleEvent(ScheduleEvent scheduleEvent) {
		this.scheduleEvent = scheduleEvent;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public DualListModel<Student> getStudents() {
		return students;
	}

	public void setStudents(DualListModel<Student> students) {
		this.students = students;
	}

	public DualListModel<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(DualListModel<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public List<ScheduledClass> getScheduledClasses() {
		return scheduledClasses;
	}

	public void setScheduledClasses(List<ScheduledClass> scheduledClasses) {
		this.scheduledClasses = scheduledClasses;
	}

	public String getDateStartString() {
		return dateStartString;
	}

	public void setDateStartString(String dateStartString) {
		this.dateStartString = dateStartString;
	}

	public String getDateEndString() {
		return dateEndString;
	}

	public void setDateEndString(String dateEndString) {
		this.dateEndString = dateEndString;
	}

	public boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	
}