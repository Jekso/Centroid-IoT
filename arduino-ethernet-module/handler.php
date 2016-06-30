<?php

require_once dirname(__FILE__) . '/../includes/System.php' ;

if (isset($_POST['system_hash']) &&
    isset($_POST['current_temp']) &&
    isset($_POST['door_state']) &&
    $_SERVER['HTTP_USER_AGENT'] == 'IoT-IT-RooM-Arduino')
{
    //get POST parameters
    $system_hash    = $_POST['system_hash'] ;
    $current_temp   = System::calculateTemp($_POST['current_temp']) ;
    $door_state     = $_POST['door_state'] ;
    //search for the system using the system_hash idintifier
    //just tall random unique string we configured it into arduino code
    $system = new System($system_hash) ;

    //security check : system must have valid system_hash idintifier
    if($system->isValid())
    {
        //update the system feedback ($current_temp,$door_state)
        $system->updateFeedback($current_temp,$door_state) ;

        //compare the current_temp with the user's critical temp , if it's higher then save Temp log
        if( $current_temp >= $system->getSystemParameters()->user_temp )
            $system->saveTempLog($current_temp) ;

        echo $system->outputResponse() ;
    }

}

?>
