<?php

require_once dirname(__FILE__) . '/../includes/APIHelper.php' ;

if ( isset($_POST['system_hash']) )
{
    //get POST parameters
    $system_hash        = $_POST['system_hash'] ;
    $user_temp          = $_POST['user_temp'] ;
    $mode               = $_POST['ac_mode'] ;
    $data['ac_mode']    = $mode ;
    $data['user_temp']  = $user_temp ;


    //check if user choose manual mode
    if($mode == 1)
    {
        $data['ac1'] = $_POST['ac1'] ;
        $data['ac2'] = $_POST['ac2'] ;
        $data['ac3'] = $_POST['ac3'] ;
        $data['ac4'] = $_POST['ac4'] ;
    }

    
    //output json message = succeed or failed
    $handler = new APIHelper ;
    echo $handler->updateSystem($system_hash,$data) ;


}

?>
