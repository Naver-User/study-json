package org.zerock.myapp.domain;

import lombok.Data;


@Data					// DTO & JavaBeans
public class Person {	// POJO
	// 아래의 필드는 자바언어의 모든 기본타입을 표현하고 있습니다.
	private String name;
	private int age;
	private double weight;
	private double height;
	private boolean isMale;
	

} // end class
