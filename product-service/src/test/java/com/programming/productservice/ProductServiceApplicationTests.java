//package com.programming.productservice;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.programming.productservice.dto.ProductRequest;
//import com.programming.productservice.repository.ProductRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.math.BigDecimal;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@Testcontainers
//@AutoConfigureMockMvc
//class ProductServiceApplicationTests {
//
//	@Container
//	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
//
//	@Autowired
//	private MockMvc mockMvc;
//	//这里创建了一个 MockMvc 实例，用于模拟 HTTP 请求进行单元测试，能快速地测试 HTTP REST 请求。
//
//	@Autowired
//	private ObjectMapper objectMapper;
//	//这里创建了一个 ObjectMapper 实例，它是 Jackson 库的一部分，用于处理 JSON 数据。它可以将 Java 对象转换为 JSON，或将 JSON 转换为 Java 对象。
//
//	@Autowired
//	private ProductRepository productRepository;
//
//	@DynamicPropertySource
//	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
//		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//	}
//	//@DynamicPropertySource: 这是 Spring 的注解，用于动态地设置或覆盖 Spring 应用的配置属性。在你的代码中，这个注解用于动态设置 MongoDB 的 URL。
//	//setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) 这是你用来动态设置配置属性的方法。
//	// DynamicPropertyRegistry 是 Spring 提供的接口，用于注册配置属性。
//	//dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//	// 这行代码将 MongoDB 的 URL 设置为你的 MongoDB 容器的 URL
//	// 这样做的目的是让你的应用在测试时连接到这个容器，而不是连接到其他可能在运行的 MongoDB 实例
//	// mongoDBContainer::getReplicaSetUrl 是一个方法引用，它返回 MongoDB 容器的 URL。
//
//
//
//	@Test
//	void shouldCreateProduct() throws Exception{
//		ProductRequest productRequest = getProductRequest();
//		String productRequestString = objectMapper.writeValueAsString(productRequest);
//		mockMvc.perform(MockMvcRequestBuilders.post("/products")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(productRequestString))
//				.andExpect(status().isCreated());
//		Assertions.assertTrue(productRepository.findAll().size() == 1);
//	}
//
//	private ProductRequest getProductRequest(){
//		 return ProductRequest.builder()
//				.name("math")
//				.description("history of math")
//				.price(BigDecimal.valueOf(70))
//				.build();
//	}
//
//
//}
