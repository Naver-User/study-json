package org.zerock.myapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.domain.Bar;
import org.zerock.myapp.domain.Foo;
import org.zerock.myapp.domain.MyPerson;

import com.cedarsoftware.util.io.JsonReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JSONDeserializeTests {
	private String json;
	
	
	@BeforeAll
	void beforeAll() {	// 1회성 전처리: 역직렬화할 JSON문자열을 개발/필드에 저장
		log.trace("beforeAll() invoked.");
		
		// Case1 - JSON 문자열 (일반객체)
//		final String json = """
//				{
//					"myName":"Yoseph",
//					"myAge": 23,
//					"eyePower": 1.5,
//					"gender": "female"
//				}
//				""";
		
		// Case2 - JSON 문자열 (배열객체)
		// 후보타입: (1) List (2) Set (3) 배열
//		final String json = "[ 1, 2, 3 ]";
		
		// Case3 - JSON 문자열 (요소가 객체인 컬렉션)
		final String json = """
			    [
					{
				        "name": "NAME_0",
				        "age": 0,
				        "weight": 60.1,
				        "height": 170.1,
				        "isMale": true
				    },
					{
				        "name": "NAME_1",
				        "age": 1,
				        "weight": 61.1,
				        "height": 171.1,
				        "isMale": false
				    }
				]
				""";
		
		log.info("\t+ json:\n{}", json);
		
		// 필드에 저장해서, 
		// 각 Case 별로 단위테스트에서 사용가능하도록 하자!
		this.json = json;		
	} // before

	
	@Disabled
	@Order(1)
//	@Test
	@RepeatedTest(1)
	@DisplayName("1. testCase1")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	// 주어진 JSON 문자열로부터, MyPerson 타입의 객체로 역변환(역직렬화)
	void testCase1() {
		log.trace("testCase1() invoked.");
		
		Gson gson = new Gson();
		
		MyPerson myPerson = 
			gson.<MyPerson>fromJson(json, MyPerson.class);
		
		assertNotNull(myPerson);
		log.info("\t+ myPerson: {}", myPerson);
	} // testCase1
	
	
//	@Disabled
	@Order(2)
//	@Test
	@RepeatedTest(1)
	@DisplayName("2. testCase2")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	// 주어진 JSON 문자열로부터, List/Set/배열 타입의 객체로 역변환(역직렬화)
	void testCase2() {
		log.trace("testCase2() invoked.");
		
		Gson gson = new Gson();
		
//		String[] obj =	// 배열로 역직렬화 
//			gson.<String[]>fromJson(json, String[].class);
		
		int[] obj =	// 배열로 역직렬화2 
			gson.<int[]>fromJson(json, int[].class);
		
//		List<String> obj = // List로 역직렬화
//			gson.<List<String>>fromJson(json, List.class);
		
//		List<Integer> obj = // List로 역직렬화2
//			gson.<List<Integer>>fromJson(json, List.class);
		
//		List<Number> obj = // List로 역직렬화3
//			gson.<List<Number>>fromJson(json, List.class);
		
//		Set<Integer> obj = // List로 역직렬화3
//			gson.<Set<Integer>>fromJson(json, Set.class);
		
		assertNotNull(obj);
//		log.info("\t+ obj: {}", Arrays.toString(obj));
		log.info("\t+ obj: {}", obj);
	} // testCase2
	
	
//	@Disabled
	@Order(3)
//	@Test
	@RepeatedTest(1)
	@DisplayName("3. testJacksonDeserialize")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	// jackson-databind 라이브러리 이용해서, 직렬화 수행하는 방법을 배우자
	void testJacksonDeserialize() throws JsonProcessingException {
		log.trace("testJacksonDeserialize() invoked.");
		
		// 역직렬화 수행대상 JSON 문자열 선언
		final String json = """
				{"id":1,"name":"Foo"}
				""";
		
		// -----------
		// jackson-databind 라이브러리를 이용한 직렬화
		// -----------
		// (1) 직렬화/역직렬화를 수행하는 핵심객체 생성
		ObjectMapper mapper = new ObjectMapper();
		
		// (2) 역직렬화 수행(JSON -> 주어진 타입의 자바객체로 역변환)
		Bar obj = mapper.readValue(json, Bar.class);
		
		Objects.requireNonNull(obj);
		log.info("\t+ obj: {}", obj);
	} // testJacksonDeserialize
	
	
//	@Disabled
	@Order(4)
//	@Test
	@RepeatedTest(1)
	@DisplayName("4. testJSONIODeserialize")
	@Timeout(value=1L, unit=TimeUnit.SECONDS)
	// json-io 라이브러리 이용해서, 직렬화 수행하는 방법을 배우자
	void testJSONIODeserialize() throws JsonProcessingException {
		log.trace("testJSONIODeserialize() invoked.");
		
		// 역직렬화 수행대상 JSON 문자열 선언
		final String json = """
				{
				    "@type": "org.zerock.myapp.Foo",
				    "id": 2,
				    "name": "BAR"
				}
				""";
		
		// -----------
		// json-io 라이브러리를 이용한 직렬화
		// -----------
		// (1) 역직렬화를 수행하는 핵심객체 생성
		Foo obj = (Foo) JsonReader.jsonToJava(json);
		
		Objects.requireNonNull(obj);
		log.info("\t+ obj: {}", obj);
	} // testJSONIODeserialize

} // end class
