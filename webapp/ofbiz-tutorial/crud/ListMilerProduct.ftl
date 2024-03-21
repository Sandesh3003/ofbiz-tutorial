 <div class="screenlet-body">
  <#if milerProductList?has_content>
    <table cellspacing=0 cellpadding=2 border=0 class="basic-table">
      <thead><tr>
        <th>${uiLabelMap.MilerProductId}</th>
        <th>${uiLabelMap.MilerProductType}</th>
        <th>${uiLabelMap.MilerProductName}</th>
        <th>${uiLabelMap.MilerProductDescription}</th>
        <th>${uiLabelMap.MilerProductPrice}</th>
        <th>${uiLabelMap.MilerProductRating}</th>
      </tr></thead>
      <tbody>
        <#list milerProductList as milerProduct>
          <tr>
            <td>${milerProduct.productId}</td>
            <td>${milerProduct.getRelatedOne("MilerProductType").get("description", locale)}</td>
            <td>${milerProduct.productName?default("NA")}</td>
            <td>${milerProduct.productPrice?default("NA")}</td>
            <td>${milerProduct.productRating?default("NA")}</td>
            <td>${milerProduct.description!}</td>
          </tr>
        </#list>
       </tbody>
    </table>
  </#if>
</div>

