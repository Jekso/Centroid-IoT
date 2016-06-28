<?php


$DB_SERVER 	= "localhost" ;
$DB_USER 	= "root" ;
$DB_PASS 	= "" ;
$DB_NAME 	= "itroom" ;

try
{
    //connect to the database
    $con = new PDO("mysql:host={$DB_SERVER};dbname={$DB_NAME};charset=utf8", $DB_USER, $DB_PASS);
    $con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}
catch(PDOException $e)
{
    echo "Connection failed: " . $e->getMessage();
}

?>
