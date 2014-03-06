package stark.service;

import java.util.Set;

import stark.entity.Teacher;

public interface ITeacherService extends IGenericService<Teacher> {

	Set<Teacher> findByCourse(Integer idCourse);

}
