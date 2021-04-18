package blackbox

import au.com.belong.customermaster.contactpoints.ContactPointsApplication
import au.com.belong.customermaster.contactpoints.model.PhoneContactPointsDto
import common.MockServer
import io.restassured.http.ContentType
import io.restassured.response.ResponseOptions
import org.apache.tools.ant.taskdefs.condition.Http
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import static io.restassured.RestAssured.given
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@ContextConfiguration(classes = ContactPointsApplication.class)
abstract class FunctionalSpec extends Specification {

    @LocalServerPort
    protected int port

    def setupSpec() {
        MockServer.startServer()
    }

    def httpClient(){
        given()
            .port(port)
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
    }

    def fetchAllContactPointsEndpoint(){
        return "cm/v1/contactpoints"
    }

    def fetchContactPointsByCustomerEndpoint(String customerId){
        return "cm/v1/contactpoints/customer/$customerId"
    }

    def changeStateEndpoint(){
        return "cm/v1/contactpoints/state"
    }

    static List<PhoneContactPointsDto> getResponseAndAssertOK(ResponseOptions webCall, HttpStatus expectedStatus) {
        assertStatus(webCall, expectedStatus).extract().as(List)
    }

    static Map getResponseAndAssertBadRequest(ResponseOptions webCall, HttpStatus expectedStatus) {
        assertStatus(webCall, expectedStatus).extract().as(Map)
    }

    def okPut(PhoneContactPointsDto input){
        getDataAndAssertOK(httpClient().body(input).put(changeStateEndpoint()), HttpStatus.OK)
    }

    def getDataAndAssertOK(ResponseOptions webClient, HttpStatus expectedStatus){
        getRootPayloadAssertOK(webClient, expectedStatus)
    }

    def getRootPayloadAssertOK(ResponseOptions webClient, HttpStatus expectedStatus){
        assertStatus(webClient, expectedStatus).extract().as(Map)
    }

    Map badRequest(body, HttpStatus statusCode, String method = 'put', String endpoint, Map headers = ['X-APPNAME': 'contact-points']){
        def client = httpClient()
        if (body){
            client = client.body(body)
        }
        client = client.headers(headers)."$method"(endpoint)
        client = assertStatus(client, statusCode)
        client.extract().as(Map)
    }

    List badRequestAsList(body, HttpStatus statusCode, String method = 'put', String endpoint, Map headers = ['X-APPNAME': 'contact-points']){
        def client = httpClient()
        if (body){
            client = client.body(body)
        }
        client = client.headers(headers)."$method"(endpoint)
        client = assertStatus(client, statusCode)
        client.extract().as(List)
    }

    Map badRequestDetails(entity, String endpoint, String method = 'put'){
        def badRequestResponse = badRequest(entity, HttpStatus.BAD_REQUEST, method, endpoint)
        System.out.println("bad response : " + badRequestResponse)
        def errors = badRequestResponse
        errors
    }

    List invalidRequestDetails(entity, String endpoint, String method = 'put'){
        def badRequestResponse = badRequestAsList(entity, HttpStatus.BAD_REQUEST, method, endpoint)
        System.out.println("bad response : " + badRequestResponse)
        def errors = badRequestResponse
        errors
    }

    static def assertStatus(ResponseOptions webClient, HttpStatus expectedStatus){
        def restCall = webClient.then()
        try{
            restCall.statusCode(expectedStatus.value())
        }catch (Throwable t){
            println(" Error while REST Call")
            println t.getMessage()
        }
    }
}
