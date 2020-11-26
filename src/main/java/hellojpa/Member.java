package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ManyToAny;

@Entity
public class Member {
    
  
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID") 
    private Long id;
    
    @Column(name ="USERNAME") 
    private String username; 
   
//    // 중요!! 
//    @Column(name="TEAM_ID") //디비 테이블에 맞춘 설계
//    private Long teamId;
    
    /* 관계가 뭔지와 이 관계를 할 때 조인하는 컬럼이 뭔지만 알려주면 단방향 연관관계는 끝*/
    @ManyToOne // 멤버입장에서는 Many 
    @JoinColumn(name = "TEAM_ID") // join해야 하는 컬럼이 뭔지 - erd에서 FK로 조인된 부분
    private Team team;

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

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
    
    

}
