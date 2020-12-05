package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member extends BaseEntity {
    
  
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID") 
    private Long id;
    
    @Column(name ="USERNAME") 
    private String username; 
    
    @ManyToOne(fetch = FetchType.LAZY) // team을 프록시 객체로 조회한다. 즉 , Member클래스만 DB에서 조회한다
    @JoinColumn
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
