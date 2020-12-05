package hellojpa;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ManyToAny;

@Entity
public class Member extends BaseEntity {
    
  
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID") 
    private Long id;
    
    @Column(name ="USERNAME") 
    private String username; 
    
    @ManyToOne
    @JoinColumn(name="TEAM_ID", insertable = false, updatable = false)
    private Team team;
    
    @OneToOne //@ManyToOne과 유사
    @JoinColumn(name="LOCKER_ID")
    private Locker locker;
 
   
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

	public Locker getLocker() {
		return locker;
	}

	public void setLocker(Locker locker) {
		this.locker = locker;
	}
	
	

}
