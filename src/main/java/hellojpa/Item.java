package hellojpa;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) 
//@DiscriminatorColumn //TABLE_PER_CLASS전략은 테이블이 다 따로 생성이 되기 때문에 @DiscriminatorColumn로 구분할 필요가 없다.
public abstract class Item { // 일반 클래스로 만들면 상속과 상관없이 Item 혼자 독단적으로 사용할 수 있다는 뜻이므로 abstract 클래스로 만들어준다.
	
	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	private int price;

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
