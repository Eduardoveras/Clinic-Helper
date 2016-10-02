<!DOCTYPE html>
<html lang="en">
<head>
    <title>Solicitud de cita</title>
</head>
<body>
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
        Fecha De nacimiento:
        <input type="date" name="dateofbirth"><br>
        Sexo:
        <input type="radio" name="gender" value="male" checked> Masculino<br>
        <input type="radio" name="gender" value="female"> Femenino<br>
        <input type="radio" name="gender" value="other"> Otro
        Fecha De Registro:
        <input type="date" name="registeredDate"><br>
        Nacionalidad:
        <select id="countries">
            <option disabled selected value> -- Seleccione un pais -- </option>
            <option value="RD">Republica Dominicana</option>
            <option value="China">China</option>
            <option value="Venezuela">Venezuela</option>
        </select>
        </select>
        <select id="cities"></select>



        <br />


        <input type="submit" value="Submit">
    </fieldset>
</form>
</body>
</html>