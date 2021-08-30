package com.appleyk.utils;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.rdd.RDD;
/**
 * Created by liangguannan on 2020/5/11.
 */
public class SparkDemo {

    public static void main(String[] args) {
        // spark配置对象
        SparkConf conf = new SparkConf().setMaster("local[3]").setAppName("bayes");
        // javaSpark上下文
        JavaSparkContext jsc = new JavaSparkContext(conf);
        // 读取数据两种方式(二选一，一个jvm只能开启一个context)

        // 方式一：
        // 数据读取为JavaRDD
        JavaRDD<String> lines = jsc.textFile("C:\\Users\\liangguannan\\Desktop\\bayes.txt");
        // 转化数据为LabeledPoint类型的JavaPDD
        JavaRDD<LabeledPoint> data = lines.map(new Function<String, LabeledPoint>() {
            public LabeledPoint call(String line) throws Exception {
                String[] arr = line.split(",");
                String[] featuresString = arr[1].split(" ");
                double[] featuresDouble = new double[featuresString.length];
                int index = 0;
                for(String num:featuresString){
                    featuresDouble[index] = Double.parseDouble(num);
                    index ++;
                }

                return new LabeledPoint(Double.valueOf(arr[0]),Vectors.dense(featuresDouble));
            }
        });
        // 将javaRDD转化为RDD
        RDD<LabeledPoint> trainRDD = data.rdd();

        // 方式二：
        // SparkContext sc = new SparkContext(conf);
        // RDD<LabeledPoint> trainEDD1 = MLUtils.loadLabeledPoints(sc,"C:\\Users\\asus\\Desktop\\data\\bayes.txt");

        // 训练数据获取训练模型
        NaiveBayesModel model = NaiveBayes.train(trainRDD);
        // 测试数据打印测试结果
        Double res1 = model.predict(Vectors.dense(2.0,1.0,0.0,1.0));
        Double res2 = model.predict(Vectors.dense(2.0,1.0,0.0,1.0));
        System.out.println("测试结果为：" + res1 + " " + res2);
    }

}
