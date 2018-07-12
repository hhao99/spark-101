package com.vw
// Import libraries
import org.apache.spark.sql.functions._

final case class Person(name:String, age: Long)

/**
 * @author timothy.findlay
 */
object ScalaLab01 extends InitSpark {

  def main(args: Array[String]) = {
    import spark.implicits._
    
    println("SPARK VERION = " + spark.version)

    val persons = reader.json("people.json").as[Person]
    persons.show()

    val averageAge = persons.agg(avg("age"))
                  .first.get(0).asInstanceOf[Double]

    println(f"Average age: $averageAge%.2f")

    close

  }

}