package blackbox.au.com.belong.customermaster.contactpoints.get

import au.com.belong.customermaster.contactpoints.model.PhoneContactPointStatus
import blackbox.FunctionalSpec
import blackbox.au.com.belong.customermaster.contactpoints.ContactPointsFixtures

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.OK

class GetContactPointsSpec extends FunctionalSpec implements ContactPointsFixtures{

    def 'should return all contact points by customer id'() {
        when:
        def response = getResponseAndAssertOK(httpClient().get(fetchContactPointsByCustomerEndpoint(customerId)), OK)

        then:
        System.out.println("Whats the response" + response)
        def responseAsMap = response.get(0)
        responseAsMap.status == status
        responseAsMap.contactNo == contactNo

        where:
        scenario | customerId | status | contactNo
        'valid input' | 'CM10000001' | PhoneContactPointStatus.NEW.toString() | 12345678
    }

    def 'should return all contact points'() {
        when:
        def response = getResponseAndAssertOK(httpClient().get(fetchAllContactPointsEndpoint()), OK)

        then:
        System.out.println("Whats the response" + response)
        def responseAsMap = response.get(0)
        responseAsMap.status == status
        responseAsMap.contactNo == contactNo

        where:
        scenario | customerId | status | contactNo
        'valid input' | 'CM10000001' | PhoneContactPointStatus.NEW.toString() | 12345678
    }

    def 'unknown customerId test'() {
        when:
        def response = getResponseAndAssertBadRequest(httpClient().get(fetchContactPointsByCustomerEndpoint(customerId)), BAD_REQUEST)
        def Map resultMap = response

        then:
        response instanceof Map
        System.out.println("Whats the response" + response)
        response.errorId == errorId

        where:
        scenario | customerId | status | contactNo | errorId
        'valid input' | 'CM11000002' | PhoneContactPointStatus.NEW.toString() | 12345678 | '400 BAD_REQUEST'
    }
}
