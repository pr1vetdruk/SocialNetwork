<#import "parts/common.ftl" as common>
<#include "parts/security.ftl">

<@common.page>
    <div class="row justify-content-center">
        <div class="col-2 m-0 p-0">
            <ul class="nav flex-column m-0 p-0" style="font-size: 14px">
                <li class="nav-item">
                    <a class="nav-link p-1" href="/id${pageOwner.id}">
                        <span class="float-left mr-1"><img src="../static/img/menu/main.png" width="20" height="20"></span>
                        <span>Моя страница</span>
                        <span class="badge badge-primary badge-pill float-right"></span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link p-1" href="#">
                        <span class="float-left mr-1"><img src="../static/img/menu/news.png" width="20" height="20"></span>
                        <span>Новости</span>
                        <span class="badge badge-primary badge-pill float-right"></span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link  p-1" href="#">
                        <span class=" mr-1"><img src="../static/img/menu/messages.png" width="20" height="20"></span>
                        <span class="">Сообщения</span>
                        <span class="badge badge-primary badge-pill float-right">6</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link p-1" href="#">
                        <span class="float-left mr-1"><img src="../static/img/menu/contacts.png" width="20" height="20"></span>
                        <span>Друзья</span>
                        <span class="badge badge-primary badge-pill float-right">1</span>
                    </a>
                </li>
            </ul>
        </div>
        <div class="col-3 border">
            <div class="card p-2">
                <img src="/img/photo_2019-11-06_16-22-22.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <div class="row">
                        <div class="col-9 p-0 mr-2">
                            <a href="#" class="btn pt-1"
                               style="width: 100%; font-size: 12.5px; height: 30px; background-color: #e5ebf1; color: #55677d;">Редактировать</a>
                        </div>
                        <div class="col-2 p-0">
                            <a href="#" class="btn pt-0"
                               style="height: 30px; background-color: #e5ebf1; color: #55677d;">...</a>
                        </div>
                    </div>
                </div>
            </div>


        </div>
        <div class="col">
            <div class="row">
                <div class="col p-3 bg-white border rounded">
                    <h5>${pageOwner.personalData.lastName} ${pageOwner.personalData.firstName}</h5>
                    <div class="dropdown-divider"></div>
                    <p>123</p>
                    <p>123</p>
                    <p>123</p>
                    <p>123</p>
                </div>
            </div>

            <#if authorizedUser.id == pageOwner.id>
                <div class="row">
                    <div class="col bg-white mt-3 border rounded">
                        <#include "parts/message/message-edit.ftl"/>
                    </div>
                </div>
            </#if>

            <#list publications as publication>
                <div class="row">
                    <div class="col bg-white mt-3 border rounded">
                        <div class="card border-0">
                            <div class="m-2">
                                ${publication.text}
                            </div>

                            <#if publication.fileName?has_content>
                                <div class="mb-3">
                                    <img src="/img/${publication.fileName}" class="card-img-top"/>
                                </div>
                            </#if>

                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@common.page>