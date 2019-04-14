package org.opensoft.projecte;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTest {

	@Autowired
    private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }
	
	@WithMockUser(value = "user")
    @Test
    public void user_can_access_pages_for_user_only() throws Exception {
        mockMvc.perform(get("/user")).andExpect(status().isOk());
        mockMvc.perform(get("/admin")).andExpect(status().isForbidden());
        mockMvc.perform(get("/sudo")).andExpect(status().isForbidden());
    }
	
	@Test
	public void app_is_up() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}
	
	@Test
	public void login_works() throws Exception {
		this.mockMvc.perform(post("/login")
				.param("username", "user")
				.param("password", "password")
		).andExpect(status().isOk());
	}



}
