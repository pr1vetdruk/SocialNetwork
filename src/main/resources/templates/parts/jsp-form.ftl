<#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />

<#macro formErrors>
    <#assign formErrors><@form.errors path="firstName" /></#assign>
    <#if formErrors?has_content>
        <div id="errors">
            <@spring.message "admin.error.globalMessage" />
        </div>
    </#if>
</#macro>