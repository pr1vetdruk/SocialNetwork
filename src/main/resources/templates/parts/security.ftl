<#assign know = Session.SPRING_SECURITY_CONTEXT?? >

<#if know>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal,
    personalData = user.getPersonalData()
    >
</#if>