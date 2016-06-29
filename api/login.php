<?php

if ( isset($_POST['username']) && isset($_POST['password']) )
{
    //get POST parameters
    $username = $_POST['username'] ;
    $password = $_POST['password'] ;

    //output json having user data and the system belongs to that user
    echo APIHelper::getUserProfile($username,$password) ;
}

?>
