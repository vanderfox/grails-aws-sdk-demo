package grails.aws.sdk.demo

import com.amazonaws.services.sns.model.PublishResult
import grails.plugin.awssdk.sns.AmazonSNSService
import grails.transaction.Transactional

@Transactional
class SnsDemoService {

    AmazonSNSService amazonSNSService

    boolean sendSnsTopic(String arn, String subject, String message) {

        PublishResult result = amazonSNSService.publishTopic(
                arn,
                subject,
                message)

        if (result.messageId) {
            true
        } else {
            false
        }

    }


}
