<#if isAdmin><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#medications">New Medication</button><#else><strong>Sign in as an ADMIN to register new medication</strong></#if>
<table id="datatable-responsive" class="table table-hover table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>${user.clinic.clinicPrefix} ID</th>
        <th>NAME</th>
        <th>SUPPLIER</th>
        <th>DESCRIPTION</th>
        <th>PRICE</th>
        <th>QUANTITY</th>
    </tr>
    </thead>
    <tbody>
    <#list medicationList as medication>
    <tr>
        <td>${medication.medicationId}</td>
        <td>${medication.medicationName}</td>
        <td>${medication.supplier}</td>
        <td>${medication.medicationDescription}</td>
        <td>$${medication.medicationPrice}</td>
        <td>${medication.medicationInStock} unit<#if medication.medicationInStock gt 1>s</#if></td>
    </tr>
    <#else>
    <tr>
        <th scope="row">No Medication Registered</th>

    </tr>
    </#list>
    </tbody>
</table>