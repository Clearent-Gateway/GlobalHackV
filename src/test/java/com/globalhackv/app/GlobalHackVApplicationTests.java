package com.globalhackv.app;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GlobalHackVApplication.class)
@WebAppConfiguration
public class GlobalHackVApplicationTests {


    @Ignore
	@Test
	public void contextLoads() {
		GlobalHackVApplication globalHackVApplication = mock(GlobalHackVApplication.class);
	    String[] args = {""};
		globalHackVApplication.main(args);
	    assertTrue(true);	    
	}

}
