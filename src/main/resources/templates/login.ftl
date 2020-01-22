<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>

<@common.page>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION}
        </div>
    </#if>

    <@login.authorization/>
</@common.page>