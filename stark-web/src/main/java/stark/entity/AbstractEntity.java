package stark.entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//@MappedSuperclass
//@Access(AccessType.FIELD)
public class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Id
//	@Column(name="id", length=36, nullable=false, unique=true)
//	private String id;
//	
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
}
