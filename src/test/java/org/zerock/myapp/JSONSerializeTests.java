package org.zerock.myapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.domain.ComplexType;
import org.zerock.myapp.domain.Foo;
import org.zerock.myapp.domain.Bar;
import org.zerock.myapp.domain.Person;

import com.cedarsoftware.util.io.JsonWriter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JSONSerializeTests {
	private Object obj;
	
	
	@BeforeAll
	void beforeAll() {	// 1회성 전처리
		log.trace("beforeAll() invoked.");
		
		// Case1 - 단순 기본타입을 필드로 가지는 객체의 직렬화
//		Person person = new Person();
//		person.setName("Yoseph");
//		person.setAge(23);
//		person.setWeight(58.9);
//		person.setHeight(172.5);
//		person.setMale(true);
//		
//		log.info("\t+ person: {}", person);
		
		// Case2 - List<Person> 컬렉션 생성하여 필드에 저장
//		List<Person> list = new ArrayList<>();
//		
//		for(int counter=0;counter < 2; ++counter) {
//			Person person = new Person();
//			person.setName("NAME_" + counter);
//			person.setAge(counter);
//			person.setHeight(170.0 + counter);
//			person.setWeight(60.0 + counter);
//			person.setMale((counter == 0)? true : false);
//			
//			list.add(person);
//		} // classical for
//		
//		log.info("\t+ list<Person>: {}", list);
		
		// Case3 - Set<Person> 컬렉션 생성하여 필드에 저장
//		Set<Person> set = new HashSet<>();
//		
//		for(int counter=0;counter < 2; ++counter) {
//			Person person = new Person();
//			person.setName("NAME_" + counter);
//			person.setAge(counter);
//			person.setHeight(170.0 + counter);
//			person.setWeight(60.0 + counter);
//			person.setMale((counter == 0)? true : false);
//			
//			set.add(person);
//		} // classical for
//		
//		log.info("\t+ Set<Person>: {}", set);
		
		// Case4 - Map<String, Person> 컬렉션 생성하여 필드에 저장
//		Map<String, Person> map = new HashMap<>();
//		
//		for(int counter=0;counter < 2; ++counter) {
//			Person person = new Person();
//			person.setName("NAME_" + counter);
//			person.setAge(counter);
//			person.setHeight(170.1 + counter);
//			person.setWeight(60.1 + counter);
//			person.setMale((counter == 0)? true : false);
//			
//			map.put(String.valueOf(counter), person);
//		} // classical for
//		
//		log.info("\t+ Map<String, Person>: {}", map);
		
		// Case5 - Map<Integer, Person> 컬렉션 생성하여 필드에 저장
//		Map<Integer, Person> map = new HashMap<>();
//		
//		for(int counter=0;counter < 2; ++counter) {
//			Person person = new Person();
//			person.setName("NAME_" + counter);
//			person.setAge(counter);
//			person.setHeight(170.1 + counter);
//			person.setWeight(60.1 + counter);
//			person.setMale((counter == 0)? true : false);
//			
//			map.put(counter, person);
//		} // classical for
//		
//		log.info("\t+ Map<Integer, Person>: {}", map);
		
		// Case6 - 배열객체를 JSON으로 직렬화하기 위해, 배열을 생성
//		int[] array = { 1, 2, 3 };
		
		// Case7 - 배열객체를 JSON으로 직렬화하기 위해, 배열을 생성
//		String[] array = { "S1", "S2", "S3" };
//		
//		log.info("\t+ array: {}", Arrays.toString(array));
		
		// Case8 - 객체의 필드가 집합관계에 있는 또 다른 객체로 구성된 경우
		ComplexType complex = new ComplexType();
		complex.setField1(new int[] {1, 2, 3});
		
		Bar foo = new Bar();
		foo.setId(1);
		foo.setName("NAME1");
		
		complex.setField2(foo);
		
		Foo bar = new Foo();
		bar.setId(2);
		bar.setName("NAME2");
		
		complex.setField3(bar);
		
		Person person = new Person();
		person.setName("Yoseph");
		person.setAge(23);
		person.setWeight(58.9);
		person.setHeight(172.5);
		person.setMale(true);
				
		complex.setField4(person);
		
		complex.setField5(Set.<Integer>of(1, 2, 3, 4, 5, 6, 7));
		
		log.info("\t+ complex: {}", complex);
		
		this.obj = complex;	// 다형성-1: 그래도 자식타입객체는 그대로있다!
	} // beforeAll
	
	
	@Disabled
	@Order(1)	
	@Test
//	@RepeatedTest(1)	
	@DisplayName("1. Person -> JSON")
	@Timeout(1L)
	void testCase1() {
		log.trace("testCase1() invoked.");
		
		Gson gson = new Gson();
		
		String json = gson.toJson(this.obj);
		log.info("\t+ json: {}", json);
	} // testCase1
	
	
	@Disabled
	@Order(2)	
	@Test
//	@RepeatedTest(1)	
	@DisplayName("2. List<Person> -> JSON")
	@Timeout(1L)
	void testCase2() {
		log.trace("testCase2() invoked.");
		
		Gson gson = new Gson();
		
		String json = gson.toJson(this.obj);
		log.info("\t+ json: {}", json);
	} // testCase2
	
	
	@Disabled
	@Order(3)	
	@Test
//	@RepeatedTest(1)	
	@DisplayName("3. Set<Person> -> JSON")
	@Timeout(1L)
	void testCase3() {
		log.trace("testCase3() invoked.");
		
		Gson gson = new Gson();
		
		String json = gson.toJson(this.obj);
		log.info("\t+ json: {}", json);
	} // testCase3
	
	
	@Disabled
	@Order(4)	
	@Test
//	@RepeatedTest(1)	
	@DisplayName("4. Map<String, Person> -> JSON")
	@Timeout(1L)
	void testCase4() {
		log.trace("testCase4() invoked.");
		
		Gson gson = new Gson();
		
		String json = gson.toJson(this.obj);
		log.info("\t+ json: {}", json);
	} // testCase4
	
	
	@Disabled
	@Order(5)	
	@Test
//	@RepeatedTest(1)	
	@DisplayName("5. Map<Integer, Person> -> JSON")
	@Timeout(1L)
	void testCase5() {
		log.trace("testCase5() invoked.");
		
		Gson gson = new Gson();
		
		String json = gson.toJson(this.obj);
		log.info("\t+ json: {}", json);
	} // testCase5
	
	
	@Disabled
	@Order(6)	
	@Test
//	@RepeatedTest(1)	
	@DisplayName("6. int[] -> JSON")
	@Timeout(1L)
	void testCase6() {
		log.trace("testCase6() invoked.");
		
		Gson gson = new Gson();
		
		String json = gson.toJson(this.obj);
		log.info("\t+ json: {}", json);
	} // testCase6
	
	
	@Disabled
	@Order(7)	
	@Test
//	@RepeatedTest(1)	
	@DisplayName("7. String[] -> JSON")
	@Timeout(1L)
	void testCase7() {
		log.trace("testCase7() invoked.");
		
		Gson gson = new Gson();
		
		String json = gson.toJson(this.obj);
		log.info("\t+ json: {}", json);
	} // testCase7
	
	
//	@Disabled
	@Order(8)	
	@Test
//	@RepeatedTest(1)	
	@DisplayName("8. String[] -> JSON")
	@Timeout(1L)
	void testCase8() {
		log.trace("testCase8() invoked.");
		
		Gson gson = new Gson();
		
		String json = gson.toJson(this.obj);
		log.info("\t+ json: {}", json);
	} // testCase8
	
	
//	@Disabled
	@Order(9)
//	@Test
	@RepeatedTest(1)
	@DisplayName("9. testJacksonSerialize")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	// jackson-databind 라이브러리 이용해서, 직렬화 수행하는 방법을 배우자
	void testJacksonSerialize() throws JsonProcessingException {
		log.trace("testJacksonSerialize() invoked.");
		
		Bar foo = new Bar();
		foo.setId(1);
		foo.setName("Foo");
		
		// -----------
		// jackson-databind 라이브러리를 이용한 직렬화
		// -----------
		// (1) 직렬화/역직렬화를 수행하는 핵심객체 생성
		ObjectMapper mapper = new ObjectMapper();		
		
		// (2) 직렬화수행메소드를 통하여, 우리가 준 자바객체 -> JSON 문자열로 변환
		String json = mapper.writeValueAsString(foo);
		
//				기대조건식
//			   ------------
		assert json != null;
		log.info("\t+ json: {}", json);
	} // testJacksonSerialize
	
	
//	@Disabled
	@Order(10)
//	@Test
	@RepeatedTest(1)
	@DisplayName("10. testJSONIOSerialize")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	// json-io 라이브러리 이용해서, 직렬화 수행하는 방법을 배우자
	void testJSONIOSerialize() {
		log.trace("testJSONIOSerialize() invoked.");
		
		Foo obj = new Foo();
		obj.setId(2);
		obj.setName("BAR");
		
		// -------------
		// 직렬화를 수행하는 핵심타입
		// -------------
		String json = JsonWriter.objectToJson(obj);
		assertNotNull(json);
		log.info("\t+ json: {}", json);
	} // testJSONIOSerialize

} // end class


