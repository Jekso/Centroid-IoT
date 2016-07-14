<?php

require_once dirname(__FILE__) . '/../includes/APIHelper.php' ;

if ( isset($_POST['system_id']))
{
    //get POST parameters
    $system_id = $_POST['system_id'] ;

    //output json having system parameters
    $handler = new APIHelper ;
    echo $handler->getSystem($system_id) ;
}

?>
