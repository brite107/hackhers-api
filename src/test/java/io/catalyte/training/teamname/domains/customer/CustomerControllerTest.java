package io.catalyte.training.teamname.domains.customer;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CustomerControllerTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  ResultMatcher okStatus = MockMvcResultMatchers.status().isOk();
  ResultMatcher createdStatus = MockMvcResultMatchers.status().isCreated();
  ResultMatcher expectedType = MockMvcResultMatchers.content()
      .contentType(MediaType.APPLICATION_JSON);


  @Before
  public void setup() {
    DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
    this.mockMvc = builder.build();
  }

//  @DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
//  @Test
//  public void queryCustomers() throws Exception {
//    mockMvc
//        .perform(get("/customers"))
//        .andExpect(jsonPath("$", hasSize(4)))
//        .andExpect(okStatus)
//        .andExpect(expectedType);
//  }
//
//  @DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
//  @Test
//  public void getCustomer() throws Exception {
//    mockMvc
//        .perform(get("/customers/{email}", "jperry@mail.com"))
//        .andExpect(jsonPath("$.firstName", is("Jen")))
//        .andExpect(okStatus)
//        .andExpect(expectedType);
//  }
//
//  @Test
//  public void saveCustomer() throws Exception {
//    String json = "{\"firstName\":\"Nicole\",\"lastName\":\"Townsend\",\"email\":\"Nicole@gmail.com\",\"address\":\"1222 Web Way Portland, OR 97124\",\"phoneNumber\":\"312-444-5555\",\"street\":\" 111 Love way\",\"city\":\"Hillsboro\",\"state\":\"OR\",\"zipCode\":\"97267\"}";
//    mockMvc
//        .perform(post("/customers")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(json))
//        .andExpect(jsonPath("$.firstName", is("Nicole")))
//        .andExpect(createdStatus)
//        .andExpect(expectedType);
//  }
}