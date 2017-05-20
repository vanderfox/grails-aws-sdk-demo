package org.grails

import com.amazonaws.services.dynamodbv2.model.ComparisonOperator
import grails.aws.sdk.demo.QuizItemDBService
import org.grails.quiz.QuizItem

class QuizItemController {

    QuizItemDBService quizItemDBService

    def index() {
        quizItemDBService.createTable() // does nothing if it already exists
        def results = quizItemDBService.query(QuizItem.DEFAULT_HASH_KEY).results
        [quizItemList: results, quizItemCount: results.size()]
    }

    def list() {
        quizItemDBService.createTable() // does nothing if it already exists
        def results = quizItemDBService.query(QuizItem.DEFAULT_HASH_KEY).results
        render(view:"index", model: [quizItemList: results, quizItemCount: results.size()])
    }

    def show() {
        [quizItem:quizItemDBService.query(QuizItem.DEFAULT_HASH_KEY,"id",params.id,ComparisonOperator.EQ).results[0]]
    }
    def edit() {
        [quizItem:quizItemDBService.query(QuizItem.DEFAULT_HASH_KEY,"id",params.id,ComparisonOperator.EQ).results[0]]
    }

    def create() {



    }


    def save(QuizItem quizItem) {
        quizItem.questionId = QuizItem.DEFAULT_HASH_KEY
        quizItemDBService.save(quizItem)
        flash.message = "Quiz item id ${quizItem.id} saved."
        render(view:"show", model: [quizItem:quizItem])
    }

    def delete() {
        quizItemDBService.delete(QuizItem.DEFAULT_HASH_KEY,params.id)
        flash.message "Item ${params.id} deleted"
        list()
    }

    def update(QuizItem quizItem) {
        QuizItem item = quizItemDBService.query(QuizItem.DEFAULT_HASH_KEY,"id",params.id,ComparisonOperator.EQ).results[0]
        item.question = quizItem.question
        item.answer = quizItem.answer
        item.option1 = quizItem.option1
        item.option2 = quizItem.option2
        item.option3 = quizItem.option3
        item.option4 = quizItem.option4
        quizItemDBService.save(item)
        render(view:"show", model: [quizItem:item])

    }


}
