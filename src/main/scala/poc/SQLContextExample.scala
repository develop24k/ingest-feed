package com.develop24k

import org.apache.spark.sql.{SQLContext, SparkSession}
import com.typesafe.config._

object SQLContextExample extends App {

  val config: Config = ConfigFactory.load()

  val spark = SparkSession.builder()
    .master("local[1]")
    .appName(config.getString("appname"))
    .getOrCreate();

  spark.sparkContext.setLogLevel("ERROR")


  val sqlContext:SQLContext = spark.sqlContext

  //read csv with options
  println(config.getString("feed_path"))

  val df = sqlContext.read.options(Map("inferSchema"->"true","delimiter"->",","header"->"true"))
    .csv(config.getString("feed_path")).cache()

//  df.repartition(4)
//    .write
//    .parquet("c:\\temp\\")
  df.show()
  df.printSchema()

  df.createOrReplaceTempView("TAB")
  sqlContext.sql("select * from TAB")
    .show(false)

}