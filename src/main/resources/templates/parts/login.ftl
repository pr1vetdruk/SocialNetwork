<#macro authorization>
        <form action="/login" method="post">
            <div class="form-group row">
                <div class="col">
                    <input type="text" name="username" placeholder="Логин"
                           class="form-control ${(loginError??)?string('is-invalid', '')}"/>
                    <#if loginError??>
                        <div class="invalid-feedback">
                            ${loginError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <div class="col">
                    <input type="password" name="password" placeholder="Пароль"
                           class="form-control ${(passwordError??)?string('is-invalid', '')}"/>
                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn" style="margin-right: 15px; width: 100px; background-color: #5181b8; color: #fff"
                    type="submit">Войти
            </button>
            <div class="forgot"><a href="">Забыли пароль?</a></div>
        </form>
</#macro>

<#macro logout>
    <div>
        <form action="/logout" method="post">
            <button class="btn btn-primary" type="submit"><#if user??>Sign Out<#else>Log in</#if></button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
</#macro>