<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        <div class="text-box">
            <div class="header">Make Notice</div>
            <div class="body">
                <form method="post">
                    <input type="hidden" name="action" value="noticeForm"/>
                    <div class="field">
                        <div class="name">
                            <label for="noticeForm">Text</label>
                        </div>
                        <textarea id="noticeForm" name="noticeForm" type="text">${noticeForm.content!}</textarea>
                    </div>
                    <@c.error "noticeForm.content"/>
                    <div class="button-field">
                        <input type="submit" value="Enter">
                    </div>
                </form>
            </div>
        </div>
    <#else>
        <h1>404</h1>
        <p>Page not found</p>
    </#if>
</@c.page>