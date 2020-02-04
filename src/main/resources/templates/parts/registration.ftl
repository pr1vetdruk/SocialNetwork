<#macro personalDataFillingForm isIndex>
    <div class="container border <#if isIndex>mr-3</#if>" style="width: 300px; padding: 25px; background: #fff;">
        <div class="row mb-2">
            <div class="col text-center">
                <h5>Впервые в SocialNetwork?</h5>
                <p>Моментальная регистрация</p>
            </div>
        </div>

        <form action="/registration" method="post">
            <div class="form-group row">
                <div class="col-sm">
                    <input type="text" name="firstName" placeholder="Ваше имя"
                           value="<#if personalData??>${personalData.firstName}</#if>"
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
                    <input type="text" name="lastName" placeholder="Ваша фамилия"
                           value="<#if user??>${user.lastName}</#if>"
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
                    <select class="custom-select ${(cityError??)?string('is-invalid', '')}" name="cityId" >
                        <#if personalData?? && personalData.city??>
                            <option value="${city.id}" selected>${city.name}</option>
                        <#else>
                            <option value="" selected>Город</option>
                        </#if>
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
                    <label for="dateBirth" class="mb-1" style="font-size: 14px; color: #626d7a; line-height: 20px;">Дата
                        рождения</label>
                    <input type="date" name="dateBirth" id="dateBirth" placeholder="Дата рождения"
                           value="<#if user?? && user.dateBirth??>${user.dateBirth?string("yyyy-MM-dd")}</#if>"
                           class="form-control ${(dateBirthError??)?string('is-invalid', '')}"/>
                    <#if dateBirthError??>
                        <div class="invalid-feedback">
                            ${dateBirthError}
                        </div>
                    </#if>
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn" style="background-color: #37b83a; color: #fff" type="submit" name="continue">Продолжить
                регистрацию
            </button>
        </form>
    </div>
</#macro>


