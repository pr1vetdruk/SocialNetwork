<#import "parts/common.ftl" as common>
<#include "parts/security.ftl">

<@common.page>
    <div class="row justify-content-center">
        <div class="col-2">
            <ul class="nav flex-column" style="font-size: 14px">
                <li class="nav-item">
                    <a class="nav-link p-1" href="/id${user.id}">Моя страница</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link p-1" href="#">Новости</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link p-1" href="#">Сообщения</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link p-1" href="#">Друзья</a>
                </li>
            </ul>
        </div>
        <div class="col-3">
            <div class="card p-2">
                <img src="../static/img/avatar/photo_2019-11-06_16-22-22.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <div class="row">
                        <div class="col-9 p-0 mr-2">
                            <a href="#" class="btn pt-1" style="width: 100%; font-size: 12.5px; height: 30px; background-color: #e5ebf1; color: #55677d;">Редактировать</a>
                        </div>
                        <div class="col-2 p-0">
                            <a href="#" class="btn pt-0" style="height: 30px; background-color: #e5ebf1; color: #55677d;">...</a>
                        </div>
                    </div>
                </div>
            </div>


        </div>
        <div class="col-7">
            <div class="row">
                <div class="col bg-white border rounded">
                    <h5>${personalData.lastName} ${personalData.firstName}</h5>
                    <p>123</p>
                    <p>123</p>
                    <p>123</p>
                    <p>123</p>
                </div>
            </div>

            <div class="row">
                <div class="col bg-white mt-3 border rounded">
                    <#include "parts/message/message-edit.ftl"/>
                </div>
            </div>


        </div>
    </div>
</@common.page>