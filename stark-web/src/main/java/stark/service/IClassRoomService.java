package stark.service;

import java.util.List;

import stark.entity.ClassRoom;

public interface IClassRoomService {

	List<ClassRoom> findAll();
	boolean save(ClassRoom classRoom);
	boolean remove(ClassRoom classRoom);
	boolean update(ClassRoom classRoom);
}
