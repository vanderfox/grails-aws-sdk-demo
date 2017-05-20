package grails.aws.sdk.demo

import grails.plugin.awssdk.dynamodb.AbstractDBService
import grails.transaction.Transactional
import org.grails.quiz.QuizItem


class QuizItemDBService  extends AbstractDBService<QuizItem> {

    QuizItemDBService() {
        super(QuizItem)
    }
}
