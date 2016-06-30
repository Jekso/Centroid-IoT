<?php

require_once dirname(__FILE__) . '/../includes/APIHelper.php' ;

if ( isset($_POST['system_hash']))
{
    //get POST parameters
    $system_hash = $_POST['system_hash'] ;

    //output json having system parameters
    $handler = new APIHelper ;
    echo $handler->getSystem($system_hash) ;
}

?>
