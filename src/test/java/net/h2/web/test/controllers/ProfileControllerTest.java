package net.h2.web.test.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import net.h2.web.core.config.MvcConfig;
import net.h2.web.mob.profile.IProfileService;
import net.h2.web.test.config.TestConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, MvcConfig.class})
@WebAppConfiguration
public class ProfileControllerTest {

    private static final Long MOBILE = 9391209988L;

    private MockMvc mockMvc;

    @Autowired
    private IProfileService profileServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(profileServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }



    @Test
    public void findById_TodoEntryFound_ShouldAddTodoEntryToModelAndRenderViewTodoEntryView() throws Exception {
   

      //  when(profileServiceMock.findProfileByMobile(MOBILE)).thenReturn(true);

        ResultActions andExpect = mockMvc.perform(get("/profile/isExistProfile/{mobile}", MOBILE))
                .andExpect(status().isOk());

        verify(profileServiceMock, times(1)).findProfileByMobile(MOBILE);
        verifyNoMoreInteractions(profileServiceMock);
    }

//    @Test
//    public void findById_TodoEntryNotFound_ShouldRender404View() throws Exception {
//        when(todoServiceMock.findById(ID)).thenThrow(new TodoNotFoundException(""));
//
//        mockMvc.perform(get("/todo/{id}", ID))
//                .andExpect(status().isNotFound())
//                .andExpect(view().name(ErrorController.VIEW_NOT_FOUND))
//                .andExpect(forwardedUrl("/WEB-INF/jsp/error/404.jsp"));
//
//        verify(todoServiceMock, times(1)).findById(ID);
//        verifyZeroInteractions(todoServiceMock);
//    }

}
