package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //JPA가 로딩될 때 이게 JPA가 쓰는거구나 인식. name은  현재 클래스와 동일한 이름을 사용. 다른 패키지에 같은 클래스 이름이 있고 JPA에 연관된 경우 사용. 
//@Table(name= "MBR") //매핑할 때 db 테이블 이름이 클래스명과 다르다면 여기서 DB 테이블 이름 지정.-> Insert쿼리에서 MBR로 나간다. 
public class Member {
    
    /* DB에 들어있는 두 개의 컬럼 */
    // 최소한 jpa에게 pk가 뭔지는 알려주어야 한다 -> @Id
    @Id
    private Long id;
    
   // @Column(unique = true, length = 10) // 실행에는 영향을 주진 않고 DDL 생성에만 영향을 준다. (JPA의 로직과 관련x)
    private String name;
    private int gogo;
    
    // JPA는 내부적으로 리플렉션을 쓰기 때문에 동적으로 객체를 생성해야 한다. 따라서 기본 생성자가 하나 있어야 함. 
    public Member() {
    	
    }
    
    public Member(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
}
