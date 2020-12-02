package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") // 엔티티명이 기본값
public class Album extends Item {
	
	private String artist;

}
