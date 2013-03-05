package stark.entity;

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
@Table(name="TEACHER")
public class Teacher extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_teacher")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEACHER")
	@SequenceGenerator(name = "SEQ_TEACHER", sequenceName = "SEQ_TEACHER", allocationSize=1)
	private Integer id;
	
	@Column(name="name_teacher", nullable=false, length=100)
	private String name;

	@Column(name="phone", length=10)
	private String phone;

	@Column(name="cpf", length=11)
	private String cpf;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="address", referencedColumnName="id_address")
	private Address address;

	@Column(name="fl_active", nullable=false)
	private Boolean active;
	
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}