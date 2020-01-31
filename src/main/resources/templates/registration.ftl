<#import "parts/common.ftl" as common>
<#import "parts/registration.ftl" as registration>

<#include "parts/security.ftl">

<@common.page>


    <#if !nextRegistrationStep??>
        <@registration.personalDataFillingForm false/>
    <#else>
        <div class="container mx-auto border" style="width: 300px; padding: 25px; background: #fff;">
            <div class="row mb-2">
                <div class="col text-center">
                    <h5>Учетные данные</h5>
                    <p>Заполните все поля</p>

                </div>
            </div>

            <form action="/registration" method="post">
                <div class="form-group row">
                    <div class="col-sm">
                        <input type="text" name="login" placeholder="Логин" autocomplete="off" required
                               class="form-control ${(loginError??)?string('is-invalid', '')}"
                               value="<#if user?? && user.login??>${user.login}</#if>"/>
                        <#if loginError??>
                            <div class="invalid-feedback">
                                ${loginError}
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm">
                        <input type="password" name="password" placeholder="Пароль" autocomplete="off" required
                               class="form-control ${(passwordError??)?string('is-invalid', '')}"/>
                        <#if passwordError??>
                            <div class="invalid-feedback">
                                ${passwordError}
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm">
                        <input type="password" name="passwordConfirmation" placeholder="Повторите пароль"
                               autocomplete="off" required
                               class="form-control ${(passwordConfirmationError??)?string('is-invalid', '')}"/>
                        <#if passwordConfirmation??>
                            <div class="invalid-feedback">
                                ${passwordConfirmationError}
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm">
                        <input type="email" name="email" placeholder="E-mail" autocomplete="off" required
                               class="form-control ${(emailError??)?string('is-invalid', '')}"
                               value="<#if user?? && user.email??>${user.email}</#if>"/>
                        <#if emailError??>
                            <div class="invalid-feedback">
                                ${emailError}
                            </div>
                        </#if>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn" style="background-color: #5181b8; color: #fff" type="submit" name="registration">
                    Зарегистрироваться
                </button>
            </form>
        </div>
    </#if>
</@common.page>
