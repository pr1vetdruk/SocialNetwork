<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>
<#import "parts/registration.ftl" as registration>

<@common.page>
    <div class="row justify-content-center">
        <div class="col-8 border">
            <div>
                <h2>Social network</h2>
            </div>
            <div style="font-size: 14px; color: #626d7a; line-height: 20px;">
                <p>Социальная сеть на коленке.</p>
                <img src="../static/img/phone.jpg"/>
            </div>
        </div>
        <div class="col-4 border">
            <div class="row">
                <div class="col p-4 border" style="background: #fff">
                    <@login.authorization/>
                </div>
            </div>

            <div class="row">
                <div class="col mt-4 p-4 border" style="background: #fff">
                    <@registration.personalDataFillingForm true/>
                </div>
            </div>
        </div>
    </div>
</@common.page>