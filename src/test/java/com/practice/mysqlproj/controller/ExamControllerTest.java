package com.practice.mysqlproj.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.practice.mysqlproj.services.ExamService;
import com.practice.mysqlproj.services.NumberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ExamController.class)
public class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NumberService numberService;

    @MockBean
    private ExamService examService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStudentsByExam() throws Exception {
        // Mock behavior for retrieving students by exam
        when(examService.getStudentsByExam(anyLong())).thenReturn(Arrays.asList());

        mockMvc.perform(get("/exams/1/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNumberFact() throws Exception {
        String numberFact = "42 is the answer to life, the universe, and everything.";
        when(numberService.getNumberFact(anyInt())).thenReturn(numberFact);

        mockMvc.perform(get("/exams/hidden-feature/42")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(numberFact));
    }
}
