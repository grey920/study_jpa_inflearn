package hellojpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
/* 양방향 연관관계 */
@Entity
public class Team {
	
	@Id
	@GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	private String name;
	
	/* 일대다관계 - 여기서 외래키를 관리한다 */
	@OneToMany
	@JoinColumn(name="TEAM_ID")
	private List<Member> members = new ArrayList<Member>(); 
	

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
	public List<Member> getMembers() {
		return members;
	}
	
	
}
