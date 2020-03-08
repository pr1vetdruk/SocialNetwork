<#import "../parts/common.ftl" as common>
<#import "../parts/personal-data.ftl" as personalData>
<#include "../parts/security.ftl">

<@common.page>
    <#include "../parts/main-menu.ftl"/>
    <div class="col-4 ml-5 mr-5 bg-white border rounded">
        <@personalData.filling false/>
    </div>
</@common.page>