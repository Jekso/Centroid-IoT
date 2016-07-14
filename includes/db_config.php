<?php

date_default_timezone_set('Africa/Cairo');

$DB_SERVER 	= "localhost" ;
$DB_USER 	= "root" ;
$DB_PASS 	= "" ;
$DB_NAME 	= "iotroom" ;

try
{
    //connect to the database
    $conn = new PDO("mysql:host={$DB_SERVER};dbname={$DB_NAME};charset=utf8", $DB_USER, $DB_PASS);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}
catch(PDOException $e)
{
    echo "Connection failed: " . $e->getMessage();
}

?>
