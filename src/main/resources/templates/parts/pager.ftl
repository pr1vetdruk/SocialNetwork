<#macro pager url page>
    <#if page.getTotalPages() gt 7>
        <#assign
            totalPages = page.getTotalPages()
            pageNumber = page.getNumber() + 1
            head= (pageNumber > 4)?then([1, -1], [1, 2, 3])
            tail = (pageNumber < totalPages - 3)?then([-1, totalPages], [totalPages - 2, totalPages - 1, totalPages])
            bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1)?then([pageNumber - 2, pageNumber - 1], [])
            bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3)?then([pageNumber + 1, pageNumber + 2], [])
            body = head + bodyBefore + (pageNumber > 3 && pageNumber < totalPages - 2)?then([pageNumber], []) + bodyAfter + tail
        >
    <#else>
        <#assign body = 1..page.getTotalPages()>
    </#if>

    <div class="col bg-white pt-3 border rounded">
        <div class="row">
            <div class="col">
                <ul class="pagination">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Страницы</a>
                    </li>
                    <#list body as number>
                        <#if (number - 1) == page.getNumber()>
                            <li class="page-item active">
                                <a class="page-link" href="#" tabindex="-1" style="background-color: #2a6996; border-color: #2a6592;">${number}</a>
                            </li>
                        <#elseif number == -1>
                            <li class="page-item disabled">
                                <a class="page-link" href="#" tabindex="-1">...</a>
                            </li>
                        <#else>
                            <li class="page-item">
                                <a class="page-link" href="${url}?page=${number - 1}&size=${page.getSize()}" tabindex="-1" style="color: #2a6592">${number}</a>
                            </li>
                        </#if>
                    </#list>
                </ul>
            </div>

            <div class="col">
                <ul class="pagination">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Элементов</a>
                    </li>
                    <#list [5, 10, 25, 50] as size>
                        <#if size == page.getSize()>
                            <li class="page-item active">
                                <a class="page-link" href="#" tabindex="-1" style="background-color: #2a6996; border-color: #2a6592;">${size}</a>
                            </li>
                        <#else>
                            <li class="page-item">
                                <a class="page-link" href="${url}?page=${page.getNumber()}&size=${size}" tabindex="-1" style="color: #2a6592">${size}</a>
                            </li>
                        </#if>
                    </#list>
                </ul>
            </div>
        </div>

    </div>
</#macro>