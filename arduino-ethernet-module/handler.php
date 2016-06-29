<?php

if (isset($_POST['system_hash']) &&
    isset($_POST['current_temp']) &&
    isset($_POST['door_state']) &&
    $_SERVER['HTTP_USER_AGENT'] == 'IoT-IT-RooM-Arduino')
{
    //get POST parameters
    $system_hash    = $_POST['syatem_hash'] ;
    $current_temp   = ArduinoHelper::calculateTemp($_POST['current_temp']) ;
    $door_state     = $_POST['door_state'] ;

    //search for the system using the system_hash
    $system = ArduinoHelper::getSystem($system_hash) ;

    //security check : system must have verified system_hash
    //just tall random unique string we configured it in arduino code
    if($system)
    {
        //update the system feedback(door_state,current_temp)
        ArduinoHelper::updateFeedback($system,$current_temp,$door_state) ;

        //compare the current_temp with the user's critical temp , if it's higher then save log
        if( $current_temp >= $system->user_temp )
            ArduinoHelper::saveTempLog($system,$current_temp) ;

        ArduinoHelper::outputResponse($system) ;
    }

}

?>
