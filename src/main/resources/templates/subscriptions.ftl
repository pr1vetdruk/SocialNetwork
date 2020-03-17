<#import "parts/common.ftl" as common>
<#include "parts/security.ftl">

<@common.page>
    <#include "parts/main-menu.ftl"/>
    <div class="col-4 ml-5 mr-5 bg-white border rounded">
        <div>${type}</div>
        <ul class="list-group">
            <#list users as user>
                <li class="list-group-item">
                    <a href="/id${user.id}">${user.personalData.firstName}</a>
                </li>
            </#list>
        </ul>
    </div>
</@common.page>