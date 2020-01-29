<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>
<#import "parts/registration.ftl" as registration>

<@common.page>
    <div class="row row-cols-2" style="width: 960px;">
        <div class="col-md-8" style="text-align: center;">
            <div>
                <h2>Social network</h2>
            </div>
            <div style="font-size: 14px; color: #626d7a; line-height: 20px;">
                Социальная сеть на коленке.
            </div>
        </div>
        <@login.authorization/>
        <@registration.personalDataFillingForm true/>
    </div>
</@common.page>