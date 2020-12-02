package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") // 엔티티명이 기본값
public class Book extends Item {

	private String author;
	private String isbn;
}
