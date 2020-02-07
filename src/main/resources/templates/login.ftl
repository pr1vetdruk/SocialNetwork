<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>

<@common.page>
    <div class="row justify-content-center">
        <div class="col-4" style="background: #fff">
            <div class="row">
                <div class="col pt-3 text-center">
                    <h5>Вход в SocialNetwork</h5>
                </div>
            </div>
            <#if error??>
                <div class="row">
                    <div class="col pt-2  ">
                        <div class="alert alert-danger mb-0" role="alert">
                            ${error}
                        </div>
                    </div>
                </div>
            </#if>
            <div class="row">
                <div class="col p-4">
                    <@login.authorization/>
                </div>
            </div>
        </div>
    </div>

</@common.page>