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
        require_once dirname(__FILE__) . '/APIHelper.php' ;
        $this->con = $conn ;
        //get the hash from the data base
        //$this->system = $system ;
        $handler = new APIHelper ;
        $this->system = $handler->getSystem($system_hash) ;
    }




    //calculate the tempreture of the PT100 sensor from the resistance datasheet
    public static function calculateTemp($resistance)
    {
        //include resistance values from the datasheet and put it in $values array
        $data = file_get_contents("pt100-resistance-table.php");
	    $data = str_replace(PHP_EOL, ' ', $data);
	    $values = explode(' ',$data) ;

        //calculate the temp , check the pt100.pdf file and u will get it ^_^
        if($resistance >= end($values))
	        $temp = count($values)-1 ;
	    else
	    {
	        for ($i = 0 ; $i < count($values)-1 ; $i++)
	        {
	            if($resistance >= $values[$i] && $resistance < $values[$i+1])
	            {
	                $temp = $i ;
	                break;
	            }
	        }
	    }
		return $temp ;
    }



    //check the $system to be sure that is a valid registerd system
    public function isValid()
    {
        //return (isset($this->system)) ? true : false ;
        return ($this->system != 'error') ? true : false ;
    }



    //update feedback from arduino
    public function updateFeedback($current_temp,$door_state)
    {
        //update feedback for the system having hash {$system->hash}
        $query = "UPDATE systems SET current_temp=:current_temp,door_state=:door_state WHERE system_hash = :system_hash" ;
        $stmt = $this->con->prepare($query) ;
        $stmt->bindParam(':current_temp',$current_temp) ;
        $stmt->bindParam(':door_state',$door_state) ;
        $stmt->bindParam(':system_hash',$this->system->system_hash) ;
        $stmt->execute() ;
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
        $query = "INSERT INTO temp_log (temp,system_id) VALUES :temp , :system_id" ;
        $stmt = $this->con->prepare($query) ;
        $stmt->bindParam(':current_temp',$current_temp) ;
        $stmt->bindParam(':system_id',$this->system->id) ;
        $stmt->execute() ;
    }


    //output json response
    public function outputResponse()
    {
        //check $system->mode then output the required json
        if($this->system->ac_mode == 0) //auto
        {

        }
        else if($this->system->ac_mode == 1) //man
        {

        }
    }

}

?>
