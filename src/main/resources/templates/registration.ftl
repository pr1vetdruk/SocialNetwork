<#import "parts/common.ftl" as common>
<#import "parts/registration.ftl" as registration>
<#include "parts/security.ftl">

<@common.page>
    <div class="row justify-content-center">
        <div class="col-4 p-4 bg-white border rounded">
            <#if !nextRegistrationStep??>
                <@registration.personalDataFillingForm false/>
            <#else>
                <div class="row mb-2">
                    <div class="col text-center">
                        <h5>Регистрация</h5>
                        <p>Данные авторизации</p>
                    </div>
                </div>

                <form action="/registration" method="post">
                    <div class="form-group row">
                        <div class="col">
                            <input type="text" name="login" placeholder="Логин" autocomplete="off"
                                   class="form-control ${(loginError??)?string('is-invalid', '')}"
                                   value="<#if userDto?? && userDto.login??>${userDto.login}</#if>"/>
                            <#if loginError??>
                                <div class="invalid-feedback">
                                    ${loginError}
                                </div>
                            </#if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col">
                            <input type="password" name="password" placeholder="Пароль" autocomplete="off"
                                   class="form-control ${(passwordError??)?string('is-invalid', '')}"/>
                            <#if passwordError??>
                                <div class="invalid-feedback">
                                    ${passwordError}
                                </div>
                            </#if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col">
                            <input type="password" name="passwordConfirmation" placeholder="Повторите пароль"
                                   autocomplete="off"
                                   class="form-control ${(passwordConfirmationError??)?string('is-invalid', '')}"/>
                            <#if passwordConfirmationError??>
                                <div class="invalid-feedback">
                                    ${passwordConfirmationError}
                                </div>
                            </#if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col">
                            <input type="email" name="email" placeholder="E-mail" autocomplete="off"
                                   class="form-control ${(emailError??)?string('is-invalid', '')}"
                                   value="<#if userDto?? && userDto.email??>${userDto.email}</#if>"/>
                            <#if emailError??>
                                <div class="invalid-feedback">
                                    ${emailError}
                                </div>
                            </#if>
                        </div>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn" style="width: 100%; background-color: #60b859; color: #fff" type="submit"
                            name="registration">
                        Зарегистрироваться
                    </button>
                </form>
            </#if>
        </div>
    </div>
</@common.page>
