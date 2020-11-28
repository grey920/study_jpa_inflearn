package hellojpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/* 양방향 연관관계 */
@Entity
public class Team {
	
	@Id
	@GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	private String name;
	
	/* OneToMany는 읽기만 가능한 가짜 매핑(주인의 반대편),  "외래키가 있는 곳을 주인으로 정해라" */
	@OneToMany(mappedBy = "team")//지금 일대다 매핑에서 뭐와 연결되어 있는지, 나의 반대편 사이트에 뭐가 걸려있는지 적어준다 (Member클래스의 team)
	private List<Member> members = new ArrayList<Member>(); // add할때 null point가 안뜨게 하기 위해 관례상 arrayList로 초기화를 많이 쓴다.
	
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
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	
	
	
}
