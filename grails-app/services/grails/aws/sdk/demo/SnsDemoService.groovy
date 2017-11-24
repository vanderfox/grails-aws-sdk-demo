package grails.aws.sdk.demo

import com.amazonaws.services.sns.model.PublishResult
import grails.plugin.awssdk.sns.AmazonSNSService
import grails.transaction.Transactional

@Transactional
class SnsDemoService {

    AmazonSNSService amazonSNSService

    String sendSnsTopic(String arn, String subject, String message) {

          amazonSNSService.publishTopic(
                arn,
                subject,
                message)


    }


}
