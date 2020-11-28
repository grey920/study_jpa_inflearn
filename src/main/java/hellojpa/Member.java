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
    
    /* 진짜 매핑 - 연관관계의 주인 (외래 키가 있는 곳을 주인으로 정한다) */
    @ManyToOne 
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

	@Override
	public String toString() {
		return "Member [id=" + id + ", username=" + username + ", team=" + team + "]";
	}

	// 관례적인 getter/setter가 아닌 중요한 일이란 느낌이 들게 메소드 명을 setOOO가 아닌 다른 이름으로 짓는다.
//	public void changeTeam(Team team) {
//		this.team = team;
//		// 연관관계 편의 메소드 - 양쪽에 값 셋팅
//		team.getMembers().add(this);
//		// 여기서 기존에 있던걸 뺀다거나 null 체크도 해줄 수 있음.
//	}
    
    

}
