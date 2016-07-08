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
    public function getUserProfile($username,$password)
    {
        $query = "SELECT * FROM users WHERE username = :username AND password = :password" ;
        $stmt = $this->con->prepare($query) ;
        $stmt->bindParam(':username',$username) ;
        $stmt->bindParam(':password',$password) ;
        $stmt->execute();
		$user = $stmt->fetch(PDO::FETCH_OBJ);
        if($user)
            return json_encode($user, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES );
        return json_encode(['error' => 1], JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES );
    }


    //return the system in json
    public function getSystem($system_id)
    {
        $query = "SELECT * FROM systems WHERE id = :system_id " ;
        $stmt = $this->con->prepare($query) ;
        $stmt->bindParam(':system_id',$system_id) ;
        $stmt->execute();
		$system = $stmt->fetch(PDO::FETCH_OBJ);
        if($system)
            return json_encode($system, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES );
        return json_encode(['error' => 1], JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES );
    }


    //update the system with new data_array
    public function updateSystem($system_id,$data)
    {
        if(count($data) == 2)
        {
            $query = "UPDATE systems SET ac_mode=:ac_mode,user_temp=:user_temp WHERE id = :system_id" ;
            $stmt = $this->con->prepare($query) ;
            $stmt->bindParam(':ac_mode',$data['ac_mode']) ;
            $stmt->bindParam(':user_temp',$data['user_temp']) ;
        }
        else
        {
            $query = "UPDATE systems SET ac_mode=:ac_mode,user_temp=:user_temp,ac1=:ac1,ac2=:ac2,ac3=:ac3,ac4=:ac4 WHERE id = :system_id" ;
            $stmt = $this->con->prepare($query) ;
            $stmt->bindParam(':ac_mode',$data['ac_mode']) ;
            $stmt->bindParam(':ac1',$data['ac1']) ;
            $stmt->bindParam(':ac2',$data['ac2']) ;
            $stmt->bindParam(':ac3',$data['ac3']) ;
            $stmt->bindParam(':ac4',$data['ac4']) ;
            $stmt->bindParam(':user_temp',$data['user_temp']) ;
        }
        $stmt->bindParam(':system_id',$system_id) ;
        if($stmt->execute())
            $jsonArr = ['process'=>'succeed'] ;
        else
            $jsonArr = ['process'=>'failed'] ;
        return json_encode($jsonArr, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES ) ;
    }
}

?>
