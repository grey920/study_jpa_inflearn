package hellojpa;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass //매핑 정보만 받는 부모클래스(슈퍼클래스)
public abstract class BaseEntity {

	 // 모든 테이블에 반복 
	@Column(name="INSERT_MEMBER")
    private String createdBy;
    private LocalDateTime createdDate;
    @Column(name="UPDATE_MEMBER")
    private String modifiedBy;
    private LocalDateTime lastModifiedDate;
    
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

    
}
