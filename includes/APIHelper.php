<?php

/**
 * helper class for api functions
 */
class APIHelper
{

    private $con ;

    function __construct()
    {
        require_once dirname(__FILE__) . '/db_config.php' ;
        $this->con = $conn ;
    }

    //authenticate user
    public static function getUserProfile($username,$password)
    {
        //search for the user in users table then get sytem data using system_id
        //return his data in json if found , or return false
    }


    //return the system in json
    public static function getSystem($system_hash)
    {
        //search for the system using {$system_hash} in systems table
    }


    //update the system with new data_array
    public static function updateSystem($system_hash,$data)
    {
        //update data for the system
    }
}

?>
