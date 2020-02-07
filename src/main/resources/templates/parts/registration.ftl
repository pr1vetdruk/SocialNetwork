
<#macro personalDataFillingForm isIndex>
    <!-- <div class="container border <#if isIndex>mr-3</#if>" style="width: 300px; padding: 25px; background: #fff;"> -->

        <div class="row mb-2">
            <div class="col text-center">
                <h5>Впервые в SocialNetwork?</h5>
                <p>Моментальная регистрация</p>
            </div>
        </div>

        <form action="/registration" method="post">
            <div class="form-group row">
                <div class="col">
                    <input type="text" name="firstName" placeholder="Ваше имя"
                           value="<#if userPersonalDataDto?? && userPersonalDataDto.firstName??>${userPersonalDataDto.firstName}</#if>"
                           class="form-control ${(firstNameError??)?string('is-invalid', '')}"/>
                    <#if firstNameError??>
                        <div class="invalid-feedback">
                            ${firstNameError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <div class="col">
                    <input type="text" name="lastName" placeholder="Ваша фамилия"
                           value="<#if userPersonalDataDto?? && userPersonalDataDto.lastName??>${userPersonalDataDto.lastName}</#if>"
                           class="form-control ${(lastNameError??)?string('is-invalid', '')}"/>
                    <#if lastNameError??>
                        <div class="invalid-feedback">
                            ${lastNameError}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="form-group row">
                <div class="col">
                    <select class="custom-select ${(cityIdError??)?string('is-invalid', '')}" name="cityId" >
                        <#if selectedCity??>
                            <option value="${selectedCity.id}" selected>${selectedCity.name}</option>
                        <#else>
                            <option value="" selected>Город</option>
                        </#if>
                        <#if citiesList??>
                            <#list citiesList as city>
                                <option value="${city.id}">${city.name}</option>
                            </#list>
                        </#if>
                    </select>
                    <#if cityIdError??>
                        <div class="invalid-feedback">
                            ${cityIdError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <div class="col">
                    <label for="dateBirth" class="mb-1" style="font-size: 14px; color: #626d7a; line-height: 20px;">Дата
                        рождения</label>
                    <input type="date" name="dateBirth" id="dateBirth" placeholder="Дата рождения"
                           value="<#if userPersonalDataDto?? && userPersonalDataDto.dateBirth??>${userPersonalDataDto.dateBirth?string("yyyy-MM-dd")}</#if>"
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

</#macro>


