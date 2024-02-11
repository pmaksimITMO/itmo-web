<#import "commons.ftlh" as c>

<@c.page>
    <@c.post post=post/>
    <#if user??>
        <div class="form-comment">
            <div class="header">Write Comment</div>
            <div class="body">
                <form method="post" action="">
                    <input type="hidden" name="action" value="writeComment"/>
                    <div class="field">
                        <div class="name">
                            <label for="name">Text</label>
                        </div>
                        <div class="value">
                            <textarea id="text" name="text">${comment.text!}</textarea>
                        </div>
                        <@c.error "comment.text"/>
                    </div>
                    <div class="button-field">
                        <input type="submit" value="Write">
                    </div>
                </form>
            </div>
        </div>
    </#if>
    <#list post.comments as comment>
        <div class="comment">
            <div class="header">
                ${comment.user.login}
            </div>
            <div class="text">
                <#list comment.text?split("\r\n") as line>
                    <p>${line}</p>
                </#list>
            </div>
            <div class="footer">
                Created at ${comment.creationTime}
            </div>
        </div>
    </#list>
</@c.page>
