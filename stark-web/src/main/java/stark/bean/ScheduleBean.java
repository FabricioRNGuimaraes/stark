package stark.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.ScheduleModel;

import stark.dao.ClassRoomDAO;
import stark.dao.StudentDAO;
import stark.dao.TeacherDAO;
import stark.entity.ClassRoom;
import stark.entity.Student;
import stark.entity.Teacher;

@ManagedBean(name="scheduleBean")
@ViewScoped
public class ScheduleBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private ScheduleModel scheduleModel;
	private ClassRoom classRoom = new ClassRoom();
	private List<Student> studentsChoosed = new ArrayList<Student>();
	private List<Teacher> teachersChoosed = new ArrayList<Teacher>();
	private Date eventDate;
	
	private List<ClassRoom> classRooms;
	private DualListModel<Student> students;
	private DualListModel<Teacher> teachers;
	
	private ClassRoomDAO classRoomDAO;
	private StudentDAO studentDAO;
	private TeacherDAO teacherDAO;
	
	public void initialize() {
		
		classRoomDAO = new ClassRoomDAO();
		teacherDAO = new TeacherDAO();
		studentDAO = new StudentDAO();
		
		classRooms = classRoomDAO.findAll();
		List<Student> s = studentDAO.findAll();
		List<Teacher> t = teacherDAO.findAll();
		students = new DualListModel<Student>(s, studentsChoosed);
		teachers = new DualListModel<Teacher>(t, teachersChoosed);
	}
	
	public void onDateSelect(DateSelectEvent event) {
		eventDate =	event.getDate();
	}
	
	public void onEventSelect(ScheduleEntrySelectEvent event) {
		event.getSource();
	}
	
	public void save(ActionEvent event) {
		
		addInfoGrowlMessage("save success!");
	}
	public void save() {
		
		addInfoGrowlMessage("save success!");
	}
//	
//	public void onEventMove(ActionEvent event) {
//		event.getSource();
//	}
//	
//	public void onEventResize(ActionEvent event) {
//		event.getSource();
//	}

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

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	public List<Student> getStudentsChoosed() {
		return studentsChoosed;
	}

	public void setStudentsChoosed(List<Student> studentsChoosed) {
		this.studentsChoosed = studentsChoosed;
	}

	public List<Teacher> getTeachersChoosed() {
		return teachersChoosed;
	}

	public void setTeachersChoosed(List<Teacher> teachersChoosed) {
		this.teachersChoosed = teachersChoosed;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
}