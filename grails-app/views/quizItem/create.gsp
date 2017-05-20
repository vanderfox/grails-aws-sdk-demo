<%@ page import="org.grails.quiz.QuizItem" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'quizItem.label', default: 'QuizItem')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-quizItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-quizItem" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.quizItem}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.quizItem}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.quizItem}" action="save" method="POST">
                <fieldset class="form">


                   question: <input type="text" name="question" value=""/><br/>
                    answer: <input type="text" name="answer" value=""/><br/>
                    option1: <input type="text" name="option1" value=""/><br/>
                    option2: <input type="text" name="option2" value=""/><br/>
                    option3: <input type="text" name="option3" value=""/><br/>
                    option4: <input type="text" name="option4" value=""/><br/>

                   %{-- <f:display property="option1" bean="quizItem"/>--}%
                    %{--<f:all bean="quizItem"/>--}%
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
