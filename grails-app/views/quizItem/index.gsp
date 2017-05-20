<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'quizItem.label', default: 'QuizItem')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-quizItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-quizItem" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
        <table><tr>
            <th>id</th>
            <th>question</th>
            <th>answer</th>
            <th>option1</th>
            <th>option2</th>
            <th>option3</th>
            <th>option4</th></tr>
            <g:each in="${quizItemList}" var="quizItem">
                <tr><td><a href="/quizItem/show/${quizItem.id}">${quizItem.id}</a></td>
                <td>${quizItem.question}</td>
                <td>${quizItem.answer}</td>
                <td>${quizItem.option1}</td>
                <td>${quizItem.option2}</td>
                <td>${quizItem.option3}</td>
                <td>${quizItem.option4}</td></tr>
            </g:each>
        </table>


            <div class="pagination">
                <g:paginate total="${quizItemCount ?: 0}" />
            </div>
        </div>
    </body>
</html>