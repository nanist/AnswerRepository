package com.appleyk.Neo4j;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * neo4j连接工具类
 */
public class SessionCreater {

    private static Driver driver;

    private static Session session;

    static {
        driver = GraphDatabase.driver( "bolt://10.10.202.118:7687", AuthTokens.basic( "neo4j", "123456" ) );
        session = driver.session();
    }

    public static Session getSession(){
        return session;
    }

    public void close(){
        session.close();
        driver.close();
    }



}
