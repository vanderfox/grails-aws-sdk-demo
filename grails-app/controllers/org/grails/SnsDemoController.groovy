package org.grails

import grails.aws.sdk.demo.SnsDemoService

class SnsDemoController {

    SnsDemoService snsDemoService

    def index() { }

    def startSnsDemo () {

        snsDemoService.sendSnsTopic("arn:aws:sns:us-east-1:057654311259:G3Summit","G3Summit Notification", "Hello Austin!!")

    }

}
