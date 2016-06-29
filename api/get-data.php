<?php

if ( isset($_POST['system_hash']))
{
    //get POST parameters
    $system_hash = $_POST['system_hash'] ;

    //output json having system parameters
    echo APIHelper::getSystem($system_hash) ;
}

}

?>
