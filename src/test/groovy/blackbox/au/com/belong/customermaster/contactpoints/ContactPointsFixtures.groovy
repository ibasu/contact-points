package blackbox.au.com.belong.customermaster.contactpoints

import au.com.belong.customermaster.contactpoints.model.PhoneContactPointsDto

trait ContactPointsFixtures {

    PhoneContactPointsDto phoneContactPointsDto(Map overrides = [:]){
        def options = [:] << phoneContactPointsDtoDefaults()
        options << overrides
        new PhoneContactPointsDto(options)
    }

    def phoneContactPointsDtoDefaults() {
        [
                "id": "abcdefgh",
                "customerId": "CM10000001",
                "contactNoCode": "04",
                "contactNo": 12345678,
                "status": "ACTIVE",
                "userNotes": "Activating contactNo"
        ]
    }
}
