<h2 class="mt-3 mb-4 text-center">Create User <@ofbizUrl>createUserEvent</@ofbizUrl> </h2>
<form method ="post" action="<@ofbizUrl>createUserEvent</@ofbizUrl>" id="createUserForm">
    <div class="form-group">
        <label for="userLoginId">User Login ID</label>
        <input type="text" class="form-control" id="userLoginId" name="userLoginId" required>
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password" required>
    </div>
    <div class="form-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
    </div>
    <div class="form-group">
        <label for="firstName">First Name</label>
        <input type="text" class="form-control" id="firstName" name="firstName" required>
    </div>
    <div class="form-group">
        <label for="lastName">Last Name</label>
        <input type="text" class="form-control" id="lastName" name="lastName" required>
    </div>
    <div class="form-group">
        <label for="middleName">Middle Name</label>
        <input type="text" class="form-control" id="middleName" name="middleName">
    </div>
    <div class="form-group">
        <label for="birthdate">Birthdate</label>
        <input type="text" class="form-control" id="birthDate" placeholder="yyyy-mm-dd" name="birthDate" required>
    </div>
    <!-- Postal Address -->
    <div class="form-group">
        <label for="addressLine1">Address Line 1</label>
        <input type="text" class="form-control" id="addressLine1" name="address1" required>
    </div>
    <div class="form-group">
        <label for="addressLine2">Address Line 2</label>
        <input type="text" class="form-control" id="addressLine2" name="address2">
    </div>
    <div class="form-group">
        <label for="city">City</label>
        <input type="text" class="form-control" id="city" name="city" required>
    </div>
    <div class="form-group">
        <label for="postalCode">Postal Code</label>
        <input type="text" class="form-control" id="postalCode" name="postalCode" required>
    </div>
    <div class="form-group">
        <label for="countryGeoId">Country</label>
        <select class="form-control" id="countryGeoId" name="countryGeoId" required>
            <option value="">Select Country</option>
            <#list countries as country>
                <option value="${country.geoId}">${country.geoName}</option>
            </#list>
        </select>
    </div>
    <!-- Telecom Number -->
    <div class="form-group">
        <label for="countryCode">Country Code</label>
        <input type="text" class="form-control" id="countryCode" name="countryCode" required>
    </div>
    <div class="form-group">
        <label for="areaCode">Area Code</label>
        <input type="text" class="form-control" id="areaCode" name="areaCode">
    </div>
    <div class="form-group">
        <label for="contactNumber">Contact Number</label>
        <input type="text" class="form-control" id="contactNumber" name="contactNumber" required>
    </div>
    <!-- Email ID -->
    <div class="form-group">
        <label for="email">Email ID</label>
        <input type="email" class="form-control" id="emailAddress" name="emailAddress" required>
    </div>
    <button type="submit" class="btn btn-primary btn-block">Create User</button>
</form>
