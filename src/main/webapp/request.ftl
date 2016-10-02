<!DOCTYPE html>
<html lang="en">
<head>
    <title>Solicitud de cita</title>
</head>
<body>
<form action="action_page.php">
    <fieldset>
        <legend>Personal information:</legend>
        Nombre:<br>
        <input type="text" name="name" ><br>
        Apellido:<br>
        <input type="text" name="lastname" ><br>
        Correo electronico:<br>
        <input type="email" name="mail" ><br>
        Numero de telefono:<br>
        <input type="number" name="number" ><br>
        Fecha y hora:
        <input type="datetime-local" name="appointmentdatetime"><br><br>
        Tipo ciguria:
        <br />
        <input name="facejob" type="checkbox" /> Cirugia facial
        <br />
        <input name="breastjob" type="checkbox" checked="checked" /> Cirugia senos
        <br />
        <input name="abdominaljob1" type="checkbox" />Liposucción
        <br />
        <input name="abdominaljob2" type="checkbox" />Cirugía bariátrica
        <br />
        <input name="buttok" type="checkbox" />Gluteoplastía
        <br />
        <input name="other" type="checkbox" /> Otro
        <input type="text" name="other" ><br>


        <input type="submit" value="Submit">
    </fieldset>
</form>
</body>
</html>