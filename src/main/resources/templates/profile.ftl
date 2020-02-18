<#import "parts/common.ftl" as common>
<#include "parts/security.ftl">

<@common.page>
    <div class="row justify-content-center">
        <div class="col-2 pr-2 pl-2">
            <div class="row main-menu">
                <div class="col">
                    <a href="/id${pageOwner.id}">
                        <div class="row">
                            <div class="col-2 p-0">
                                <img src="../static/img/menu/main.png" width="15" height="15">
                            </div>
                            <div class="col p-0">
                                Моя страница
                            </div>
                        </div>
                    </a>
                </div>
            </div>

            <div class="row main-menu">
                <div class="col">
                    <a href="#">
                        <div class="row">
                            <div class="col-2 p-0">
                                <img src="../static/img/menu/news.png" width="15" height="15">
                            </div>
                            <div class="col p-0">
                                Новости
                            </div>
                        </div>
                    </a>
                </div>
            </div>

            <div class="row main-menu">
                <div class="col">
                    <a href="#">
                        <div class="row">
                            <div class="col-2 p-0">
                                <img src="../static/img/menu/messages.png" width="15" height="15">
                            </div>
                            <div class="col p-0">
                                Сообщения
                            </div>
                            <div class="col-2 p-0">
                                <span class="badge badge-primary badge-pill">6</span>
                            </div>
                        </div>
                    </a>
                </div>
            </div>

            <div class="row main-menu">
                <div class="col">
                    <a href="#">
                        <div class="row">
                            <div class="col-2 p-0">
                                <img src="../static/img/menu/contacts.png" width="15" height="15">
                            </div>
                            <div class="col p-0">
                                Друзья
                            </div>
                            <div class="col-2 p-0">
                                <span class="badge badge-primary badge-pill">1</span>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>

        <div class="col-3">
            <div class="card p-2">
                <img src="/img/photo_2019-11-06_16-22-22.jpg" class="card-img-top" alt="avatar">
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
                    <h5>${pageOwner.personalData.firstName} ${pageOwner.personalData.lastName}</h5>
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
                            <div class="row pt-3">
                                <div class="col-2">
                                    <img src="/img/photo_2019-11-06_16-22-22.jpg" class="card-img-top rounded-circle"
                                         alt="avatar">
                                </div>
                                <div class="col pl-0">
                                    <a href="/id${pageOwner.id}">
                                        ${pageOwner.personalData.firstName} ${pageOwner.personalData.lastName}
                                    </a>
                                    <p>${publication.dateCreation?date}</p>
                                </div>
                                <div class="col-1">
                                    <div class="dropdown">
                                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></a>

                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                            <a class="dropdown-item" href="/id${publication.author.id}/publications/?publication=${publication.id}">Удалить запись</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row pt-2">
                                <div class="col">
                                    ${publication.text}
                                </div>
                            </div>

                            <#if publication.fileName?has_content>
                                <div class="mb-1">
                                    <img src="/img/${publication.fileName}" class="card-img-top"/>
                                </div>
                            </#if>

                            <div class="dropdown-divider"></div>

                            <div class="row pb-2">
                                <div class="col-1">
                                    L
                                </div>
                                <div class="col-2">
                                    123
                                </div>
                                <div class="col">
                                    Repost
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@common.page>