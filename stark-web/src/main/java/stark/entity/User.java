package stark.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_user")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
	@SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER")
	private Integer id;
	
	@Column(name="name_user", nullable=false, length=50)
	private String name;

	@Column(name="password_user", nullable=false, length=50)
	private String password;

	@Column(name="fl_active", nullable=false)
	private Boolean active = true;
	
	@Column(name="fl_type", nullable=false)
	private Integer type = 0;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}