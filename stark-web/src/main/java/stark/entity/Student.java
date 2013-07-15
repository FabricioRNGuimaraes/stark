package stark.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT")
public class Student extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_student")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDENT")
	@SequenceGenerator(name = "SEQ_STUDENT", sequenceName = "SEQ_STUDENT", allocationSize=1)
	private Integer id;
	
	@Column(name="name_student", nullable=false, length=100)
	private String name;

	@Column(name="phone", length=10)
	private String phone;

//	@DateTimeFormat(style = "S-")
//	@Temporal(TemporalType.DATE)
	@Column(name="dt_register", nullable=false)
	private Date dtRegister;
	
	@Column(name="cpf", length=11)
	private String cpf;

	@Column(name="fl_active", nullable=false)
	private Boolean active;

	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="address", referencedColumnName="id_address")
	private Address address;
	
	@Column(name="email", length=50)
	private String email;
	
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDtRegister() {
		return dtRegister;
	}

	public void setDtRegister(Date dtRegister) {
		this.dtRegister = dtRegister;
	}
}