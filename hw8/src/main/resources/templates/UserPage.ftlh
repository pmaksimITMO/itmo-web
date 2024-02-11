<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        <section class = "user_main">
            <div>
                <div class="user">${user.login}</div>
                <ul>
                    <li>id: ${user.id}</li>
                    <li>creation time: ${user.creationTime}</li>
                </ul>
            </div>
            <img class="user_pic" src="<@spring.url '/img/logo.png'/>" alt="user_pic">
        </section>
    <#else>
        No such user
    </#if>
</@c.page>
