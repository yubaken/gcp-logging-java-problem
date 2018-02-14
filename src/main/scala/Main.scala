import com.google.cloud.logging.Logging.EntryListOption
import com.google.cloud.logging.LoggingOptions
import org.joda.time.DateTime

import scala.collection.JavaConverters._

object Main {
  def main(args: Array[String]) = {
    val projectId = "your gcp project id"
    val pageToken = ""
    val filter =
      s""" (
         |(timestamp>="2018-02-01T12:00:00.000+09:00" AND
         |timestamp<="2018-02-01T13:00:00.000+09:00")
         |)
        """.stripMargin
    val optionsBuilder = LoggingOptions.newBuilder()
    val option = optionsBuilder.setProjectId(projectId).build()
    val logging = option.getService
    val optionArray = Array(
      EntryListOption.filter(filter),
      EntryListOption.pageSize(10) // not working
    )
    if (pageToken.nonEmpty) {
      optionArray :+ EntryListOption.pageToken(pageToken) // not working
    }
    val entries = logging.listLogEntries(optionArray: _*)
    val result = entries.getValues.asScala
    println("First Date: " + new DateTime(result.head.getTimestamp))
    println("Last Date: " + new DateTime(result.last.getTimestamp))
    println("size: " + result.size)
    println("token: " + entries.getNextPageToken)
  }
}
