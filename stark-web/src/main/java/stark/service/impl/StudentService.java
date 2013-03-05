package stark.service.impl;


import java.util.List;

import stark.dao.IStudentDAO;
import stark.entity.Student;
import stark.service.IStudentService;

//@Service
public class StudentService implements IStudentService {

//	@Autowired
	IStudentDAO genericDAO;
	
	@Override
//	@Transactional
	public boolean save(Student student) {
		// TODO Auto-generated method stub
		return genericDAO.save(student);
	}

	@Override
	public List<Student> findAll() {
//		Student a= new Student();
//		a.setName("fabricio guimarães");
//		a.setCpf("069.069.966.22");
//		a.setDtPayment(new Date());
//		
//		List<Student> list = new ArrayList<Student>();
//		list.add(a);
//		return list;
		return genericDAO.findAll();
	}

	public void setGenericDAO(IStudentDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

//	public IGenericDAO getGenericDAO() {
//		return genericDAO;
//	}
	

}
