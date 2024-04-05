<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h2 class="text-center">Create User</h2>
            <form method="post" action="<@ofbizUrl>createUserEvent</@ofbizUrl>" id="createUserForm" class="custom-form">
                <div class="form-group">
                    <input type="text" class="form-control" id="userLoginId" name="userLoginId" placeholder="User Login ID" required>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="currentPassword" name="currentPassword" placeholder="Password" required>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="currentPasswordVerify" name="currentPasswordVerify" placeholder="Confirm Password" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="middleName" name="middleName" placeholder="Middle Name">
                </div>
                <div class="form-group">
                    <select class="form-control" id="gender" name="gender">
                        <option value="">Gender</option>
                        <option value ="M">Male</option>
                        <option value="F">Female</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="addressLine1" name="address1" placeholder="Address Line 1" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="addressLine2" name="address2" placeholder="Address Line 2">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="city" name="city" placeholder="City" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="postalCode" name="postalCode" placeholder="Postal Code" required>
                </div>
                <div class="form-group">
                    <select class="form-control" id="countryGeoId" name="countryGeoId" required>
                        <option value="">Select Country</option>
                        <#list countries as country>
                            <option value="${country.geoId}">${country.geoName}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="countryCode" name="countryCode" placeholder="Country Code" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="areaCode" name="areaCode" placeholder="Area Code">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="contactNumber" name="contactNumber" placeholder="Contact Number" required>
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" id="emailAddress" name="emailAddress" placeholder="Email ID" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Create User</button>
            </form>
           <#if errors?has_content>
               <div class="alert alert-danger alert-dismissible fade show" role="alert">
                   <strong>Error!</strong> There were some issues with your submission:
                   <ul>
                       <#list errors as err>
                           <li>${err}</li>
                       </#list>
                   </ul>
                   <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                       <span aria-hidden="true">&times;</span>
                   </button>
               </div>
           </#if>

        </div>
    </div>
</div>
