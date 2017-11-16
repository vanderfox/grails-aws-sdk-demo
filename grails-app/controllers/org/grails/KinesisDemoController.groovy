package org.grails

import grails.aws.sdk.demo.KinesisDemoService

/**
 * Created by rvanderwerf on 10/13/17.
 */
class KinesisDemoController {

    KinesisDemoService kinesisDemoService


    def startKinesisDemo() {
        Map kinesisParams = [groovy:"isFun", grails:"Rocks", awsWithGroovy:"awesome", g3summit:"hey there Austin!"]

        kinesisDemoService.sendToKinesis(kinesisParams)

    }
}
