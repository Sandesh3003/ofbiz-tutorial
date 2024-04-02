<div class="container">
  <div class="page-header">
    <h1>User Profile</h1>
  </div>
  <div class="row">
      <div class="col-md-6">
        <div class="panel">
          <div class="panel-heading">Personal Details</div>
          <div class="panel-body">
            <p><strong>First Name:</strong> ${personalDetails.firstName!"N/A"}</p>
            <p><strong>Last Name:</strong> ${personalDetails.lastName!"N/A"}</p>
            <p><strong>Date of Birth:</strong> ${personalDetails.birthDate!"N/A"}</p>
            <p><strong>Gender:</strong> ${personalDetails.gender!"N/A"}</p>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <div class="panel">
          <div class="panel-heading">Postal Addresses</div>
          <div class="panel-body">
            <#list postalAddresses as address>
              <p><strong>${address.contactMechPurposeTypeId!"Address"}:</strong> ${address.address1!"N/A"}, ${address.address2!"N/A"}, ${address.city!"N/A"}, ${address.countryGeoId!"N/A"}, ${address.postalCode!"N/A"}</p>
            </#list>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6">
        <div class="panel">
          <div class="panel-heading">Telecom Numbers</div>
          <div class="panel-body">
            <#list telecomNumbers as telecom>
              <p><strong>${telecom.contactMechPurposeTypeId!"Primary"}:</strong> ${telecom.countryCode!"-"} ${telecom.areaCode!"-"} ${telecom.contactNumber!"N/A"}</p>
            </#list>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <div class="panel">
          <div class="panel-heading">Email Addresses</div>
          <div class="panel-body">
            <#list emailAddresses as email>
              <p><strong>${email.contactMechPurposeTypeId!"Primary"}:</strong> ${email.emailAddress!"N/A"}</p>
            </#list>
          </div>
        </div>
      </div>
</div>