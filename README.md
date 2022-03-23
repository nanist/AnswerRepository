# 安装neo4j数据
https://www.cnblogs.com/huzixia/p/10402421.html

# 导入neo4j数据
https://blog.csdn.net/u010797364/article/details/103787362


# Spring-Boot-Neo4j-Movies

#### 升级Spark依赖，由原来的2.3升级到2.4，GitHub官方提醒> = 1.0.0，<= 2.3.2之间的版本容易受到攻击
#### spark2.4  == >scala2.11 and scala2.12


```text

<!-- https://mvnrepository.com/artifact/org.apache.spark/spark-core -->
<dependency>
	<groupId>org.apache.spark</groupId>
	<artifactId>spark-core_2.12</artifactId>
	<version>2.4.0</version>
	<exclusions>
		<exclusion>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
		</exclusion>
	</exclusions>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.spark/spark-mllib -->
<dependency>
	<groupId>org.apache.spark</groupId>
	<artifactId>spark-mllib_2.12</artifactId>
	<version>2.4.0</version>
</dependency>


```



