package org.zerock.myapp.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.util.PersistenceUnits;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberTests {
	private EntityManagerFactory emf;
	private EntityManager em;

	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		this.emf = Persistence.createEntityManagerFactory(PersistenceUnits.H2);

		assertNotNull(emf);

		this.em = this.emf.createEntityManager();
		assert this.em != null;
	} // beforeAll

	@AfterAll
	void afterAll() {
		log.trace("afterAll() invoked.");

		try {
			Objects.requireNonNull(this.em);

			this.em.close();
		} finally {
			Objects.requireNonNull(this.emf);

			if (this.emf.isOpen()) {
				this.emf.close();
			} // if
		} // try-finally
//		this.emf.close();
	} // afterAll

//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1. createEntity")
	@Timeout(value = 5L, unit = TimeUnit.MINUTES)
	void createEntity() {
		log.trace("createEntity() invoked.");

		EntityTransaction tx = this.em.getTransaction();

		try {
			tx.begin();

			Member transientMember = new Member();
			transientMember.setName("NAME");
			transientMember.setAge(23);
//			transientMember.setCreateDate(new Date());
			
			this.em.persist(transientMember);

			tx.commit();
		} catch (Exception _original) {
			tx.rollback();
			throw new IllegalStateException(_original);
		} // try-catch
	} // createEntity
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("2. findMember")
	@Timeout(value = 5L, unit = TimeUnit.MINUTES)
	void findMember() {
		log.trace("findMember() invoked.");

		final long PK = 1L;
		Member foundMember = this.em.<Member>find(Member.class, PK);
		
		assertNotNull(foundMember);
		
		log.info("\t+ foundMember : {}", foundMember);
	} // findMember
	
//	@Disabled
	@Order(3)
	@Test
//	@RepeatedTest(1)
	@DisplayName("3. updateTuple")
	@Timeout(value = 5L, unit = TimeUnit.MINUTES)
	void updateTuple() {
		log.trace("updateTuple() invoked.");
		
		EntityTransaction tx = this.em.getTransaction();
		
		try {
			tx.begin();
			
			final long PK = 1L;
			Member foundMember = this.em.<Member>find(Member.class, PK);
			
			assertNotNull(foundMember);
			foundMember.setName("Yoon2");
			foundMember.setAge(25);
			
			tx.commit();
		}catch(Exception _original){
			tx.rollback();
			throw new IllegalStateException(_original);
		} // try-catch
	} // updateTuple
	
//	@Disabled
	@Order(4)
	@Test
//	@RepeatedTest(1)
	@DisplayName("4. removeEntity")
	@Timeout(value = 5L, unit = TimeUnit.MINUTES)
	void removeEntity() {
		log.trace("removeEntity() invoked.");
		
		EntityTransaction tx = this.em.getTransaction();
		
		try {
			tx.begin();
			
			final long PK = 1L;
			Member foundMember = this.em.<Member>find(Member.class, PK);
			
			this.em.remove(foundMember);
			
			tx.commit();
		}catch(Exception _original){
			tx.rollback();
			throw new IllegalStateException(_original);
		} // try-catch
	} // removeEntity

} // end class
