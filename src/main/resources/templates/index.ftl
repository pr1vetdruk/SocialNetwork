<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>
<#import "parts/registration.ftl" as registration>

<@common.page>
    <div class="row justify-content-center mt-5">
        <div class="col-8">
            <div>
                <h2>Social network</h2>
            </div>
            <div>
                <p>Социальная сеть на коленке.</p>
                <img src="../static/img/phone.jpg"/>
            </div>
        </div>
        <div class="col-4">
            <div class="row">
                <div class="col p-4 bg-white border rounded">
                    <@login.authorization/>
                </div>
            </div>

            <div class="row">
                <div class="col mt-4 p-4 bg-white border rounded">
                    <@registration.personalDataFillingForm true/>
                </div>
            </div>
        </div>
    </div>
</@common.page>