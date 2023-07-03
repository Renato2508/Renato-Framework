<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="showOneEmp.do" method="POST" enctype="multipart/form-data">
        <p>Nom: <input type="text" name="nom"></p>
        <p>Age: <input type="text" name="age"></p>
        <p> Date embauche: <input type="date" name ="embauche"></p>
        <p> Note: <input type="text" name ="note"></p>
        <p> Salaire: <input type="text" name ="salaire"></p>
        <p>Votre fichier : <input type="file" name = "file"></p>
        <input type="submit" value="Valider">
    </form>
</body>
</html>