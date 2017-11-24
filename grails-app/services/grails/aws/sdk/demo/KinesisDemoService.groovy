package grails.aws.sdk.demo

import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry
import com.amazonaws.services.kinesis.model.PutRecordsResult
import grails.plugin.awssdk.kinesis.AmazonKinesisService
import groovy.json.JsonOutput

import java.nio.ByteBuffer

/**
 * Created by rvanderwerf on 10/13/17.
 */
class KinesisDemoService {

    AmazonKinesisService amazonKinesisService

    boolean sendToKinesis(Map params) {
        def jsonData = JsonOutput.toJson(params)
        ByteBuffer dataBuffer = ByteBuffer.wrap(jsonData.bytes)
        PutRecordsRequestEntry recordRequest = new PutRecordsRequestEntry(data: dataBuffer,
                partitionKey: "partitionKey-${System.currentTimeMillis()}".toString())
        PutRecordsResult resultRecords = amazonKinesisService.putRecords("g3summit", [recordRequest])
        if (resultRecords) {
            if (log.isDebugEnabled()) {
                log.debug("failed kinesis record count:${resultRecords.failedRecordCount}")
                resultRecords.records.each { putResultRecord ->
                    log.debug("kinesis record request: ${putResultRecord.toString()}")
                }
            }
            if (resultRecords.failedRecordCount > 0) {
                log.error("Error sending message to Kinesis failed records:${resultRecords.failedRecordCount} ${resultRecords?.records?.get(0)?.errorMessage}")
                return false
            }
            return true
        }
        false
    }


}
