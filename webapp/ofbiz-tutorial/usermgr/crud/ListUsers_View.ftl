<div class="container mt-4">
    <div class="table-responsive">
    <#if userList?has_content>
        <table class="table table-bordered table-striped table-hover table-custom">
            <thead class="thead-dark">
                <tr>
                    <th>Party ID</th>
                    <th>User Login ID</th>
                    <th>First Name</th>
                    <th>Middle Name</th>
                    <th>Last Name</th>
                </tr>
            </thead>
            <tbody id="userTableBody">
                <#list userList as user>
                    <tr>
                        <td ><a href="<@ofbizUrl>ViewProfile</@ofbizUrl>?partyId=${user.partyId!"N/A"}">${user.partyId!"N/A"}</a></td>
                        <td>${user.userLoginId!"N/A"}</td>
                        <td>${user.firstName!"N/A"}</td>
                        <td>${user.middleName!"N/A"}</td>
                        <td>${user.lastName!"N/A"}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    <#else>
        <h1>User List is empty!!</h1><br>
        <h3>Click <a href="<@ofbizUrl>CreateUserFtl</@ofbizUrl>">here</a> to create users</h3>
    </#if>
    </div>
</div>

