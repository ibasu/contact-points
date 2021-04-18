package blackbox.au.com.belong.customermaster.contactpoints.post


import au.com.belong.customermaster.contactpoints.model.PhoneContactPointsDto
import blackbox.FunctionalSpec
import blackbox.au.com.belong.customermaster.contactpoints.ContactPointsFixtures

class PutContactPointsSpec extends FunctionalSpec implements ContactPointsFixtures{

    def 'test contact point activation' (){
        given:
        PhoneContactPointsDto input = phoneContactPointsDto([userId: userId, userNotes: userNotes])

        when:
        def result = okPut(input)
        def Map resultMap = result
        System.out.println("Result Map : " + resultMap )

        then:
        result instanceof Map

        where:
        scenario | userId | userNotes
        'valid input' | 'e10001' | 'Approved'
    }

    def "test un-regsistered customer error" (){
        given:
        PhoneContactPointsDto input = phoneContactPointsDto([customerId: customerId, contactNo: contactNo, userId: userId, userNotes: userNotes])

        when:
        def result = badRequestDetails(input, changeStateEndpoint())
        def Map errors = result
        System.out.println("Errors : " + errors )
        errors.size() == errorscount
        errors.errorId == errorId

        then:
        result instanceof Map

        where:
        scenario | customerId | contactNo | userId | userNotes | errorscount | errorId
        'invalid input 1' | 'CM10000002' | 12345678 | 'e10001' | 'Approved' | 5 | '400 BAD_REQUEST'
        'invalid input 2' | 'CM10000001' | 10345678 | 'e10001' | 'Approved' | 5 | '400 BAD_REQUEST'
    }

    def "test validation error" (){
        given:
        PhoneContactPointsDto input = phoneContactPointsDto([customerId: customerId, contactNo: contactNo, userId: userId, userNotes: userNotes])

        when:
        def result = invalidRequestDetails(input, changeStateEndpoint())
        def List errors = result
        def Map errorsMap = errors.get(0)
        errors.size() == errorscount
        errors.errorId == errorId

        then:
        result instanceof List

        where:
        scenario | customerId | contactNo | userId | userNotes | errorscount | errorId | fieldName
        'invalid input 1' | 'CM10000002' | 12345678333 | 'e10001' | 'Approved' | 1 | '400 BAD_REQUEST' | 'contactNo'
    }
}
