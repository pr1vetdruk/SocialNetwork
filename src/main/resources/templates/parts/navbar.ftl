<#import "login.ftl" as login/>
<#include "security.ftl"/>

<nav class="navbar justify-content-center p-0 border" style="background: #4a76a8; border-bottom: 1px solid #4872a3;">
    <div class="row" style="width: 960px;">
        <div class="col pl-0">
            <a class="navbar-brand align-self-center" style=" color: #ffffff" href="/">
                SocialNetwork
            </a>
        </div>

        <div class="col d-flex justify-content-end pr-0">
            <#if authorizedUser??>
                <div class="dropdown logout">
                    <a class="dropdown-toggle " href="#" role="button" id="dropdownMenuLink"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                       style="color: #ffffff">
                        ${authorizedUser.personalData.firstName}
                        <img src="<#if authorizedUser.personalData.avatarFileName??>/img/${authorizedUser.personalData.avatarFileName}<#else>../../static/img/icon/camera_200.png</#if>"
                             class="card-img-top rounded-circle" style="width: 28px; height: 28px" alt="avatar">
                    </a>

                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <a class="dropdown-item" href="/id${pageOwner.id}">Моя страница</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/id${pageOwner.id}/edit/">Редактировать</a>
                        <a class="dropdown-item" href="#">Настройки</a>
                        <a class="dropdown-item" href="#">Помощь</a>
                        <div class="dropdown-divider"></div>
                        <form action="/logout" method="post">
                            <input type="submit" class="dropdown-item" value="Выход"/>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        </form>
                    </div>
                </div>
            </#if>
        </div>
    </div>
</nav>

