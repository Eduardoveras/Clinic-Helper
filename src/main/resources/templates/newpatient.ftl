<#include "/header.ftl">

<body>
<div class="loader"></div>

<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">

<#include "/navbar.ftl">
<#include "/sidebar.ftl">


    <main class="mdl-layout__content mdl-color--grey-100">



<form >
    <fieldset>
        <legend>Registro de paciente:</legend>
        Nombre:<br>
        <input type="text" name="firstName" ><br>
        Apellido:<br>
        <input type="text" name="lastName" ><br>
        Cedula:<br>
        <input type="text" name="idCard" ><br>
        Correo electronico:<br>
        <input type="email" name="mail" ><br>
        Numero de telefono:<br>
        <input type="text" name="telephoneNumber" ><br>
        Numero de telefono de contacto:<br>
        <input type="text" name="contactTelephoneNumber" ><br>
        Direccion:<br>
        <input type="text" name="address" ><br>
        Ocupacion:<br>
        <input type="text" name="occupation" ><br>
        Fecha De nacimiento:
        <input type="date" name="dateOfbirth"><br>
        Fecha De Registro:
        <input type="date" name="registeredDate"><br>
        Sexo:
        <input type="radio" name="gender" value="male" > Masculino<br>
        <input type="radio" name="gender" value="female"> Femenino<br>
        <input type="radio" name="gender" value="other"> Otro
        Nacionalidad:
        <select id="countries" name="countries">
            <option disabled selected value> -- Seleccione un pais -- </option>
            <option id="RD">Republica Dominicana</option>
            <option id="China">China</option>
            <option id="Venezuela">Venezuela</option>
        </select>
        Nacionalidad:
        <select id="countries" name="nationality">
            <option disabled selected value> -- Seleccione un pais -- </option>
            <option id="RD">Republica Dominicana</option>
            <option id="China">China</option>
            <option id="Venezuela">Venezuela</option>
        </select>
        Pais Actual:
        <select id="countries" name="countries">
            <option disabled selected value> -- Seleccione un pais -- </option>
            <option id="RD">Republica Dominicana</option>
            <option id="China">China</option>
            <option id="Venezuela">Venezuela</option>
        </select>
        </select>
        <select id="cities" name="cities"></select>



        <br />


        <input type="submit" value="Submit">
    </fieldset>
</form>
<table>
    <tr>
        <th>All patients</th>
    </tr>
    <tr>
        <th>jascId</th> <th>patient Name</th> <th>patient Email</th>
    </tr>
    <#list patientList?sort_by("jascId") as patient>
        <tr>
            <th>${patient.getJascId()}</th>
            <th>${patient.getFirstName()} ${patient.getLastName()}</th>
            <th>${patient.getEmail()}</th>
        </tr>
    </#list>
</table>
    </main
</div>


</body>