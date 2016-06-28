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
}

?>
