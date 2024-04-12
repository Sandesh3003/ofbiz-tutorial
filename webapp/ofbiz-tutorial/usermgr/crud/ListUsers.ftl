<div class="container mt-4">
    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover table-custom">
            <thead class="thead-dark">
                <tr>
                    <th>User Login ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email Address</th>
                    <th>Address1</th>
                    <th>Address2</th>
                    <th>City</th>
                    <th>Postal Code</th>
                    <th>Country</th>
                    <th>Country Code</th>
                    <th>Area Code</th>
                    <th>Contact Number</th>
                </tr>
            </thead>
            <tbody id="userTableBody">
                <#list userAndPartyList as user>
                    <tr>
                        <td>${user.userLoginId!"N/A"}</td>
                        <td>${user.firstName!"N/A"}</td>
                        <td>${user.lastName!"N/A"}</td>
                        <td>${user.emailAddress!"N/A"}</td>
                        <td>${user.address1!"N/A"}</td>
                        <td>${user.address2!"N/A"}</td>
                        <td>${user.city!"N/A"}</td>
                        <td>${user.postalCode!"N/A"}</td>
                        <td>${user.countryGeoId!"N/A"}</td>
                        <td>${user.countryCode!"N/A"}</td>
                        <td>${user.areaCode!"N/A"}</td>
                        <td>${user.contactNumber!"N/A"}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>
