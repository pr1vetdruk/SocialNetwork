<#assign know = Session.SPRING_SECURITY_CONTEXT?? >

<#if know>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    login = user.getLogin()
    isAdmin = user.isAdmin()
    currentUserId = user.getId()
    >
<#else>
    <#assign
    name = "Guest"
    isAdmin = false
    currentUserId = -1
    >
</#if>