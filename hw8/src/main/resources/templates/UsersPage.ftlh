<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        <div class="users datatable">
            <div class="caption">User</div>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Login</th>
                    <th>Created</th>
                </tr>
                </thead>
                <tbody>
                <#if users?size==0>
                    <tr class="noData">
                        <td colspan="3">
                            No data
                        </td>
                    </tr>
                <#else>
                    <#list users as u>
                        <tr>
                            <td class="id">${u.id}</td>
                            <td class="login">${u.login}</td>
                            <td class="creationTime">${u.creationTime}</td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    <#else>
        <h1>404</h1>
        <p>Page not found</p>
    </#if>
</@c.page>
