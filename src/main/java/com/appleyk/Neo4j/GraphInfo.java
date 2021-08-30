package com.appleyk.Neo4j;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;
import org.neo4j.driver.v1.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangguannan on 2020/5/14.
 */
public class GraphInfo {


    /**
     * 解析neo4j结果 取除节点
     * @param result
     * @return
     */
    public List<String> getNODE(StatementResult result) {

        List<String> nodeids = new ArrayList<String>();
        List<String> nodeList = new ArrayList<String>();

        if (result.hasNext()) {
            List<Record> records = result.list();
            for (Record recordItem : records) {
                List<Pair<String, Value>> f = recordItem.fields();

                for (Pair<String, Value> pair : f) {
                    String typeName = pair.value().type().name();
                    if (typeName.equals("NODE")) {
                        Node noe4jNode = pair.value().asNode();
                        String id = String.valueOf(noe4jNode.id());

                        if(!nodeids.contains(id)) {
                            Map<String, Object> map = noe4jNode.asMap();
                            //System.out.println("NODE Map:"+map);

                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                String key = entry.getKey();
                                String Value=entry.getValue().toString();
                                //System.out.println("key:"+key);
                                //System.out.println("Value:"+entry.getValue());

                                if ("describe".equals(key)){
                                    nodeList.add(Value);
                                }
                            }

                        }
                        nodeids.add(id);

                    }
                }
            }
        }

        return nodeList;
    }
    /**
     * 解析知识图谱 所有节点和关系
     * @throws Exception
     * @param result
     */
    public JSONObject getGraphNodeAndRelationship(StatementResult result){
        JSONObject jsonObject= new JSONObject();

        JSONArray nodes0JsonArray = new JSONArray();//顶层节点

        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();

        List<String> nodeids = new ArrayList<String>();
        List<String> shipids = new ArrayList<String>();


        if (result.hasNext()) {
            List<Record> records = result.list();
            for (Record recordItem : records) {
                List<Pair<String, Value>> f = recordItem.fields();

                HashMap<String, Object> rss = new HashMap<String, Object>();
                HashMap<String, Object> rss1 = new HashMap<String, Object>();
                for (Pair<String, Value> pair : f) {
                    String typeName = pair.value().type().name();
                    if (typeName.equals("NODE")) {
                        Node noe4jNode = pair.value().asNode();
                        String id = String.valueOf(noe4jNode.id());

                        System.out.println("neo4j 分配的id:"+id);//

                        if(!nodeids.contains(id)) {
                            Map<String, Object> map = noe4jNode.asMap();
                            System.out.println("NODE Map:"+map);

                            JSONObject jsonObject1 = new JSONObject();
                            String title=null;
                            String level=null;
                            String describe=null;

                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                String key = entry.getKey();
                                String Value=entry.getValue().toString();
                                System.out.println("key:"+key);
                                System.out.println("Value:"+entry.getValue());

                                if ("title".equals(key)){
                                    title=Value;
                                }
                                if ("level".equals(key)){
                                    level=Value;
                                }
                                if ("describe".equals(key)){
                                    describe=Value;
                                }
                            }

                            jsonObject1.put("title", title);
                            jsonObject1.put("uuid", id);
                            jsonObject1.put("level", level);
                            jsonObject1.put("describe", describe);

                            if ("0".equals(level)){
                                nodes0JsonArray.add(jsonObject1);
                            }else {
                                jsonArray.add(jsonObject1);
                            }
                        }
                        nodeids.add(id);

                    }else if (typeName.equals("RELATIONSHIP")) {
                        Relationship rship = pair.value().asRelationship();
                        String uuid = String.valueOf(rship.id());
                        String type = String.valueOf(rship.type());

                        if (!shipids.contains(uuid)) {
                            String sourceid = String.valueOf(rship.startNodeId());
                            String targetid = String.valueOf(rship.endNodeId());
                            Map<String, Object> map = rship.asMap();
                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                String key = entry.getKey();
                                rss.put(key, entry.getValue());

                                System.out.println("--1111111--key:"+key);
                                System.out.println("--111111111111---Value:"+entry.getValue());
                            }
                            rss1.put("uuid", uuid);
                            rss1.put("type", type);
                            rss1.put("sourceid", sourceid);
                            rss1.put("targetid", targetid);

                            jsonArray1.add(rss1);
                        }
                        shipids.add(uuid);

                    }else if (typeName.equals("PATH")){

                        Path path=pair.value().asPath();
                        Iterable<Node> nodes=path.nodes();

                        for (Node n:nodes){

                            JSONObject jsonObject1 = new JSONObject();
                            String title=null;
                            String level=null;
                            String describe=null;

                            String id = String.valueOf(n.id());
                            Map<String, Object> map = n.asMap();

                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                String key = entry.getKey();
                                String Value=entry.getValue().toString();
                                System.out.println("key:"+key);
                                System.out.println("Value:"+entry.getValue());

                                if ("title".equals(key)){
                                    title=Value;
                                }
                                if ("level".equals(key)){
                                    level=Value;
                                }
                                if ("describe".equals(key)){
                                    describe=Value;
                                }
                            }

                            jsonObject1.put("title", title);
                            jsonObject1.put("uuid", id);
                            jsonObject1.put("level", level);
                            jsonObject1.put("describe", describe);

                            jsonArray.add(jsonObject1);
                        }

                        Iterable<Relationship> relationship=path.relationships();
                        for (Relationship rship:relationship){

                            String uuid = String.valueOf(rship.id());
                            String type = String.valueOf(rship.type());

                            String sourceid = String.valueOf(rship.startNodeId());
                            String targetid = String.valueOf(rship.endNodeId());
                            Map<String, Object> map = rship.asMap();
                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                String key = entry.getKey();
                                rss.put(key, entry.getValue());
                            }
                            rss1.put("uuid", uuid);
                            rss1.put("type", type);
                            rss1.put("sourceid", sourceid);
                            rss1.put("targetid", targetid);

                            jsonArray1.add(rss1);
                        }
                    }else {
                        rss.put(pair.key(),pair.value().toString());
                    }
                }

            }
        }

        if (nodes0JsonArray.size()>0){//如果 搜索结果没有包含 顶层节点没有
            jsonArray.add(0,nodes0JsonArray.get(0));
        }

        jsonObject.put("nodes",jsonArray);
        jsonObject.put("links",jsonArray1);
        System.out.println("-----jsonObject------"+jsonObject);

        return jsonObject;
    }

}
