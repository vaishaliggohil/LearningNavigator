package com.practice.mysqlproj.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.practice.mysqlproj.entites.Subject;
import com.practice.mysqlproj.services.SubjectService;

@WebMvcTest(controllers = SubjectController.class)
public class SubjectControllerTest {
 @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    @Test
    public void testGetSubjectById() throws Exception {
        Subject subject = new Subject();
        subject.setName("Mathematics");

        when(subjectService.getSubjectById(1L)).thenReturn(subject);

        mockMvc.perform(get("/subjects/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
