package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // 꼭 넣어야 함!! 그래야 JPA가 로딩될 때 이게 JPA가 쓰는거구나.. 내가 관리해야겠다.. 라고 생각함
//@Table(name= "USER") : db 테이블 이름이 USER로 클래스명과 다르다면 여기서 디비의 테이블명을 알려준다.
public class Member {
    
    /* DB에 들어있는 두 개의 컬럼 */
    // 최소한 jpa에게 pk가 뭔지는 알려주어야 한다 -> @Id
    @Id
    private Long id;
    private String name;
    
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
