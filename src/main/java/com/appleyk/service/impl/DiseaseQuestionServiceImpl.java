package com.appleyk.service.impl;

import com.appleyk.core.CoreProcessor;
import com.appleyk.repository.DiseaseRepository;
import com.appleyk.service.DiseaseQuestionService;
import com.appleyk.service.classify.ClassifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liangguannan on 2020/5/13.
 */
@Service
@Primary
public class DiseaseQuestionServiceImpl implements DiseaseQuestionService {

    @Autowired
    private CoreProcessor queryProcess;

    ClassifyUtils classifyUtils =new ClassifyUtils();

    /**
     * <p>核心问答业务实现类</p>
     * @param question
     * @return
     * @throws Exception
     */
    @Override
    public String answer(String question) throws Exception {
        List<String> reStrings = queryProcess.analysis(question);
        int modelIndex = Integer.valueOf(reStrings.get(0));
        String answer =null;

        /**匹配问题模板*/
        answer = classifyUtils.getTreatment(reStrings);

        System.out.println("------answer:"+answer);
        if (answer != null && !"".equals(answer) && !("\\N").equals(answer)) {
            return answer;
        } else {
            return "sorry,知识库中没有检索到你要的答案！";
        }
    }


}
