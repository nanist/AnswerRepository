package com.appleyk.service.classify;

import com.appleyk.Neo4j.GraphInfo;
import com.appleyk.Neo4j.SessionCreater;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import java.util.List;

/**
 * 问答分类的工具类
 * Created by liangguannan on 2020/5/14.
 */
public class ClassifyUtils {

    GraphInfo graphInfo= new GraphInfo();

    /**
     * 治疗
     *
     * @param reStrings
     * @return
     */
    public String getTreatment(List<String> reStrings) {
        String title;
        String answer=null;
        title = reStrings.get(2);
        String queryRelation = String.format("MATCH (n:%s) WHERE n.title = '%s' return n ","coronavirus",title);//

        Session session = SessionCreater.getSession();
        StatementResult result = session.run(queryRelation);

        List<String> list=graphInfo.getNODE(result);

        if (list.size()>0){
            answer=list.get(0);
        }


        return answer;
    }
}
