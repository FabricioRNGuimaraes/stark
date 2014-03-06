package stark.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT")
public class Student extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_student")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDENT")
	@SequenceGenerator(name = "SEQ_STUDENT", sequenceName = "SEQ_STUDENT", allocationSize=1)
	private Integer id;
	
	@Column(name="name_student", nullable=false, length=100)
	private String name;

////	@DateTimeFormat(style = "S-")
////	@Temporal(TemporalType.DATE)
//	@Column(name="dt_register", nullable=false)
//	private Date dtRegister;
	
	@Column(name="fl_active", nullable=false)
	private Boolean active = true;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}