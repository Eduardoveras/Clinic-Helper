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
        <input type="text" name="name" ><br>
        Apellido:<br>
        <input type="text" name="lastname" ><br>
        Cedula:<br>
        <input type="text" name="idCard" ><br>
        Correo electronico:<br>
        <input type="email" name="mail" ><br>
        Numero de telefono:<br>
        <input type="number" name="telephonenumber" ><br>
        Numero de telefono de contacto:<br>
        <input type="number" name="contacttelephonenumber" ><br>
        Direccion:<br>
        <input type="text" name="address" ><br>
        Ocupacion:<br>
        <input type="text" name="occupation" ><br>
        Fecha De nacimiento:
        <input type="date" name="dateofbirth"><br>
        Fecha De Registro:
        <input type="date" name="registeredDate"><br>
        Sexo:
        <input type="radio" name="gender" value="male" > Masculino<br>
        <input type="radio" name="gender" value="female"> Femenino<br>
        <input type="radio" name="gender" value="other"> Otro
        Nacionalidad:
        <select id="countries">
            <option disabled selected value> -- Seleccione un pais -- </option>
            <option id="RD">Republica Dominicana</option>
            <option id="China">China</option>
            <option id="Venezuela">Venezuela</option>
        </select>
        </select>
        <select id="cities"></select>



        <br />


        <input type="submit" value="Submit">
    </fieldset>
</form>


    </main>
</div>


</body>