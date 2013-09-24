package stark.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import stark.entity.ClassRoom;
import stark.service.IClassRoomService;
import stark.service.impl.ClassRoomServiceImpl;

@ManagedBean(name="classRoomBean")
@ViewScoped
public class ClassRoomBean extends GenericBean {

	private static final long serialVersionUID = 1L;

	private List<ClassRoom> classRooms = new ArrayList<ClassRoom>();
	private ClassRoom classRoom = new ClassRoom();
	private Boolean newEntity = false;
	
	private IClassRoomService classRoomService;
	
	public void initialize() {

		classRoomService = new ClassRoomServiceImpl();
		classRooms = classRoomService.findAll();
	}

	public void save(ActionEvent event) {

		if(!newEntity && classRoom.getId() == null) {
			addInfoGrowlMessage("Class Room " + classRoom.getDescription() + " saved!");
			classRoomService.save(classRoom);
		} else {
			
			addInfoGrowlMessage("Class Room " + classRoom.getDescription() + " updated!");
			classRoomService.update(classRoom);
		}
		classRooms = classRoomService.findAll();
	}
	
	public void remove(ActionEvent event) {
		
		classRoomService.remove(classRoom);
		classRooms = classRoomService.findAll();
		addInfoGrowlMessage("Class Room " + classRoom.getDescription() + " deactivated!");
	}
	
	public void clean(ActionEvent ev) {
		clean();
	}
	
	private void clean() {
		
		classRoom = new ClassRoom();
		newEntity = false;
	}
	
	public void prepareEdit() {
		
		newEntity = true;
	}

	public List<ClassRoom> getClassesRoom() {
		return classRooms;
	}

	public void setClassesRoom(List<ClassRoom> classesRoom) {
		this.classRooms = classesRoom;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	public Boolean getNewEntity() {
		return newEntity;
	}

	public void setNewEntity(Boolean newEntity) {
		this.newEntity = newEntity;
	}

	public List<ClassRoom> getClassRooms() {
		return classRooms;
	}

	public void setClassRooms(List<ClassRoom> classRooms) {
		this.classRooms = classRooms;
	}

}