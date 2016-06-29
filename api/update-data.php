<?php

if ( isset($_POST['system_hash']) )
{
    //get POST parameters
    $system_hash  = $_POST['system_hash'] ;
    $user_temp    = $_POST['user_temp'] ;
    $mode         = $_POST['mode'] ;
    $data['mode'] = $mode ;
    if($mode == 'man')
    {
        $data['ac'] = $_POST['ac'] ;
    }
    $data['user_temp'] = $user_temp ;


    //output json message = succeed or failed
    echo APIHelper::updateSystem($system_hash,$data) ;


}

?>
