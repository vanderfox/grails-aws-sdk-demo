package org.grails

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.services.s3.model.ListObjectsRequest
import com.amazonaws.services.s3.model.ObjectListing
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.S3Object
import grails.plugin.awssdk.s3.AmazonS3Service

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class BucketObjectController {
  AmazonS3Service amazonS3Service

  def index = { redirect(action: "show" ) }

  def list = {
    params.max = Math.min(params.max ? params.int('max') : 5, 100)
    ObjectListing bucketObjects
    if (flash.bucketObjects && params.page == 'next') {
      bucketObjects = amazonS3Service.client.listNextBatchOfObjects(flash.bucketObjects)
      flash.page += 1
    } else {
      def lstObjReq = new ListObjectsRequest()
      lstObjReq.setBucketName(params.id)
      lstObjReq.setMaxKeys(params.max)
      bucketObjects = amazonS3Service.client.listObjects(lstObjReq)
      flash.page = bucketObjects?.isTruncated()?1:0
    }
    if (bucketObjects?.isTruncated() ) {
      flash.bucketObjects = bucketObjects
    } else {
      flash.bucketObjects = null
    }
    def bucketObjectsSummaries = bucketObjects?.objectSummaries
    def metadataList = []
    bucketObjectsSummaries.each {bos ->
      metadataList <<
              bucketObjects.metaPropertyValues
    }
    [bucketName:params.id, 
     bucketObjectsSummaries:bucketObjectsSummaries, 
     metadataList:metadataList]
  }

  def create = {
    [bucketName:params.id]
  }

  def save = {
    String newKey = amazonS3Service.storeMultipartFile(params.bucketName,params.key,params.file)
    redirect(action:"list", params: [id:params.bucketName, key:newKey])
  }


  def show = {
    params.max = params.max ? params.int('max') : 10000
    params.offset = params.offset ? params.int('offset'):0
    
    ObjectMetadata meta = amazonS3Service.client.getObjectMetadata(params.bucketName, params.key)
    GetObjectRequest getObjReq = new GetObjectRequest(params.bucketName, params.key)
    getObjReq.withRange(params.offset, params.max+params.offset)

    S3Object s3Object = amazonS3Service.client.getObject(getObjReq)
    [text:s3Object.objectContent.text,meta:meta.metadata, params:params]
  }
  def download = {  
    response.contentType = "application/octet-stream"
    response.outputStream <<
         amazonS3Service.client.getObject((String)params.bucketName, (String)params.key).objectContent
  }
  def edit = {render "edit not implemented"  }
  def update = {render "update not implented" }
  def delete = {
    amazonS3Service.deleteFile((String)params.bucketName, (String)params.key)
    flash.message = "bucket: $params.bucketName key: $params.key deleted."
    redirect(action: "list", id:params.bucketName)
  }
}
