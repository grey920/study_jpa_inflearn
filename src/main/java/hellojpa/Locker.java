package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {

    @Id @GeneratedValue
    private Long id;
    
    private String name;
    
    @OneToOne(mappedBy = "locker") // 일대일 양방향일때
    private Member member;
}
