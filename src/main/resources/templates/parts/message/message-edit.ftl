

<form method="post" enctype="multipart/form-data">
    <div class="form-group mt-3">
        <input type="text" name="text" placeholder="Что у вас нового?"
               autocomplete="off" role="button" data-toggle="collapse" aria-expanded="false"
               aria-controls="collapseExample" href="#addNewMessage"
               class="form-control form-control-sm border-0 ${(textError??)?string('is-invalid', '')}"/>
        <#if textError??>
            <div class="invalid-feedback">
                ${textError}
            </div>
        </#if>
    </div>
    <div class="collapse <#if message??>show</#if>" id="addNewMessage">
        <div class="form-group">
            <input type="text" name="tag" placeholder="Тэг"
                   class="form-control form-control-sm ${(tagError??)?string('is-invalid', '')}"
                   value="<#if message??>${message.tag}</#if>"/>
            <#if tagError??>
                <div class="invalid-feedback">
                    ${tagError}
                </div>
            </#if>
        </div>
        <div class="form-row">
            <div class="col-8">
                <div class="custom-file">
                    <input type="file" class="custom-file-input" id="myInput" aria-describedby="myInput">
                    <label class="custom-file-label" for="myInput">Загрузить изображение</label>
                </div>

                <script>
                    document.querySelector('.custom-file-input').addEventListener('change',function(e){
                        var fileName = document.getElementById("myInput").files[0].name;
                        var nextSibling = e.target.nextElementSibling
                        nextSibling.innerText = fileName
                    })
                </script>
            </div>

            <div class="col">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="<#if message??>${message.id}</#if>"/>
                <div class="form-group">
                    <button type="submit" class="btn" style="width: 100%">Опубликовать</button>
                </div>
            </div>
        </div>

    </div>
</form>
