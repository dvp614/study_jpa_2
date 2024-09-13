package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity(name = "User")
@Table(name = "Users")
@EntityListeners(CommonEntityLifecycleListener.class)
@SequenceGenerator(name = "id_generator", sequenceName = "seq_users")
//@SequenceGenerator(
//		name = "id_generator", 
//		sequenceName = "seq_users", 
//		initialValue = 1, 
//		allocationSize = 1)
public class Member implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	private Long id;

	@Column(length = 50, nullable = false)
	private String name;

	@Basic
	private Integer age;
	
	@CreationTimestamp
	@Basic(optional = false)
	private Date createDate;
	
	@UpdateTimestamp
	@Basic(optional = true)
	private LocalDateTime lastModifiedDate;

//	@PostLoad
//	void postLoad() {
//		log.trace("1. postLoad() invoked.");
//	} // postLoad
//
//	@PrePersist
//	void prePersist() {
//		log.trace("2. prePersist() invoked.");
//	} // prePersist
//
//	
//	@PostPersist
//	void postPersist() {
//		log.trace("3. postPersist() invoked.");
//
//	} // postPersist
//
//	@PreUpdate
//	void preUpdate() {
//		log.trace("4. preUpdate() invoked.");
//
//	} // preUpdate
//
//	@PostUpdate
//	void postUpdate() {
//		log.trace("5. postUpdate() invoked.");
//
//	} // postUpdate
//
//	@PreRemove
//	void preRemove() {
//		log.trace("6. preRemove() invoked.");
//
//	} // preRemove
//
//	@PostRemove
//	void postRemove() {
//		log.trace("7. postRemove() invoked.");
//
//	} // postRemove
} // end class
