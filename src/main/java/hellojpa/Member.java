package hellojpa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity //JPA가 로딩될 때 이게 JPA가 쓰는거구나 인식. name은  현재 클래스와 동일한 이름을 사용. 다른 패키지에 같은 클래스 이름이 있고 JPA에 연관된 경우 사용. 
public class Member {
    
    
    @Id
    private Long id;
    
    // @Column : 객체에는 username을 쓰고 싶은데 DB에는 name을 써야할 때
    @Column(name = "name", nullable = false, columnDefinition = "varchar(100) default 'EMPTY'") //insertable,updatable : 이 컬럼을 수정했을때 디비에 반영을 할건지 말건지. updatable을 false로 해두면 변경이 일어나도 절대 디비에 반영되지 않는다.
    private String username; //nullable = false : not null
    
    // 숫자가 엄청 큰 BigDecimal 경우는 소수점 옵션을 줄 수 있음. 소숫점 줄 때 쓰는 것. 
    private int age; // DB에도 적절한 숫자 타입이 선택된다.
    
    // 객체에서 ENUM타입을 쓰고 싶을 때 (DB에는 기본적으로 없음) -> VARCHAR와 매핑됨
    @Enumerated(EnumType.STRING) // ENUM타입은 ORDINAL과 STRING을 선택할 수 있다. 기본은 ORDINAL
    private RoleType roleType;
    
    // 날짜 타입을 쓸 떄 (타입은 세가지- DATE, TIME, TIMESTAMP)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    
    // 최신 버전은 하이버네이트가 알아서 지원, 예전 Date타입을 써야될 땐 Temporal 사용하기
    private LocalDate testLocalDate; // 년 월
    private LocalDateTime testLocalDateTime; // 년 월 일
    
    // 큰 컨텐츠를 쓸 때 쓰는 LOB
    @Lob
    private String description; // @LOB을 하고 타입을 String으로 지정하면 기본값으로 CLOB으로 컬럼타입이 매핑된다.
    
    @Transient
    private int temp;
    
    // JPA는 내부적으로 리플렉션을 쓰기 때문에 동적으로 객체를 생성해야 한다. 따라서 기본 생성자가 하나 있어야 함. 
    public Member() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

    
    
}
