<div class="screenlet-body">
  <form id="addMilerProductEvent" method="post" action="<@ofbizUrl>addMilerProduct</@ofbizUrl>">
    <input type="hidden" name="addMilerProductFtl" value="Y"/>
    <fieldset>
      <div>
        <span class="label">${uiLabelMap.MilerProductType}</span>
        <select name="milerProductTypeId" class='required'>
          <#list milerProductTypes as productType>
            <option value='${productType.productTypeId}'>${productType.description}</option>
          </#list>
        </select>*
      </div>
      <div>
        <span class="label">${uiLabelMap.MilerProductName}</span>
        <input type="text" name="productName" id="productName" class='required' maxlength="20" />*
      </div>
      <div>
        <span class="label">${uiLabelMap.MilerProductPrice}</span>
        <input type="number" name="productPrice" id="productPrice" class='required'/>*
      </div>
      <div>
        <span class="label">${uiLabelMap.MilerProductRating}</span>
        <input type="number" name="productPrice" id="productRating" class='required'/>*
      </div>
      <div>
        <span class="label">${uiLabelMap.MilerProductDescription}</span>
        <input type="text" name="description" id="description" class='inputBox' size="60" maxlength="255" />
      </div>
    </fieldset>
    <input type="submit" value="${uiLabelMap.CommonAdd}" />
  </form>
</div>

