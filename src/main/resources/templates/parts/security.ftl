<#assign know = Session.SPRING_SECURITY_CONTEXT?? >

<#if know>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    login = user.getLogin()
    isAdmin = user.isAdmin()
    userId = user.getId()
    >
<#else>
    <#assign
    login = "Guest"
    isAdmin = false
    userId = -1
    >
</#if>