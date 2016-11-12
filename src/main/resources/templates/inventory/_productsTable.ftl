<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#product">New Product</button>
<table id="datatable-responsive" class="table table-hover table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>${user.clinic.clinicPrefix} ID</th>
        <th>NAME</th>
        <th>DESCRIPTION</th>
        <th>SUPPLIER</th>
        <th>PRICE</th>
        <th>QUANTITY</th>
    </tr>
    </thead>
    <tbody>
    <#list productList as product>
    <tr>
        <td>${product.productId}</td>
        <td>${product.productName}</td>
        <td>${product.productDescription}</td>
        <td>${product.supplier}</td>
        <td>$${product.productPrice}</td>
        <td>${product.productInStock} unit<#if product.productInStock gt 1>s</#if></td>
    </tr>
    <#else>
    <tr>
        <th scope="row">No Products Registered</th>

    </tr>
    </#list>
    </tbody>
</table>