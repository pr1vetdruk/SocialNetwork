<#macro filling isIndex>
    <#if isIndex>
        <div class="row mb-2">
            <div class="col text-center">
                <h5>Впервые в SocialNetwork?</h5>
                <p>Моментальная регистрация</p>
            </div>
        </div>
    <#else>
        <div class="row mb-3 bg-light border-bottom">
            <div class="col pl-3 pt-3 pb-2">
                <h6>Основное</h6>
            </div>
        </div>
    </#if>
    <form action="<#if isIndex>/registration<#else>/id${userPersonalDataDto.user.id}/edit/save/</#if>" method="post" class="mb-3"
          enctype="multipart/form-data">
        <#if !isIndex>
            <div class="form-group row">
                <div class="col d-flex justify-content-center">
                    <div class="row align-center" style="max-width: 200px">
                        <img class="col " id="previewImg" alt="avatar"
                             src=<#if userPersonalDataDto?? && userPersonalDataDto.avatarFileName??>"/img/${userPersonalDataDto.avatarFileName}"
                        <#else>"../../static/img/profile/none_avatar.png"</#if>/>
                    </div>
                </div>
            </div>

            <div class="form-group row">
                <div class="col">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" name="avatarFileName" id="avatarFileName"
                               aria-describedby="image"
                               onchange="this.value">
                        <label class="custom-file-label" for="avatarFileName">Ваша фотография</label>
                    </div>

                    <script>
                        if (window.File && window.FileReader && window.FileList && window.Blob) {
                            document.querySelector('.custom-file-input').addEventListener('change', function (e) {
                                var
                                    f = e.target.files[0],
                                    reader = new FileReader,
                                    place = document.getElementById("previewImg")
                                ;
                                reader.readAsDataURL(f);
                                reader.onload = function (e) {
                                    place.src = e.target.result;
                                }
                                var fileName = document.getElementById("avatarFileName").files[0].name;
                                var nextSibling = e.target.nextElementSibling;
                                nextSibling.innerText = fileName;
                            });
                        } else {
                            console.warn("Ваш браузер не поддерживает FileAPI")
                        }
                        ;
                    </script>
                </div>
            </div>
        </#if>

        <div class="form-group row">
            <div class="col">
                <input type="text" name="firstName" placeholder="Ваше имя" autocomplete="off"
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
                <input type="text" name="lastName" placeholder="Ваша фамилия" autocomplete="off"
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
                <select class="custom-select ${(cityIdError??)?string('is-invalid', '')}" name="cityId">
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
                       value="<#if userPersonalDataDto?? && userPersonalDataDto.dateBirth??>${userPersonalDataDto.dateBirth}</#if>"
                       class="form-control ${(dateBirthError??)?string('is-invalid', '')}"/>
                <#if dateBirthError??>
                    <div class="invalid-feedback">
                        ${dateBirthError}
                    </div>
                </#if>
            </div>
        </div>


        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn" style="width: 100%; background-color: #37b83a; color: #fff" type="submit" name="continue">
            <#if isIndex>Продолжить регистрацию<#else>Сохранить изменения</#if>
        </button>
    </form>
</#macro>


