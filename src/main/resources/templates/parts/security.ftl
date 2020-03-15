<#assign know = Session.SPRING_SECURITY_CONTEXT?? >

<#if know>
    <#assign
    authorizedUser = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    >
</#if>