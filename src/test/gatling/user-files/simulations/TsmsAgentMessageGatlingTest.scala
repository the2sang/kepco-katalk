import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the TsmsAgentMessage entity.
 */
class TsmsAgentMessageGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:9070"""

    val httpConf = http
        .baseUrl(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
        .silentResources // Silence all resources like css or css so they don't clutter the results

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test the TsmsAgentMessage entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))
        ).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJson
        .check(header("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(2)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all tsmsAgentMessages")
            .get("/services/kepcokatalk/api/tsms-agent-messages")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new tsmsAgentMessage")
            .post("/services/kepcokatalk/api/tsms-agent-messages")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "messageSeqno":null
                , "serviceSeqno":null
                , "sendMessage":"SAMPLE_TEXT"
                , "subject":"SAMPLE_TEXT"
                , "backupMessage":"SAMPLE_TEXT"
                , "backupProcessCode":"SAMPLE_TEXT"
                , "messageType":"SAMPLE_TEXT"
                , "contentsType":"SAMPLE_TEXT"
                , "receiveMobileNo":"SAMPLE_TEXT"
                , "callbackNo":"SAMPLE_TEXT"
                , "jobType":"SAMPLE_TEXT"
                , "sendReserveDate":"2020-01-01T00:00:00.000Z"
                , "templateCode":"SAMPLE_TEXT"
                , "mmsImgPath1":"SAMPLE_TEXT"
                , "mmsImgPath2":"SAMPLE_TEXT"
                , "mmsImgPath3":"SAMPLE_TEXT"
                , "imgAttachFlag":"SAMPLE_TEXT"
                , "kkoBtnName":"SAMPLE_TEXT"
                , "kkoBtnUrl":"SAMPLE_TEXT"
                , "kkoBtnLink1":"SAMPLE_TEXT"
                , "kkoBtnLink2":"SAMPLE_TEXT"
                , "kkoBtnLink3":"SAMPLE_TEXT"
                , "kkoBtnLink4":"SAMPLE_TEXT"
                , "kkoBtnLink5":"SAMPLE_TEXT"
                , "kkoImgPath":"SAMPLE_TEXT"
                , "kkoImgLinkUrl":"SAMPLE_TEXT"
                , "taxLevel1Nm":"SAMPLE_TEXT"
                , "taxLevel2Nm":"SAMPLE_TEXT"
                , "registerBy":"SAMPLE_TEXT"
                , "registerDate":"2020-01-01T00:00:00.000Z"
                , "custBackupFlag":"SAMPLE_TEXT"
                , "custMessageType":"SAMPLE_TEXT"
                , "custBackupDate":"2020-01-01T00:00:00.000Z"
                , "custData1":"SAMPLE_TEXT"
                , "custData2":"SAMPLE_TEXT"
                , "custData3":"SAMPLE_TEXT"
                , "custData4":"SAMPLE_TEXT"
                , "custData5":"SAMPLE_TEXT"
                , "custData6":"SAMPLE_TEXT"
                , "custData7":"SAMPLE_TEXT"
                , "custData8":"SAMPLE_TEXT"
                , "custData9":"SAMPLE_TEXT"
                , "custData10":"SAMPLE_TEXT"
                , "sendFlag":"SAMPLE_TEXT"
                , "sendDate":"2020-01-01T00:00:00.000Z"
                , "resendFlag":"SAMPLE_TEXT"
                , "mmsImgServerPath1":"SAMPLE_TEXT"
                , "mmsImgServerPath2":"SAMPLE_TEXT"
                , "mmsImgServerPath3":"SAMPLE_TEXT"
                , "imgSendDate":"2020-01-01T00:00:00.000Z"
                , "updateDate":"2020-01-01T00:00:00.000Z"
                , "updateBy":"SAMPLE_TEXT"
                , "kkoImgServerPath":"SAMPLE_TEXT"
                , "intfNm":"SAMPLE_TEXT"
                , "sendedId":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_tsmsAgentMessage_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created tsmsAgentMessage")
                .get("/services/kepcokatalk${new_tsmsAgentMessage_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created tsmsAgentMessage")
            .delete("/services/kepcokatalk${new_tsmsAgentMessage_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
