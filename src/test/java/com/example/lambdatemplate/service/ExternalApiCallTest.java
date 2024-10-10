// package com.example.lambdatemplate.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.ArgumentMatchers.eq;
// import java.util.List;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.web.client.RestTemplate;
// import com.example.lambdatemplate.api.model.Model;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import static org.mockito.Mockito.when;

// public class ExternalApiCallTest {

//     @Mock
//     private RestTemplate restTemplate;

//     @InjectMocks
//     private ExternalApiCall externalApiCall;

//     @BeforeEach
//     public void setUp(){
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     public void testGetFact_ListResponse() throws JsonProcessingException {
//         String mockJsonResponse = "[{\\\"fact\\\": \\\"Fact 1\\\"}, {\\\"fact\\\": \\\"Fact 2\\\"}]";

//         when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(mockJsonResponse);

//         Object result = externalApiCall.getFact();

//         assertNotNull(result);
//         assertTrue(result instanceof List);
//         List<Model> modelList = (List<Model>) result;
//         assertEquals(2, modelList.size());
//         assertEquals("Fact 1", modelList.get(0).getFact());
//     }

//     @Test
//     public void testGetFact_SingleObjectResponse() throws JsonProcessingException{
//         String mockJsonResponse = "{\\\"fact\\\": \\\"Single Fact\\\"}";
//         when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(mockJsonResponse);
//         Object result = externalApiCall.getFact();

//         assertNotNull(result);
//         assertTrue(result instanceof Model);
//         Model model = (Model) result;
//         assertEquals("Single Fact", model.getFact());
//     }

// }
