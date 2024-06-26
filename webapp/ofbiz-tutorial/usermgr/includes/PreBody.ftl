<html>
<head>
  <meta name="viewport" content="width=device-width, user-scalable=no">
  <#if layoutSettings.companyName??>
    <title>${layoutSettings.companyName}</title>
  <#else>
    <title>User Manager</title>
  </#if>
  <!-- Bootstrap CSS -->
  <#if webSiteFaviconContent?has_content>
    <link rel="shortcut icon" href="">
  </#if>
  <#if layoutSettings.styleSheets??>
       <#list layoutSettings.styleSheets as styleSheet>
           <link rel="styleSheet" href="${StringUtil.wrapString(styleSheet)}" type="text/css"/>
       </#list>
  </#if>
  <#if layoutSettings.javaScripts??>
       <#list layoutSettings.javaScripts as javaScript>
           <script type="text/javascript" src="${StringUtil.wrapString(javaScript)}"></script>
       </#list>
  </#if>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&family=Teko&display=swap" rel="stylesheet">
</head>
<body>

  <!-- Breadcrumbs -->
  <div class="container mt-4">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="<@ofbizUrl>main</@ofbizUrl>">Home</a></li>
        <#if uiLabelMap[titleProperty]??>
             <li class="breadcrumb-item active" aria-current="page">${uiLabelMap[titleProperty]}</li>
        <#else>
            <li class="breadcrumb-item active" aria-current="page">Data</li>
        </#if>
      </ol>
    </nav>
  </div>

  <!-- Hero Section -->
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div id="main-content">

