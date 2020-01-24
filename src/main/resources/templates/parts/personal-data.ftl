<div class="row mb-2">
    <div class="col text-center">
        <h5>Впервые в SocialNetwork?</h5>
        <p>Моментальная регистрация</p>
    </div>
</div>

<form action="/registration" method="post">
    <div class="form-group row">
        <div class="col-sm">
            <input type="text" name="firstName" placeholder="Ваше имя" value="<#if user??>${user.firstName}</#if>"
                   class="form-control ${(firstNameError??)?string('is-invalid', '')}"/>
            <#if firstNameError??>
                <div class="invalid-feedback">
                    ${firstNameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm">
            <input type="text" name="lastName" placeholder="Ваша фамилия" value="<#if user??>${user.lastName}</#if>"
                   class="form-control ${(lastNameError??)?string('is-invalid', '')}"/>
            <#if lastNameError??>
                <div class="invalid-feedback">
                    ${lastNameError}
                </div>
            </#if>
        </div>
    </div>

    <div class="form-group row">
        <div class="col-sm">
            <select class="custom-select ${(dateBirthError??)?string('is-invalid', '')}" name="city">
                <option value="" selected>Город</option>
                <#if citiesList??>
                    <#list citiesList as city>
                        <option value="${city.id}">${city.name}</option>
                    </#list>
                </#if>
            </select>
            <#if cityError??>
                <div class="invalid-feedback">
                    ${cityError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm">

            <label for="dateBirth" class="mb-1" style="font-size: 14px; color: #626d7a; line-height: 20px;">Дата рождения</label>
            <input type="date" class="form-control ${(dateBirthError??)?string('is-invalid', '')}"
                   name="dateBirth"
                   id="dateBirth"
                   value="<#if user??>${user.dateBirth?string("yyyy-MM-dd")}</#if>"
                   placeholder="Дата рождения"/>
            <p><#if user??>${user.dateBirth?string("dd.MM.yyyy")}</#if></p>
            <#if dateBirthError??>
                <div class="invalid-feedback">
                    ${dateBirthError}
                </div>
            </#if>
        </div>
    </div>

    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button class="btn" style="background-color: #5181b8; color: #fff" type="submit" name="continue">Продолжить регистрацию</button>
</form>

