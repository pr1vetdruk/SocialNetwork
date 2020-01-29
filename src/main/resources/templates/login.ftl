<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>

<@common.page>
    <#if error??>
        <div class="alert alert-danger" role="alert">
            ${error}
        </div>
    </#if>

    <@login.authorization/>
</@common.page>