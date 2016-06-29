<?php

/**
 * helper class for arduino ethernet functions
 */
class system
{
    private $con,$system ;

    function __construct($system_hash)
    {
        require_once dirname(__FILE__) . '/db_config.php' ;
        $this->con = $conn ;
        //get the hash from the data base
        //$this->system = $system ;
    }




    //calculate the tempreture of the PT100 sensor from the resistance datasheet
    public static function calculateTemp($resistance)
    {

    }



    //check the $system to be sure that is a valid registerd system
    public function isValid()
    {
        return (isset($this->system)) ? true : false ;
    }



    //update feedback from arduino
    public function updateFeedback($current_temp,$door_state)
    {
        //update feedback for the system having hash {$system->hash}
    }



    //getter function for the system
    public function getSystemParameters()
    {
        return $this->system ;
    }



    //save bad temp log
    public function saveTempLog($current_temp)
    {
        //save bad temp log  ($system->id)
    }


    //output json response
    public function outputResponse()
    {
        //check $system->mode then output the required json
    }

}

?>
