package org.grails

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import grails.plugin.awssdk.s3.AmazonS3Service

class BucketController {

  AmazonS3Service amazonS3Service


  def index() { redirect(action: "list" ) }


  def list()  {
	try {
	 [buckets:amazonS3Service.listBucketNames()]
	} catch ( Exception e) { flash.message = e }
  }


  def create = { } // show GSP
  def save() {
    if (!amazonS3Service.exists((String)params.bucketName,"")) {
      amazonS3Service.createBucket((String)params.bucketName);
      flash.message = "bucket ${params.bucketName} created"
    } else {
      flash.message = "bucket ${params.bucketName} already exists"
    }
    redirect(action: "list")
  }
  def show() {
    redirect(controller:'bucketController', action: "list") 
  }
  def edit() {render "edit implemented"  }
  def update() {render "update not implemented" }
  def delete() {
    try {
      amazonS3Service.deleteBucket((String)params.bucketName)
        flash.message = "Bucket ${params.bucketName} deleted"
    } catch (Exception e) {flash.message = e }
    redirect(action: "list")
  }
}
