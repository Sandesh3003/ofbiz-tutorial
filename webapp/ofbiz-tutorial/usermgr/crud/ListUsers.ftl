<div class="container mt-4">
    <table class="table table-bordered table-striped table-hover">
        <thead class="thead-dark">
            <tr>
                <th>User Login ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email Address</th>
                <th>Gender</th>
                <th>Birth Date</th>
                <th>Address1</th>
                <th>Address2</th>
                <th>City</th>
                <th>Postal Code</th>
                <th>Country</th>
                <th>Country Code>/th>
                <th>Area Code</th>
                <th>Contact Number</th>
            </tr>
        </thead>
        <tbody>
            <#list userAndPartyList as user>
                <tr>
                    <td>${user.userLoginId}</td>
                    <#if user.firstName??>
                        <td>${user.firstName}</td>
                    <#else>
                        <td>N/A</td>
                    </#if>
                    <#if user.lastName??>
                        <td>${user.lastName}</td>
                    <#else>
                        <td>N/A</td>
                    </#if>
                     <#if user.emailAddress??>
                        <td>${user.emailAddress}</td>
                     <#else>
                        <td>N/A</td>
                    </#if>
                     <#if user.gender??>
                        <td>${user.gender}</td>
                     <#else>
                        <td>N/A</td>
                    </#if>
                     <#if user.birthDate??>
                        <td>${user.birthDate}</td>
                     <#else>
                        <td>N/A</td>
                    </#if>
                     <#if user.address1??>
                        <td>${user.address1}</td>
                     <#else>
                        <td>N/A</td>
                    </#if>
                     <#if user.address2??>
                        <td>${user.address2}</td>
                     <#else>
                        <td>N/A</td>
                    </#if>
                     <#if user.city??>
                        <td>${user.city}</td>
                     <#else>
                        <td>N/A</td>
                    </#if>
                     <#if user.postalCode??>
                        <td>${user.postalCode}</td>
                     <#else>
                        <td>N/A</td>
                    </#if>
                     <#if user.countryGeoId??>
                        <td>${user.firstName}</td>
                     <#else>
                        <td>N/A</td>
                    </#if>
                     <#if user.countryCode??>
                        <td>${user.countryCode}</td>
                     <#else>
                        <td>N/A</td>
                    </#if>
                     <#if user.areaCode??>
                        <td>${user.areaCode}</td>
                     <#else>
                        <td>N/A</td>
                    </#if>
                     <#if user.contactNumber??>
                        <td>${user.contactNumber}</td>
                     <#else>
                        <td>N/A</td>
                    </#if>
                </tr>
            </#list>
        </tbody>
    </table>
</div>
