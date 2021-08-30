package com.appleyk.controller;

import com.appleyk.service.DiseaseQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appleyk.service.QuestionService;

@RestController
@RequestMapping("/rest/appleyk/question")
public class QuestionController {

    @Autowired
    QuestionService questService;

    @Autowired
    DiseaseQuestionService diseaseQuestionService;

    @RequestMapping("/query")
    public String query(@RequestParam(value = "question") String question) throws Exception {
        return diseaseQuestionService.answer(question);
    }

}
