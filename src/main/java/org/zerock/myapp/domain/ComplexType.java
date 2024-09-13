package org.zerock.myapp.domain;

import java.util.Set;

import lombok.Data;


@Data
public class ComplexType {
	private int[] field1;
	private Bar field2;
	private Foo field3;
	private Person field4;
	private Set<Integer> field5;
	

} // end class


