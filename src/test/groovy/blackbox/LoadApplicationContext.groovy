package blackbox

import au.com.belong.customermaster.contactpoints.api.controller.v1.ContactPointsController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadApplicationContext extends Specification{

    @Autowired
    private ContactPointsController contactPointsController

    def 'Load Application Context Test'(){
        expect: 'The ContactPointsController Created'
        contactPointsController
    }
}
