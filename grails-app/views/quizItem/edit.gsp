<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'quizItem.label', default: 'QuizItem')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-quizItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-quizItem" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
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
            <g:form id="${quizItem.id}" action="update" method="PUT">

                <fieldset class="form">

                    id : ${quizItem.id}<br/>
                    question: <input type="text" name="question" value="${quizItem.question}"/><br/>
                    answer: <input type="text" name="answer" value="${quizItem.answer}"/><br/>
                    option1: <input type="text" name="option1" value="${quizItem.option1}"/><br/>
                    option2: <input type="text" name="option2" value="${quizItem.option2}"/><br/>
                    option3: <input type="text" name="option3" value="${quizItem.option3}"/><br/>
                    option4: <input type="text" name="option4" value="${quizItem.option4}"/><br/>

                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
