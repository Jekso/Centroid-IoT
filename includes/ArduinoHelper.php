<?php

/**
 * helper class for arduino ethernet functions
 */
class ArduinoHelper
{
    private $con ;

    function __construct()
    {
        require_once dirname(__FILE__) . '/db_config.php' ;
        $this->con = $conn ;
    }



    //check the syatem_hash to be sure that is a valid registerd system
    public static function checkSystemHash($hash)
    {
        //search for the system having hash {$hash} then return it or false
    }


    //update feedback from arduino
    public static function updateFeedback($system,$current_temp,$door_state)
    {
        //update feedback for the system having hash {$system-hash}
    }


    //save bad temp log
    public static function saveTempLog($system,$current_temp)
    {
        //save bad temp log  ($system->id)
    }


    //output json response
    public static function outputResponse($system)
    {
        //check $system->mode then output the required json
    }

}

?>
