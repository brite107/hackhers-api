package io.catalyte.training.teamname.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * This is the test file for the security folder.
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SecurityTest {

  @Autowired
  private static MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;
  private JSONObject validCredentialObject;

  @Before
  public void setUp() throws JSONException {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    validCredentialObject = new JSONObject();
    validCredentialObject.put("email", "jperry@mail.com");
    validCredentialObject.put("password", "IamJen4");
  }

  @Test
  public void successfulCustomerLoginReturnsToken() throws Exception {
    mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(validCredentialObject.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.token").exists())
        .andReturn();
  }


  @Test
  public void loginWithBadPasswordIs400() throws Exception {
    JSONObject credentialObject = validCredentialObject;
    credentialObject.put("password", "not a password");

    mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(credentialObject.toString()))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.token").doesNotExist())
        .andReturn();
  }

  @Test
  public void loginWithBadUsernameIs404() throws Exception {
    JSONObject credentialObject = validCredentialObject;
    credentialObject.put("email", "scooby@bademail.com");

    mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(credentialObject.toString()))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.token").doesNotExist())
        .andReturn();
  }

}
