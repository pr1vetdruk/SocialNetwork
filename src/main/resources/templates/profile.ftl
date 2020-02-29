<#import "parts/common.ftl" as common>
<#import "parts/pager.ftl" as p>
<#include "parts/security.ftl">

<@common.page>
    <div class="row justify-content-center">
        <div class="col-2 pr-2 pl-2">
            <a class="row main-menu" href="/id${pageOwner.id}">
                <div class="col-2 p-0">
                    <img class="main-menu-img" src="../static/img/icon/home.png">
                </div>
                <div class="col main-menu-text">
                    Моя страница
                </div>
            </a>

            <a class="row main-menu" href="#">
                <div class="col-2 p-0">
                    <img class="main-menu-img" src="../static/img/icon/news.png">
                </div>
                <div class="col main-menu-text">
                    Новости
                </div>
            </a>

            <a class="row main-menu" href="#">
                <div class="col-2 p-0">
                    <img class="main-menu-img" src="../static/img/icon/messages.png">
                </div>
                <div class="col main-menu-text">
                    Сообщения
                </div>
                <div class="col-2 p-0">
                    <span class="badge badge-primary badge-pill">6</span>
                </div>
            </a>

            <a class="row main-menu" href="#">
                <div class="col-2 p-0">
                    <img class="main-menu-img" src="../static/img/icon/friends.png">
                </div>
                <div class="col main-menu-text">
                    Друзья
                </div>
                <div class="col-2 p-0">
                    <span class="badge badge-primary badge-pill">1</span>
                </div>
            </a>

            <a class="row main-menu" href="#">
                <div class="col-2 p-0">
                    <img class="main-menu-img" src="../static/img/icon/photo.png">
                </div>
                <div class="col main-menu-text">
                    Фотографии
                </div>
            </a>
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

            <div class="row">
                <form method="get" action="/id${pageOwner.id}" class="form-inline">
                    <input type="text" name="filter" value="${filter?ifExists}" placeholder="Search by tag"
                           class="form-control"/>
                    <button type="submit" class="btn btn-primary ml-2">Search</button>
                </form>
            </div>

            <div class="row">
                <@p.pager url pagePublications/>
            </div>

            <#list pagePublications.content as publication>
                <div class="row">
                    <div class="col bg-white mt-3 border rounded">
                        <div class="card border-0">
                            <div class="row pt-3">
                                <div class="col-2">
                                    <img src="/img/photo_2019-11-06_16-22-22.jpg" class="card-img-top rounded-circle" style="width: 60px"
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
                                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></a>

                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                            <a class="dropdown-item"
                                               href="/id${publication.author.id}/publications/?publication=${publication.id}">Удалить
                                                запись</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row pt-2">
                                <div class="col">
                                    ${publication.text}
                                </div>
                            </div>

                            <#if publication.fileName?hasContent>
                                <div class="mb-1">
                                    <img src="/img/${publication.fileName}" class="card-img-top" alt="mini-avatar"/>
                                </div>
                            </#if>

                            <div class="dropdown-divider"></div>

                            <div class="row pb-2">
                                <a class="col-2" href="/id${pageOwner.id}/publications/${publication.id}/like">
                                        <#if publication.getLikedAuthorizedUser()>
                                            <img class="main-menu-img" src="../static/img/icon/like.png"
                                                 alt="like"> ${publication.numberLikes}
                                        <#else>
                                            <img class="main-menu-img" src="../static/img/icon/none-like.png"
                                                 alt="none like"> ${publication.numberLikes}
                                        </#if>
                                </a>
                                <div class="col">
                                    <img class="main-menu-img" src="../static/img/icon/repost.png" alt="repost">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@common.page>